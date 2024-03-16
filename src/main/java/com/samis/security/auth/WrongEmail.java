package com.samis.security.auth;

public class WrongEmail extends  RuntimeException{
    public  WrongEmail(String message){
        super(message);
    }
}
