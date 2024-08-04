package com.vfl.mutirao_solidario.config;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
@Configuration
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseModel handleException(NotFoundException e) {
        e.printStackTrace();
        return ErrorResponseModel
                .builder()
                .errors(List.of(ErrorModel.builder().detail(e.getMessage() + " não encontrado").build()))
                .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handleException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<ErrorModel> errorModels = processErrors(e);
        return ErrorResponseModel
                .builder()
                .errors(errorModels)
                .build();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handleException(IllegalArgumentException e) {
        e.printStackTrace();
        ErrorModel errorModel = ErrorModel.builder().detail(e.getMessage()).build();
        return ErrorResponseModel
                .builder()
                .errors(List.of(errorModel))
                .build();
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseModel handleException(BadCredentialsException e) {
        e.printStackTrace();
        ErrorModel errorModel = ErrorModel.builder().detail(e.getMessage()).build();
        return ErrorResponseModel
                .builder()
                .errors(List.of(errorModel))
                .build();
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponseModel handleException(ExpiredJwtException e) {
        e.printStackTrace();
        ErrorModel errorModel = ErrorModel.builder().detail("Sessão expirada").build();
        return ErrorResponseModel
                .builder()
                .errors(List.of(errorModel))
                .build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseModel handleException(Exception e) {
        e.printStackTrace();
        ErrorModel errorModel = ErrorModel.builder().detail(e.getMessage()).build();
        return ErrorResponseModel
                .builder()
                .errors(List.of(errorModel))
                .build();
    }

    private List<ErrorModel> processErrors(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<ErrorModel> validationErrorModels = new ArrayList<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            ErrorModel validationErrorModel = ErrorModel
                    .builder()
                    .field(fieldError.getField())
                    .detail(fieldError.getDefaultMessage())
                    .build();
            validationErrorModels.add(validationErrorModel);
        }
        return validationErrorModels;
    }

}
