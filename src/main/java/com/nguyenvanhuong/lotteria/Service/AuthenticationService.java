package com.nguyenvanhuong.lotteria.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.cloudinary.api.exceptions.ApiException;
import com.nguyenvanhuong.lotteria.dto.request.AuthenticationRequest;
import com.nguyenvanhuong.lotteria.dto.request.IntrospectRequest;
import com.nguyenvanhuong.lotteria.dto.request.LogoutRequest;
import com.nguyenvanhuong.lotteria.dto.response.AuthenticationResponse;
import com.nguyenvanhuong.lotteria.dto.response.IntrospectResponse;
import com.nguyenvanhuong.lotteria.entity.InvalidatedToken;
import com.nguyenvanhuong.lotteria.entity.User;
import com.nguyenvanhuong.lotteria.exception.AppExeption;
import com.nguyenvanhuong.lotteria.exception.ErrorCode;
import com.nguyenvanhuong.lotteria.reponsitory.InvalidatedTokenReponsitory;
import com.nguyenvanhuong.lotteria.reponsitory.UserReponsitory;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationService {
	@Autowired
	InvalidatedTokenReponsitory invalidatedTokenReponsitory;
	@Autowired
	UserReponsitory userRepository;
	@Value("${jwt.signerKey}")
	protected String SIGNER_KEY;
	
	public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
		var token=request.getToken();
		try {
			verifyToken(token);
		} catch (Exception e) {
			return IntrospectResponse.builder().valid(false).build();
		}
		
		
		return IntrospectResponse.builder().valid(true).build();
	}
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
		var user=userRepository.findByPhoneNumber(request.getPhoneNumber()).orElseThrow(()->new AppExeption(ErrorCode.USER_NOT_EXIST));
		
		boolean authenticated= passwordEncoder.matches(request.getPassword(), user.getPassword());
		if (!authenticated) {
			throw new AppExeption(ErrorCode.UNAUTHENTICATED);
		}
		var token=generateToken(user);
		log.info(SIGNER_KEY);
		return AuthenticationResponse.builder().token(token).authenticated(authenticated).build();
		
	}
	
	private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
		JWSVerifier verifier=new MACVerifier(SIGNER_KEY.getBytes());
		
		SignedJWT signedJWT=SignedJWT.parse(token);
		
		Date expityTime=signedJWT.getJWTClaimsSet().getExpirationTime();
		
		var verified=signedJWT.verify(verifier);
		
		if (! verified && expityTime.after(new Date())) {
			throw new AppExeption(ErrorCode.UNAUTHENTICATED);
		}
		
		if (invalidatedTokenReponsitory.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
			throw new AppExeption(ErrorCode.UNAUTHENTICATED);
		}
		
		return signedJWT;
	}
	
	private String generateToken(User user) {
		JWSHeader header=new JWSHeader(JWSAlgorithm.HS512);
		
		JWTClaimsSet jwtClaimsSet=new JWTClaimsSet.Builder()
				.subject(user.getPhoneNumber())
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
				.jwtID(UUID.randomUUID().toString())
				.claim("scope", buildScope(user))
				.build();
		
		Payload payload=new Payload(jwtClaimsSet.toJSONObject());
		
		JWSObject jwsObject=new JWSObject(header, payload); 
		
		try {
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (KeyLengthException e) {
	
			e.printStackTrace();
		} catch (JOSEException e) {
			log.error("can not create token");
			e.printStackTrace();
		}
		return null;
	}
	
	public void logout(LogoutRequest request) throws JOSEException, ParseException {
		var signToken=verifyToken(request.getToken());
		String jid=signToken.getJWTClaimsSet().getJWTID();
		Date expriTime=signToken.getJWTClaimsSet().getExpirationTime();
		
		InvalidatedToken invalidatedToken = InvalidatedToken.builder().Id(jid).expriTime(expriTime).build();
		
		invalidatedTokenReponsitory.save(invalidatedToken);
	}
	
	private String buildScope(User user) {
		StringJoiner joiner=new StringJoiner(" ");
		if (!CollectionUtils.isEmpty(user.getRoles())) {
			user.getRoles().forEach(role->{
				joiner.add("ROLE_"+role.getName().toString());
				if (!CollectionUtils.isEmpty(user.getRoles())) {
					role.getPermissions().forEach(permission->joiner.add(permission.getName()));
				}
			});
			
		}
		return joiner.toString();
	}
	
}
