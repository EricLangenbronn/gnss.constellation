package fr.gnss.constellation.ouranos.api.controller.exception.dto;

import jakarta.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class BadRequestDto extends ErrorDto {

  private static final String BAD_REQUEST_ERROR_CODE = "BAD_REQUEST";
  private List<String> reasonsForError = new ArrayList<>();

  public BadRequestDto(String message) {
    super(BAD_REQUEST_ERROR_CODE, message);
  }

  public BadRequestDto(String message, Set<ConstraintViolation<?>> constraintViolations) {
    super(BAD_REQUEST_ERROR_CODE, message);
    this.reasonsForError = constraintViolations.stream()
        .map(constraintViolation ->
            new StringBuilder().append(constraintViolation.getMessageTemplate())
                .append(" : ")
                .append(constraintViolation.getPropertyPath())
                .append(" : ")
                .append(constraintViolation.getMessage())
                .toString()
        ).collect(Collectors.toList());
  }
}