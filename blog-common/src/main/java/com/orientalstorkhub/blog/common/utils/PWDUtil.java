package com.orientalstorkhub.blog.common.utils;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class PWDUtil {
    private static final int SALT_LENGTH = 16; // 盐值长度，可以根据需要调整

    /**
     * 生成随机盐值。
     * 
     * @return 随机生成的盐值
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 使用SHA-256算法和盐值对密码进行加密。
     * 
     * @param password 明文密码
     * @param salt     盐值
     * @return 加密后的密码
     */
    public static String hashPassword(String password, String salt) {
        // 将盐值从字符串转换为字节数组
        byte[] saltBytes = Base64.getDecoder().decode(salt);
        byte[] saltedPassword = new byte[saltBytes.length + password.getBytes().length];
        System.arraycopy(saltBytes, 0, saltedPassword, 0, saltBytes.length);
        System.arraycopy(password.getBytes(), 0, saltedPassword, saltBytes.length, password.getBytes().length);

        byte[] hash = DigestUtils.sha256(saltedPassword);
        return Hex.encodeHexString(hash);
    }

    /**
     * 验证密码是否正确。
     * 
     * @param password   待验证的明文密码
     * @param storedHash 存储的加密后的密码
     * @param salt       存储的盐值
     * @return 如果密码正确则返回true，否则返回false
     */
    public static boolean verifyPassword(String password, String storedHash, String salt) {
        String hashOfInput = hashPassword(password, salt);
        return hashOfInput.equals(storedHash);
    }

}
