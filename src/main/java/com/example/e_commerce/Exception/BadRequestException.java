package com.example.e_commerce.Exception;

public class BadRequestException extends org.apache.coyote.BadRequestException {

   public BadRequestException (String message){
       super(message);
   }
}
