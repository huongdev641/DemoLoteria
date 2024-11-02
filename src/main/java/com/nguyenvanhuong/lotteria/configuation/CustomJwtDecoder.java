package com.nguyenvanhuong.lotteria.configuation;

import java.text.ParseException;
import java.util.Objects;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import com.nguyenvanhuong.lotteria.Service.AuthenticationService;
import com.nguyenvanhuong.lotteria.dto.request.IntrospectRequest;
import com.nimbusds.jose.JOSEException;

@Component
public class CustomJwtDecoder implements JwtDecoder {
	@Autowired
	private AuthenticationService authenticationService;
	
	@Value("${jwt.signerKey}")
	private String signerKey;
	
	private NimbusJwtDecoder nimbusJwtDecoder=null;
	@Override
	public Jwt decode(String token) throws JwtException {
		try {
			var reponse = authenticationService.introspect(IntrospectRequest.builder().token(token).build());
			if (!reponse.isValid()) {
				throw new JwtException("token invalid");
			}
		} catch (JOSEException | ParseException e) {
			throw new JwtException(e.getMessage());
		}
		if (Objects.isNull(nimbusJwtDecoder)) {
			SecretKeySpec secretKeySpec=new SecretKeySpec(signerKey.getBytes(), "HS512");
			nimbusJwtDecoder=NimbusJwtDecoder
	    			.withSecretKey(secretKeySpec)
	    			.macAlgorithm(MacAlgorithm.HS512)
	    			.build();
		}
		return nimbusJwtDecoder.decode(token);
	}
	
}
