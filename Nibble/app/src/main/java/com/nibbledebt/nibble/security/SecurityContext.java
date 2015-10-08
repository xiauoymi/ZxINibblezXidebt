package com.nibbledebt.nibble.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ralam on 10/2/15.
 */
public class SecurityContext {

    private static SecurityContext context = new SecurityContext();

    private String cookie;
    private Map<String, SessionObject<?>> sessionMap;

    private SecurityContext(){}

    public static SecurityContext getCurrentContext(){
        return context;
    }

    public boolean isLoggedIn(){
        return this.cookie==null ? false : true;
    }

    public String getCookie(){
        return this.cookie;
    }

    public void setCookie(String cookie){
        this.cookie = cookie;
    }

    public Map<String, SessionObject<?>> getSessionMap() {
        if(sessionMap == null) sessionMap = new HashMap<>();
        return sessionMap;
    }

    public void setSessionMap(Map<String, SessionObject<?>> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
