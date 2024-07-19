package com.cosplaystore.cosplaystore.exception;

import org.springframework.http.HttpStatus;

import com.cosplaystore.cosplaystore.dto.response.Message;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private HttpStatus status;

    public GeneralException(Message message) {
        super(message.getMessage());
        this.status = message.getStatus();
    }

}
