package com.illtamer.sillage.rear.controller;

import com.illtamer.sillage.rear.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.SQLException;

/**
 * 全局异常处理类
 * */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response sqlException(SQLException e) {
        log.error("SQL异常", e);
        return Response.error("SQL执行错误");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response runtimeException(RuntimeException e) {
        log.error("Unknown runtime exception", e);
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response httpStatusException(NoHandlerFoundException e) {
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response doException(Exception e) {
        log.error("Exception", e);
        return Response.error(e.getMessage());
    }

}
