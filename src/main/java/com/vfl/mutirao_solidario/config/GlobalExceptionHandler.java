package com.vfl.mutirao_solidario.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
@Configuration
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handleException(MethodArgumentNotValidException e) {
        List<ErrorModel> errorModels = processErrors(e);
        return ErrorResponseModel
                .builder()
                .errors(errorModels)
                .type("VALIDATION")
                .build();
    }

    private List<ErrorModel> processErrors(MethodArgumentNotValidException e) {
        List<ErrorModel> validationErrorModels = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            ErrorModel validationErrorModel = ErrorModel
                    .builder()
                    .code(fieldError.getCode())
                    .source(fieldError.getObjectName() + "/" + fieldError.getField())
                    .detail(fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .build();
            validationErrorModels.add(validationErrorModel);
        }
        return validationErrorModels;
    }

}
