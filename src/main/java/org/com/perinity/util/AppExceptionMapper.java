package org.com.perinity.util;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Provider
@AllArgsConstructor
@Getter
@Setter
public class AppExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {
		return Response.status(exception.getResponse().getStatus()).entity(new ErrorMessage(exception.getMessage()))
				.type(MediaType.APPLICATION_JSON).build();
	}

	public static class ErrorMessage {
		private String message;

		public ErrorMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
