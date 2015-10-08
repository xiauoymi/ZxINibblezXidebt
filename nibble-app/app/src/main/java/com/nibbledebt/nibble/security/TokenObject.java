package com.nibbledebt.nibble.security;

/**
 * Created by ralam on 10/7/15.
 */
public class TokenObject implements SessionObject<String>{

    private String token;

    public TokenObject(String token){
        this.token = token;
    }


    @Override
    public String getData(String key) {
        return null;
    }
}
