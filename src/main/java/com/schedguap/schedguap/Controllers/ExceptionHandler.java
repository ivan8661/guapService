package com.schedguap.schedguap.Controllers;

import com.schedguap.schedguap.Exceptions.ErrorResult;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResult> handleUserException(UserException us) {
        if(us.getCode().equals("403"))
            return new ResponseEntity<>(new ErrorResult(us.getId(), us.getCode(), us.getMessage(), us.getData()), HttpStatus.FORBIDDEN);
        if(us.getCode().equals("404"))
            return new ResponseEntity<>(new ErrorResult(us.getId(), us.getCode(), us.getMessage(), us.getData()), HttpStatus.NOT_FOUND);
        if(us.getCode().equals("406"))
            return new ResponseEntity<>(new ErrorResult(us.getId(), us.getCode(), us.getMessage(), us.getData()), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(new ErrorResult(us.getId(), us.getCode(), us.getMessage(), us.getData()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
