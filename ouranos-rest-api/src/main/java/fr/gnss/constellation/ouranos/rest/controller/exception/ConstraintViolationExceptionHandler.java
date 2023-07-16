package fr.gnss.constellation.ouranos.rest.controller.exception;

import fr.gnss.constellation.ouranos.rest.controller.exception.dto.BadRequestDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


@Provider
@Slf4j
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(ConstraintViolationException e) {
    log.warn(StringUtils.join(e.getConstraintViolations(), ", ", e));
    return Response.status(Response.Status.BAD_REQUEST).entity(new BadRequestDto(e.getMessage(), e.getConstraintViolations())).build();
  }
}
