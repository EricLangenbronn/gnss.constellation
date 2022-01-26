package fr.gnss.constellation.ouranos.api.controller.exception;

import lombok.Data;

@Data
public class ErrorDto {

    protected Integer status;
    protected String reasonPhrase;
    protected String message;
    protected String technicalMessage;
    protected String stackTrace;
}
