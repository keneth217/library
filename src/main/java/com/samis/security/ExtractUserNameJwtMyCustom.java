package com.samis.security;

public class ExtractUserNameJwtMyCustom {
    public static String extractUsername(String token)
    {
        final String jwt;
        final String userName;
        final String authHeader =token;
        jwt=authHeader.substring(7);
        userName= JwtService.extractUsername(jwt);
        return  userName;
    }



}
