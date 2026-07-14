package com.example.e_commerce.Exception;

public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException (String message){
        super(message);
    }
}
