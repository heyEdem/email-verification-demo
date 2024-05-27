package com.edem.emailverificationdemo.Exception;


public class UserAlreadyExistsException extends RuntimeException{

   public UserAlreadyExistsException(String message){
       super(message);
   }
}
