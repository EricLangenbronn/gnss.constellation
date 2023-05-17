package fr.gnss.constellation.ouranos.api.controller.exception;

import fr.gnss.constellation.ouranos.api.controller.exception.dto.ErrorDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class UnsupportedOperationExceptionHandler implements ExceptionMapper<UnsupportedOperationException> {

  @Override
  public Response toResponse(UnsupportedOperationException e) {
    return Response.status(Response.Status.FORBIDDEN).entity(new ErrorDto(Response.Status.FORBIDDEN.toString(), e.getMessage())).build();
  }
}
