package com.example.e_commerce.Exception;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException (String message){
        super(message);
    }
}
