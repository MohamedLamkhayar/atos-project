package com.example.usermongodb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {

    /**  the moment of the throwing exception  */
    private Date timestamp;
    /**  the description of the throwing exception */
    private String message;
}
