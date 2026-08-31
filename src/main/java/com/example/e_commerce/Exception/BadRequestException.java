package com.example.e_commerce.Exception;

public class BadRequestException extends RuntimeException {

   public BadRequestException (String message){
       super(message);
   }
}
