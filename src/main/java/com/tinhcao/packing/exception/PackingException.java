package com.tinhcao.packing.exception;

/**
 * @author tinhcao
 * 1/27/21
 * 11:57 PM
 * Custom exception class
 **/
public class PackingException extends RuntimeException {
    public PackingException(String message) {
        super(message);
    }
}
