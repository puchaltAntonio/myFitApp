package com.learning.myfitapp.common.web.error;

import com.learning.myfitapp.common.domain.exception.DomainValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleDomainValidationException(
            final DomainValidationException ex
    ) {

        List<ApiParamErrorField> errorFields = ex.getErrors().stream()
                .map(
                        error -> new ApiParamErrorField(
                                error.getField(), error.getMessage()
                        )
                )
                .toList();

        ApiErrorResponse response = new ApiErrorResponse(
                "VALIDATION_ERROR",
                "One or more fields are not valid.",
                errorFields
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
