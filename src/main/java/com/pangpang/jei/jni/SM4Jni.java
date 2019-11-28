package com.pangpang.jei.jni;

import java.io.*;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: call sm4 c++ code
 * @author: leewake
 * @create: 2019-11-21 16:00
 **/

public class SM4Jni {

    private static final int SM4_ENCRYPT = 0;
    private static final int SM4_DECRYPT = 1;

    private static final String pathPrefix = "/Users/lijingwei/sm/ubuntu/demo";

    //native声明，用于生成c/c++代码
    public native byte[] sm4Encry(byte[] data, byte[] key, byte[] iv, int mode);

    public native byte[] sm4Decry(byte[] data, byte[] key, byte[] iv, int mode);

    public static void encDataFromFile(String filePath, byte[] key, byte[] iv, int mode) throws Exception {
        if (mode == 0) {
            System.out.println("using ecb mode enc");
        } else if (mode == 1) {
            System.out.println("using cbc mode enc");
        } if (mode == 2) {
            System.out.println("using ctr mode enc");
        }

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String plainText = "";

        File writerName = new File(pathPrefix + "/cipher.txt");
        writerName.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writerName));

        while ((plainText =bReader.readLine()) != null) {
            if (!"".equals(plainText)) {
                String cipherText = sm4EncStr(plainText, key, iv, mode);
                out.write(cipherText + "\n"); // \r\n即为换行
                out.flush();
            }
        }
        bReader.close();
        out.close();
    }

    public static void decDataFromFile(String filePath, byte[] key, byte[] iv, int mode) throws Exception {
        if (mode == 0) {
            System.out.println("using ecb mode dec");
        } else if (mode == 1) {
            System.out.println("using cbc mode dec");
        } if (mode == 2) {
            System.out.println("using ctr mode dec");
        }

        File file = new File(filePath);
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        String cipher = "";

        File writerName = new File(pathPrefix + "/plainText.txt");
        writerName.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(writerName));

        while ((cipher =bReader.readLine()) != null) {
            if (!"".equals(cipher)) {
                String plainText = sm4DecStr(cipher, key, iv, mode);
                out.write(plainText + "\n"); // \r\n即为换行
                out.flush();
            }
        }
        bReader.close();
        out.close();
    }

    /**
     * <B>Description:</B> sm4加密接口 <br>
     * <B>Create on:</B> 2019/11/23 下午8:51 <br>
     * key与iv都是长度为16的byte数组,其中ecb模式下,用不到iv,只需要传一个空byte数组
     * mode代表加密模式,0:ecb; 1:cbc; 2:ctr
     * @author leewake
     */
    public static String sm4EncStr(String plainText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] cipherous = sm4Enc(plainText, key, iv, mode);

        //encode,不然输出字符串是乱码
        String cipherStr = new String(Base64.getEncoder().encodeToString(cipherous));
        if (cipherStr != null && cipherStr.trim().length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(cipherStr);
            cipherStr = m.replaceAll("");
        }
        return cipherStr;
    }

    public static byte[] sm4Enc(String plainText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] bytes = padding(plainText.getBytes(), SM4_ENCRYPT);
        int length = bytes.length;

        ByteArrayInputStream bins = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        for (; length > 0; length -= 16) {
            byte[] in = new byte[16];
            bins.read(in);
            byte[] ciphertext = new SM4Jni().sm4Encry(in, key, iv, mode);
            bous.write(ciphertext);
        }

        byte[] output = bous.toByteArray();
        bins.close();
        bous.close();

        return output;
    }

    /**
     * <B>Description:</B> sm4解密接口 <br>
     * <B>Create on:</B> 2019/11/23 下午9:06 <br>
     *
     * @author leewake
     */
    public static String sm4DecStr(String cipherText, byte[] key, byte[] iv, int mode) throws IOException {
        byte[] plainTextOus = sm4Dec(cipherText, key, iv, mode);
        String cipherStr = new String(plainTextOus);
        return cipherStr;
    }

    public static byte[] sm4Dec(String cipherText, byte[] key, byte[] iv, int mode) throws IOException {
        //使用Base64先decode
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        int length = bytes.length;

        ByteArrayInputStream bins = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream bous = new ByteArrayOutputStream();
        for (; length > 0; length -= 16) {
            byte[] in = new byte[16];
            bins.read(in);
            byte[] plainText = new SM4Jni().sm4Decry(in, key, iv, mode);
            bous.write(plainText);
        }

        byte[] output = bous.toByteArray();
        byte[] outputAfterPadding = padding(output, SM4_DECRYPT);
        bins.close();
        bous.close();

        return outputAfterPadding;
    }

    private static void printByteToStr(byte[] bytes, int length) {
        for (int i = 0; i < length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1){
                hex = '0' + hex;
            }
            System.out.print("0x" + hex + ",");
        }
        System.out.println();
    }

    /**
     * <B>Description:</B>加解密时不足128bit,填充 <br>
     *     mode : 1:加密, 0: 解密
     * <B>Create on:</B> 2019/11/23 下午8:58 <br>
     *
     * @author leewake
     */
    private static byte[] padding(byte[] input, int mode) {
        if (input == null) {
            return null;
        }

        byte[] ret = (byte[]) null;
        if (mode == SM4_ENCRYPT) {
            int p = 16 - input.length % 16;
            ret = new byte[input.length + p];
            System.arraycopy(input, 0, ret, 0, input.length);
            for (int i = 0; i < p; i++) {
                ret[input.length + i] = (byte) p;
            }
        } else {
            int p = input[input.length - 1];
            ret = new byte[input.length - p];
            System.arraycopy(input, 0, ret, 0, input.length - p);
        }
        return ret;
    }

}
