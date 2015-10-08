package com.nibbledebt.nibble.security;

/**
 * Created by ralam on 10/7/15.
 */
public interface SessionObject<T> {
    T getData(String key);
}
