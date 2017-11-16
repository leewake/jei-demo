package com.pangpang.jei.util;

import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

import com.pangpang.jei.util.GenericResponseEntity.GenericResponse;

/**
 * @author lijingwei
 *
 */
public class GenericResponseEntity<T> extends HttpEntity<GenericResponse<T>> {

	public GenericResponseEntity() {
		this(null);
	}
	
	public GenericResponseEntity(boolean success, String message, T body) {
		this(new GenericResponse<T>(success, message, body));
	}
	
	public GenericResponseEntity(boolean success, String message, T body, MultiValueMap<String, String> headers) {
		this(new GenericResponse<T>(success, message, body), headers);
	}
	
	public GenericResponseEntity(GenericResponse<T> body) {
		this(body, null);
	}
	
	public GenericResponseEntity(GenericResponse<T> body, MultiValueMap<String, String> headers) {
		super(body, headers);
	}
	
	public static GenericResponseBuilder success(boolean success) {
		return new GenericResponseBuilder(success);
	}
	
	public static GenericResponseBuilder fail() {
		return success(false);
	}
	
	public static GenericResponseBuilder ok() {
		return success(true);
	}
	
	public static <T> GenericResponseEntity<T> ok(T body) {
		return ok().body(body);
	}
	
	public static <T> GenericResponseEntity<T> fail(String message) {
		return fail().message(message).build();
	}
	
	public static class GenericResponseBuilder {
		private boolean success;
		private String message;
		public GenericResponseBuilder(boolean success) {
			this.success = success;
		}
		public GenericResponseBuilder message(String message) {
			this.message = message;
			return this;
		}
		public <T> GenericResponseEntity<T> build() {
			return body(null);
		}
		public <T> GenericResponseEntity<T> body(T body) {
			return new GenericResponseEntity<T>(new GenericResponse<T>(this.success, this.message, body));
		}
	}
	
	public static class GenericResponse<T> {
		private boolean success;
		private String message;
		private T body;
		public GenericResponse() { }
		public GenericResponse(boolean success) {
			this.success = success;
		}
		public GenericResponse(boolean success, String message, T body) {
			this.success = success;
			this.message = message;
			this.body = body;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public T getBody() {
			return body;
		}
		public void setBody(T body) {
			this.body = body;
		}
	}
	
}
