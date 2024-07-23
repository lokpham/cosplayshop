package com.cosplaystore.cosplaystore.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cosplaystore.cosplaystore.dto.response.ResponseObject;

@RestControllerAdvice
public class GlobalException {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ResponseObject> handleGeneralException(Exception e) {
                return ResponseEntity.badRequest()
                                .body(ResponseObject.builder()
                                                .message(e.getMessage())
                                                .status_code(HttpStatus.BAD_REQUEST.value())
                                                .data(null).cause(e.getClass())
                                                .build());
        };

        @ExceptionHandler(GeneralException.class)
        public ResponseEntity<ResponseObject> handleResponse(GeneralException e) {

                return ResponseEntity.badRequest()
                                .body(ResponseObject.builder()
                                                .message(e.getMessage()).status_code(e.getStatus().value())
                                                .data(null).cause(e.getClass())
                                                .build());
        };

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ResponseObject> handleValidation(MethodArgumentNotValidException e) {
                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .status_code(HttpStatus.BAD_REQUEST.value())
                                                .message(e.getFieldError().getField() + ":"
                                                                + e.getFieldError().getDefaultMessage())
                                                .data(null).cause(e.getClass())
                                                .build());
        };

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ResponseObject> handleDataViolation(DataIntegrityViolationException e) {
                if (e.getMostSpecificCause().getMessage().contains("Duplicate")) {
                        String field = e.getMostSpecificCause().getMessage().split("key")[1];
                        int position = field.indexOf("'");
                        field = field.substring(position + 1, field.length() - 1);
                        field = field.substring(0, 1).toUpperCase() + field.substring(1).toLowerCase();

                        return ResponseEntity.ok()
                                        .body(ResponseObject.builder()
                                                        .status_code(HttpStatus.BAD_REQUEST.value())
                                                        .message(field + " already existed!")
                                                        .data(null).cause(e.getClass())
                                                        .build());
                }

                return ResponseEntity.ok()
                                .body(ResponseObject.builder()
                                                .status_code(HttpStatus.BAD_REQUEST.value())
                                                .message(e.getMostSpecificCause().getMessage())
                                                .data(null).cause(e.getClass())
                                                .build());
        };

}
