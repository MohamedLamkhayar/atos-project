package com.example.usermongodb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    /**
     * throw exception in case of no resource was found
     * @param message
     */
    public ResourceNotFoundException(String message){
        super(message);
    }
}
