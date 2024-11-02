package com.nguyenvanhuong.lotteria.configuation;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;






@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	CustomJwtDecoder customJwtDecoder;
	
	private final String[] PUBLIC_ENDPOINTS= {
			"/user", "/auth/token", "/auth/introspect", "/auth/logout", "/category" 
	};
	@Value("${jwt.signerKey}")
	private String signerKey;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request->
        		request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()	
        		.requestMatchers(HttpMethod.GET, "/category").permitAll()
        		.anyRequest().authenticated());
        
        httpSecurity.oauth2ResourceServer(
        		oauth2->oauth2.jwt(jwtConfigurer->jwtConfigurer.decoder(customJwtDecoder).jwtAuthenticationConverter(jwtAuthenticationConverter())));
        
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        
        return httpSecurity.build();
    }
    
//    @Bean
//    public CorsFilter corsFilter() {
//    	CorsConfiguration corsConfiguration=new CorsConfiguration();
//    	corsConfiguration.addAllowedOrigin("http://localhost:3000");
//    	corsConfiguration.addAllowedMethod("*");
//    	corsConfiguration.addAllowedHeader("*");
//    	
//    	UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
//    	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//    	return new CorsFilter((CorsConfigurationSource) urlBasedCorsConfigurationSource);
//    }
    
    
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
    	JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
    	jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
    	
    	JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
    	jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    	return jwtAuthenticationConverter;
    }
    
 
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder(10);
    }

}