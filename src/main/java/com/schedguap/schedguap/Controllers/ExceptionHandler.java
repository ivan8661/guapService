package com.schedguap.schedguap.Controllers;

import com.schedguap.schedguap.Exceptions.ErrorResult;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    protected ResponseEntity<ErrorResult> handleUserException(UserException ex) {
        return new ResponseEntity<>(new ErrorResult(ex), ex.getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResult> handleFoundException(NoHandlerFoundException exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServerErrorException.class)
    protected ResponseEntity<ErrorResult> handleInternalErrorException(ServerErrorException exception) {
        UserException ex = new UserException(UserExceptionType.SERVER_ERROR);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchFieldException.class)
    protected ResponseEntity<ErrorResult> handleNoSuchFieldException(NoSuchFieldException exception) {
        UserException ex = new UserException(UserExceptionType.OBJECT_NOT_FOUND);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.BadRequest.class)
    protected ResponseEntity<ErrorResult> handleBadRequestException(HttpClientErrorException exception) {
        UserException ex = new UserException(UserExceptionType.BAD_REQUEST);
        return handleUserException(ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResult> handleOtherException(Exception exception) {
        UserException ex = new UserException(UserExceptionType.SERVER_ERROR);
        return handleUserException(ex);
    }
}
