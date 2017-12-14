package com.example.coderqiang.xmatch_android.util.jwt;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by coderqiang on 2017/12/1.
 */

public class RSAUtil {

    private static RSAPublicKey publicKey = null;
    private static final String RSA_PUBLIC=
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDnjMicOB/Y2Ug7o5SjFFJIM7Ie\n" +
            "s3p+HfL/Wx25w1d5z97+o7CQkKHaxHURRPohtLc5jJ1tfvsLo4sejpW6OtBk6JvY\n" +
            "UFRdXBydpMUmV4DovXzBqi8YIGbdUo6gTycHCRRh9nyLir4M3rSmpbtxurDrhawa\n" +
            "3snAmiesO7iZtrwcswIDAQAB";

    public static void loadPublicKey() {
        try {
            byte[] buffer = Base64.decode(RSA_PUBLIC, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公钥加密过程
     *
     *            公钥
     * @param plainData
     *            明文数据
     * @return
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encryptWithRSA(String plainData)  {
        if (publicKey == null) {
            loadPublicKey();
        }
        try {
            Cipher cipher = null;
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");// 此处如果写成"RSA"加密出来的信息JAVA服务器无法解析
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainData.getBytes("utf-8"));
            return Base64.encodeToString(output, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptWithRSA(String encryedData) throws Exception {
        if (publicKey == null) {
            throw new NullPointerException("decrypt PublicKey is null !");
        }

        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");// 此处如果写成"RSA"解析的数据前多出来些乱码
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(Base64.decode(encryedData, Base64.DEFAULT));
        return new String(output);
    }

}
