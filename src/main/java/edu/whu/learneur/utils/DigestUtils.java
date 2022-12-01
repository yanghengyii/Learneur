package edu.whu.learneur.utils;

import org.apache.commons.lang3.Validate;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * 支持SHA-1/MD5消息摘要工具类
 *
 * 返回ByteSource, 可以进一步编码为Hex, Base64和UrlSafeBase64
 */
public class DigestUtils {
    private static final String SHA1 = "SHA-1";
    private static final String MD5 = "MD5";
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * 对字符串进行散列, 支持md5与sha1算法
     * @param input         输入
     * @param algorithm     算法
     * @param salt          盐
     * @param iterations    迭代次数
     * @return
     */
    public static byte[] digest(
            byte[] input, String algorithm, byte[] salt, int iterations
    ) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            if(Objects.nonNull(salt)) {
                digest.update(salt);
            }

            byte[] result = digest.digest(input);

            for (int i = 0; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw ExceptionsUtils.unchecked(e);
        }
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法
     * @param input         输入
     * @param algorithm     算法
     * @return
     */
    public static byte[] digest(
            InputStream input, String algorithm
    ) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            int bufferLength = 8 * 1024;
            byte[] buffer = new byte[bufferLength];
            int read = input.read(buffer, 0, bufferLength);
            while (read > -1) {
                digest.update(buffer, 0, read);
                read = input.read(buffer, 0, bufferLength);
            }

            return digest.digest();
        } catch (GeneralSecurityException e) {
            throw ExceptionsUtils.unchecked(e);
        }
    }

    /**
     * 生成随机的byte[]作为salt
     * @param numBytes  numBytes.length
     * @return
     */
    public static byte[] generateSalt(int numBytes) {
        Validate.isTrue(numBytes > 0,
                "numBytes argument must be a positive integer (1 or larger)", numBytes);
        byte[] bytes = new byte[numBytes];
        SECURE_RANDOM.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对文件进行md5散列
     * @param inputStream   输入流
     * @return
     * @throws IOException
     */
    public static byte[] md5(InputStream inputStream) throws IOException {
        return digest(inputStream, MD5);
    }

    /**
     * 对文件进行sha1散列
     * @param inputStream   输入流
     * @return
     * @throws IOException
     */
    public static byte[] sha1(InputStream inputStream) throws IOException {
        return digest(inputStream, SHA1);
    }

    /**
     * 对文件进行md5散列
     * @param input 输入
     * @return
     */
    public static byte[] md5(byte[] input) {
        return digest(input, MD5, null, 1);
    }

    /**
     * 对文件进行md5散列
     * @param input         输入
     * @param iterations    迭代次数
     * @return
     */
    public static byte[] md5(byte[] input, int iterations) {
        return digest(input, MD5, null, iterations);
    }

    /**
     * 对文件进行sha5散列
     * @param input 输入
     * @return
     */
    public static byte[] sha1(byte[] input) {
        return digest(input,SHA1, null, 1);
    }

    /**
     * 对文件进行sha5散列
     * @param input 输入
     * @param salt  盐
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, SHA1, salt, 1);
    }

    /**
     * 对文件进行sha5散列
     * @param input         输入
     * @param salt          盐
     * @param iterations    迭代次数
     * @return
     */
    public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
        return digest(input, SHA1, salt, iterations);
    }
}
