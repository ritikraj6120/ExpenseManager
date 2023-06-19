package com.khatabook.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	protected static final Log pageNotFoundLogger = LogFactory.getLog(PAGE_NOT_FOUND_LOG_CATEGORY);
  
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
	System.out.println(ex);
    Map<String, Map<String, List<String>>> body = new HashMap<>();
    Map<String, List<String>> errorMap = new HashMap<>();
    ArrayList<FieldError> a= new ArrayList<>(ex.getBindingResult().getFieldErrors());
    System.out.println(a.get(0).getClass());
    ex.getBindingResult().getFieldErrors().forEach(error -> {
    	String key = error.getField();
    	String value = error.getDefaultMessage();
        if (errorMap.containsKey(key)) {
            // Key exists, retrieve the list associated with the key
            List<String> values = errorMap.get(key);
            // Add the new values to the list
            values.add(value);
        } else {
            // Key does not exist, create a new list and add the values to it
            List<String> values = new ArrayList<>();
            values.add(value);
            // Put the key-value pair in the multi-map
            errorMap.put(key, values);
        }
    });
        
    body.put("error", errorMap);
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
		HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		Map<String,String > body = new HashMap<>();
		String supportedMethodString = String.join(", ", supportedMethods.stream().map(Enum::toString).collect(Collectors.toList()));
		String bodyContent = ex.getMessage() + ". Supported media types are " + supportedMethodString;
		body.put("error",bodyContent);
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
		HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
		if (!CollectionUtils.isEmpty(mediaTypes)) {
			headers.setAccept(mediaTypes);
			if (request instanceof ServletWebRequest) {
				ServletWebRequest servletWebRequest = (ServletWebRequest) request;
				if (HttpMethod.PATCH.equals(servletWebRequest.getHttpMethod())) {
					headers.setAcceptPatch(mediaTypes);
				}
			}
		}
		Map<String,String > body = new HashMap<>();
		String mediaTypeString = String.join(", ", mediaTypes.stream().map(MediaType::toString).collect(Collectors.toList()));
		String bodyContent = ex.getMessage() + ". Supported media types are " + mediaTypeString;
		body.put("error",bodyContent);
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(
			TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String,String > body = new HashMap<>();
		String bodyContent = ex.getMessage();
		body.put("error",bodyContent);
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(
			MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String,String > body = new HashMap<>();
		String bodyContent = ex.getMessage();
		body.put("error",bodyContent);
//		System.out.println("mo");
//		System.out.println(ex.getMessage());
//		System.out.println(ex.getVariableName());
//		System.out.println(ex.getParameter());
//		System.out.println("bo");
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String,String > body = new HashMap<>();
		String bodyContent = ex.getMessage();
		body.put("error",bodyContent);
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("hello");
		Map<String,String > body = new HashMap<>();
		String bodyContent = ex.getMessage();
		body.put("error",bodyContent);
		return handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleBusinessException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }
	
}