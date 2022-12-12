//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.whu.learneur.elasticsearch.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class TransportUtil {
    public TransportUtil() {
    }

    public static SSLContext sslContextFromHttpCaCrt(File file) throws IOException {
        InputStream in = Files.newInputStream(file.toPath());

        SSLContext var2;
        try {
            var2 = sslContextFromHttpCaCrt((InputStream)in);
        } catch (Throwable var5) {
            try {
                in.close();
            } catch (Throwable var4) {
                var5.addSuppressed(var4);
            }

            throw var5;
        }

        in.close();
        return var2;
    }

    public static SSLContext sslContextFromHttpCaCrt(InputStream in) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            Certificate certificate = cf.generateCertificate(in);
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load((InputStream)null, (char[])null);
            keyStore.setCertificateEntry("elasticsearch-ca", certificate);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init((KeyManager[])null, tmf.getTrustManagers(), (SecureRandom)null);
            return sslContext;
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | IOException | CertificateException var6) {
            throw new RuntimeException(var6);
        }
    }

    public static SSLContext sslContextFromCaFingerprint(String fingerPrint) {
        fingerPrint = fingerPrint.replace(":", "");
        int len = fingerPrint.length();
        final byte[] fpBytes = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            fpBytes[i / 2] = (byte)((Character.digit(fingerPrint.charAt(i), 16) << 4) + Character.digit(fingerPrint.charAt(i + 1), 16));
        }

        try {
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    throw new CertificateException("This is a client-side only trust manager");
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    X509Certificate anchor = chain[chain.length - 1];

                    byte[] bytes;
                    try {
                        MessageDigest md = MessageDigest.getInstance("SHA-256");
                        md.update(anchor.getEncoded());
                        bytes = md.digest();
                    } catch (NoSuchAlgorithmException var6) {
                        throw new RuntimeException(var6);
                    }

                    if (!Arrays.equals(fpBytes, bytes)) {
                        throw new CertificateException("Untrusted certificate: " + anchor.getSubjectX500Principal());
                    }
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init((KeyManager[])null, new X509TrustManager[]{tm}, (SecureRandom)null);
            return sslContext;
        } catch (KeyManagementException | NoSuchAlgorithmException var5) {
            throw new RuntimeException(var5);
        }
    }
}
