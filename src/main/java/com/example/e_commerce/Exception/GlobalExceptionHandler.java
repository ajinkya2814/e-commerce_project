package com.example.e_commerce.Exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.RoleNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public String handelBadReq (BadRequestException e){
        return e.getMessage();
    }

    @ExceptionHandler(ForbirddenException.class)
    public String handeForBirddenException(ForbirddenException e){
        return e.getMessage();
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public String handelResourceAlreadyExist(ResourceAlreadyExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public String handelUnAuth (UnAuthorizedException e){
        return e.getMessage();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handelResourceNotFound (ResourceNotFoundException e){
        return e.getMessage();
    }
}
