//package com.khatabook.security;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validator;
//
//import org.springframework.context.support.DefaultMessageSourceResolvable;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.net.MediaType;
//import com.khatabook.request.user.UserLoginDTO;
//
//import io.jsonwebtoken.io.IOException;
//
//public class ValidationFilter extends OncePerRequestFilter {
//
//    private final ObjectMapper objectMapper;
//    private final Validator validator;
//
//    public ValidationFilter(ObjectMapper objectMapper, Validator validator) {
//        this.objectMapper = objectMapper;
//        this.validator = validator;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        if (isLoginRequest(request)) {
//            try {
//                UserLoginDTO authenticationRequest = objectMapper.readValue(request.getInputStream(), UserLoginDTO.class);
//                validateRequest(authenticationRequest);
//            } catch ( ex) {
//                system.out.println()
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isLoginRequest(HttpServletRequest request) {
//        return request.getMethod().equals(HttpMethod.POST.name()) && request.getRequestURI().equals("/login");
//    }
//
//    private void validateRequest(Object request) throws MethodArgumentNotValidException {
//        Set<ConstraintViolation<Object>> violations = validator.validate(request);
//        if (!violations.isEmpty()) {
//            throw new MethodArgumentNotValidException(null, violations);
//        }
//    }
//
//    
//}
