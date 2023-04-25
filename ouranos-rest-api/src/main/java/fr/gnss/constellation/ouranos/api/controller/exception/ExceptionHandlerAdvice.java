package fr.gnss.constellation.ouranos.api.controller.exception;


import fr.gnss.constellation.ouranos.api.controller.exception.dto.BadRequestDto;
import fr.gnss.constellation.ouranos.api.controller.exception.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

  @ExceptionHandler(value = {IllegalArgumentException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public BadRequestDto handleIllegalArgumentException(IllegalArgumentException e) {
    log.warn(e.getLocalizedMessage(), e);
    return new BadRequestDto(e.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BadRequestDto handleConstraintViolationException(ConstraintViolationException e) {
    log.warn(StringUtils.join(e.getConstraintViolations(), ", ", e));
    return new BadRequestDto(e.getMessage(), e.getConstraintViolations());
  }

  @ExceptionHandler(value = UnsupportedOperationException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ErrorDto handleUnsupportedOperationException(UnsupportedOperationException e) {
    return new ErrorDto(HttpStatus.FORBIDDEN.toString(), e.getMessage());
  }

  @ExceptionHandler(value = {Exception.class})
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorDto handleError(Exception e) {
    log.error(e.getMessage(), e);
    return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
  }
}
