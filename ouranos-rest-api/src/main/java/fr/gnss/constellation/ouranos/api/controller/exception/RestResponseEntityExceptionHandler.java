package fr.gnss.constellation.ouranos.api.controller.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fr.gnss.constellation.ouranos.xsd.response.error.Error;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	public RestResponseEntityExceptionHandler() {
		super();
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setReasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase());
		error.setMessage("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(headers, request), HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		Error error = new Error();
		error.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
		error.setReasonPhrase(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
		error.setMessage(builder.toString());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(headers, request), HttpStatus.METHOD_NOT_ALLOWED, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

		Error error = new Error();
		error.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		error.setReasonPhrase(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
		error.setMessage(builder.toString());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(headers, request), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
	}

	// 400

	@Override
	protected @ResponseBody ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage(ex.getParameterName() + " parameter is missing");
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(headers, request), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage(StringUtils.join(errors, ", "));
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(headers, request), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage("This should be application specific");
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage("This should be application specific");
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		StringBuilder errorMessage = new StringBuilder();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errorMessage.append(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
		}

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage(errorMessage.toString());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setReasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase());
		error.setMessage(ex.getName() + " should be of type " + ex.getRequiredType().getName());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.BAD_REQUEST, request);
	}

	// 403

	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex, final WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.FORBIDDEN.value());
		error.setReasonPhrase(HttpStatus.FORBIDDEN.getReasonPhrase());
		error.setMessage("Access denied message request : " + request.getUserPrincipal());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.FORBIDDEN, request);
	}

	// 409

	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {

		Error error = new Error();
		error.setReasonPhrase(HttpStatus.CONFLICT.getReasonPhrase());
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setMessage("This should be application specific");
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.CONFLICT, request);
	}

	// 500

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleInternal(final Exception ex, final WebRequest request) {

		Error error = new Error();
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setReasonPhrase(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		error.setMessage(ex.getMessage());
		error.setStackTrace(ExceptionUtils.getFullStackTrace(ex));

		return handleExceptionInternal(ex, error, getHeader(null, request), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private HttpHeaders getHeader(HttpHeaders headers, WebRequest request) {

		if (headers == null) {
			headers = new HttpHeaders();
		}

		if (request.getHeaderValues(HttpHeaders.CONTENT_TYPE).length > 0) {
			String contentType = request.getHeaderValues(HttpHeaders.CONTENT_TYPE)[0];
			if (MediaType.APPLICATION_XML_VALUE.equals(contentType)) {
				headers.setContentType(MediaType.APPLICATION_XML);
			} else {
				headers.setContentType(MediaType.APPLICATION_JSON);
			}
		} else {
			headers.setContentType(MediaType.APPLICATION_JSON);
		}

		List<String> requestHeaderNames = new ArrayList<>();
		request.getHeaderNames().forEachRemaining(requestHeaderNames::add);

		List<String> requestHeaderNameNeeds = requestHeaderNames.stream().filter(headerName -> !StringUtils.equalsAnyIgnoreCase(headerName, HttpHeaders.HOST))
				.collect(Collectors.toList());

		for (String headerName : requestHeaderNameNeeds) {
			headers.set(headerName, request.getHeader(headerName));
		}

		return headers;
	}
}