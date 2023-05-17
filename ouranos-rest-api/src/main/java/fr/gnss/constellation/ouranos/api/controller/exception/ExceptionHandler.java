package fr.gnss.constellation.ouranos.api.controller.exception;

import fr.gnss.constellation.ouranos.api.controller.exception.dto.ErrorDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ExceptionHandler implements ExceptionMapper<Exception> {

  @Override
  public Response toResponse(Exception e) {
    log.error(e.getMessage(), e);
    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .entity(new ErrorDto(Response.Status.INTERNAL_SERVER_ERROR.toString(), e.getMessage())).build();
  }
}
