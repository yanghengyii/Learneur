package edu.whu.learneur.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.text.StringEscapeUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码解码工具类
 */
public class EncodesUtils {
    private static final String DEFAULT_URL_ENCODE = "UTF-8";
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * hex编码: 转换为十六进制
     * @param input 输入
     * @return
     */
    public static String encodeHex(byte[] input) {
        return new String(Hex.encodeHex(input));
    }

    /**
     * hex解码
     * @param input 输入
     * @return
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw ExceptionsUtils.unchecked(e);
        }
    }

    /**
     * base64编码
     * @param input 输入
     * @return
     */
    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    /**
     * base64编码
     * @param input 输入
     * @return
     */
    public static String encodeBase64String(String input) {
        try {
            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODE)));
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * base64编码, URL安全
     * @param input 输入
     * @return
     */
    public static String encodeUrlSafeBase64(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * base64解码
     * @param input 输入
     * @return
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }

    /**
     * base64解码
     * @param input 输入
     * @return
     */
    public static String decodeBase64String(String input) {
        try {
            return new String(Base64.decodeBase64(input.getBytes()), DEFAULT_URL_ENCODE);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * base62编码
     * @param input 输入
     * @return
     */
    public static String encodeBase62(byte[] input) {
        char[] chars = new char[input.length];
        for(int i = 0; i < input.length; ++i) {
            chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
        }
        return new String(chars);
    }

    /**
     * url编码, 默认为UTF-8
     * @param url   url
     * @return
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, DEFAULT_URL_ENCODE);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionsUtils.unchecked(e);
        }
    }

    /**
     * url解码, 默认为UTF-8
     * @param url   url
     * @return
     */
    public static String decodeUrl(String url) {
        try {
            return URLDecoder.decode(url, DEFAULT_URL_ENCODE);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionsUtils.unchecked(e);
        }
    }

    /**
     * HTML转码
     * @param html  html
     * @return
     */
    public static String escapeHTML(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * HTML解码
     * @param htmlEscaped  escaped html
     * @return
     */
    public static String unescapeHTML(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    /**
     * xml转码
     * @param xml   xml
     * @return
     */
    public static String escapeXML(String xml) {
        return StringEscapeUtils.escapeXml10(xml);
    }

    /**
     * xml转码
     * @param xmlEscaped    xml
     * @return
     */
    public static String unescapeXML(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }
}
