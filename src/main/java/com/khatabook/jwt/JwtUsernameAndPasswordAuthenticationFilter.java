package com.khatabook.jwt;

import com.khatabook.auth.ApplicationUser;
 
import com.fasterxml.jackson.databind.ObjectMapper;
import com.khatabook.request.user.UserLoginDTO;
import com.khatabook.response.user.LoggedInUser;
import com.khatabook.response.user.UserLoginResponseDTO;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

class CustomAuthenticationException extends AuthenticationException {
    public CustomAuthenticationException(String msg) {
        super(msg);
    }
    public CustomAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager,
                                                      JwtConfig jwtConfig,SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
        	UserLoginDTO authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(),UserLoginDTO.class);
        	
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(),
                    authenticationRequest.getPassword()
            );
            System.out.println(authentication.getClass());
//            System.out.println(authentication.getPrincipal());
//            System.out.println(authentication.getName());
            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
        	
        	 // Set an appropriate error response
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            try {
//                response.getWriter().write("An error occurred while processing the request.");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
            throw new CustomAuthenticationException("An error occurred while processing the authentication request", e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
//        System.out.println(authResult.getClass());
//    	System.out.println(authResult.getPrincipal().getClass());
//        System.out.println(authResult.getName());
        String email = authResult.getName();
        String userName = ((ApplicationUser)authResult.getPrincipal()).getRealUserName();
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        	System.out.println("hello");
//        this.userName = userName;
//        this.email = email;
//        this.token = token;
            UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO(200,"Success","User logged In",new LoggedInUser(userName,email,token));

        	    // Serialize AuthResponse object to JSON
        	    ObjectMapper objectMapper = new ObjectMapper();
        	    // Set the response content type to JSON
        	    response.setContentType("application/json");
        	    // Write the JSON response to the response body
        	    objectMapper.writeValue(response.getWriter(), userLoginResponseDTO);
        	    System.out.println(authResult);
//        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
    
//	    public ResponseEntity<AuthResponse> mee(AuthResponse authResponse )
//	    {
//	    	return ResponseEntity.status(404).body(authResponse);
//	    }
}
