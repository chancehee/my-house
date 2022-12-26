package com.ssafy.home.util;

public interface EncryptUtil {
    public String makeSalt() throws Exception;
    public String hashing(byte[] password, String salt) throws Exception;
}
