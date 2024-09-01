package com.cosplaystore.cosplaystore.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Message {
    PRODUCT_NOT_FOUND("Not found product", HttpStatus.BAD_REQUEST),
    CATETORY_NOT_FOUND("Not found catetory", HttpStatus.BAD_REQUEST),
    FIELD_ERROR("FIeld error", HttpStatus.BAD_REQUEST),
    CATETORY_LIST_FAILED("Failed to get catetory list", HttpStatus.BAD_REQUEST),
    CATETORY_EXISTED("Catetory already existed", HttpStatus.BAD_REQUEST),
    PRODUCT_ADD_FAILED("Failed to add product", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD("Wrong password!", HttpStatus.BAD_REQUEST),
    AUTHENTICATION_FAILED("Authentication failed", HttpStatus.UNAUTHORIZED),
    USERNAME_NOTFOUND("Not found username!", HttpStatus.BAD_REQUEST),
    ACCESS_DENY("You don't have right to access this!", HttpStatus.FORBIDDEN),
    INVALID_TOKEN("Your token is invalid!", HttpStatus.UNAUTHORIZED),
    USER_BANNED("Your account is banned!", HttpStatus.BAD_REQUEST),
    DATA_VIOLATION("Data vialation", HttpStatus.BAD_REQUEST);

    private HttpStatus status;
    private String message;

    private Message(String message, HttpStatus status) {
        this.message = message;
        this.status = status;

    }
}
