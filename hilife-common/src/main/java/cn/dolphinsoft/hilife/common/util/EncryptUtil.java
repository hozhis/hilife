package cn.dolphinsoft.hilife.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import cn.dolphinsoft.hilife.common.constant.ApplicationConstant;
import cn.dolphinsoft.hilife.common.exception.HiLifeException;

/**
 * Class Name: EncryptUtil Description: 加密工具类
 * 
 * @author hozhis
 *
 */
public class EncryptUtil {
    /**
     * 描述: MD5 加密
     * 
     * @param input
     * @param salt
     * @return
     */
    public static String encryptMd5(String input, String salt) {
        input = input.toLowerCase();
        try {
            int middle = input.length() / 2;
            byte[] result = MessageDigest.getInstance("MD5")
                    .digest((input.substring(0, middle) + salt + input.substring(middle)).getBytes());
            StringBuilder strBuilder = new StringBuilder(result.length * 2);
            for (byte b : result) {
                String s = Integer.toHexString(b & 0x00FF);
                if (1 == s.length()) {
                    strBuilder.append('0');
                }
                strBuilder.append(s);
            }
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new HiLifeException(e);
        }
    }

    /**
     * 描述：密码一次加密
     */
    public static String encryptMd5(String input) {
        try {
            byte[] result = MessageDigest.getInstance("MD5").digest(input.getBytes(ApplicationConstant.ENCODING));
            StringBuilder strBuilder = new StringBuilder(result.length * 2);
            for (byte b : result) {
                String s = Integer.toHexString(b & 0x00FF);
                if (1 == s.length()) {
                    strBuilder.append('0');
                }
                strBuilder.append(s);
            }
            return strBuilder.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new HiLifeException(e);
        }
    }

    /**
     * AES加密
     * 
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) {
        KeyGenerator kgen;
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(encryptKey.getBytes());
            kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
            return cipher.doFinal(content.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES解密
     * 
     * @param encryptBytes
     *            待解密的byte[]
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(decryptKey.getBytes());

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
            byte[] decryptBytes = cipher.doFinal(encryptBytes);
            return new String(decryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换成16进制
     * 
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * 
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * AES加密
     * 
     * @param content
     *            待加密的内容
     * @param encryptKey
     *            加密密钥
     * @return 加密后的byte[]
     * @throws NoSuchAlgorithmException
     */
    public static String aesEncrypt(String content, String encryptKey) {
        return parseByte2HexStr(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * 
     * @param encryptStr
     *            待解密的String
     * @param decryptKey
     *            解密密钥
     * @return 解密后的String
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) {
        return aesDecryptByBytes(parseHexStr2Byte(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(aesEncrypt("166", "123"));
    }

}
