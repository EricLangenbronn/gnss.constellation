package fr.gnss.constellation.ouranos.api.controller.exception.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        this.reasonsForError = constraintViolations.stream().map(cV ->
                new StringBuilder().append(cV.getMessageTemplate())
                        .append(" : ")
                        .append(cV.getPropertyPath())
                        .append(" : ")
                        .append(cV.getMessage())
                        .toString()
        ).collect(Collectors.toList());
    }
}