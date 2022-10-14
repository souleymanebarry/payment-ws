package com.upstreampay.payment.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Slf4j
public class ChangeStatusException extends RuntimeException{

    public ChangeStatusException(String message) {
        super(message);
        log.error(message);
    }
}
