package fr.gnss.constellation.ouranos.api.controller.exception;

import fr.gnss.constellation.ouranos.api.controller.exception.dto.BadRequestDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class IllegalArgumentExceptionHandler implements ExceptionMapper<IllegalArgumentException> {

  @Override
  public Response toResponse(IllegalArgumentException e) {
    log.warn(e.getLocalizedMessage(), e);
    return Response.status(Response.Status.BAD_REQUEST).entity(new BadRequestDto(e.getMessage())).build();
  }
}