package fr.gnss.constellation.ouranos.api.controller.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ErrorDto {

    private String errorCode;
    private String response;

}
