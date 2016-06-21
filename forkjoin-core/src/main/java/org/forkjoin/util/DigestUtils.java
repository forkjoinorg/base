package org.forkjoin.util;

import org.apache.commons.codec.binary.Base64;

/**
 * @author qiang on 2015/9/8.
 *         jinliqiang@ihbaby.com
 */
public class DigestUtils {
    private static final String salt = "p3XnKm";
    private static final String password="123456";

    public static String digest(String pwd) {
        return Base64.encodeBase64String(
                org.apache.commons.codec.digest.DigestUtils.sha256(salt + pwd)
        );
    }

    public static String digestSha1(String pwd) {
        return org.apache.commons.codec.digest.DigestUtils.sha1Hex(pwd);
    }

    public static String getDefalutPassword(){
        return Base64.encodeBase64String(
                org.apache.commons.codec.digest.DigestUtils.sha256(salt + password)
        );
    }
    //sha1('password')
}
