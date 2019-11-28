package com.pangpang.jei.controller;

import com.pangpang.jei.jni.SM4Jni;
import com.pangpang.jei.util.NativeLoader;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JniController {

    /**
     * <B>Description:</B>  <br>
     * <B>Create on:</B> 2019/11/28 下午1:54 <br>
     * http://localhost:8070/jei-demo/jni/sm4
     * @author leewake
     */
    @ApiOperation("测试native方法加载库")
    @GetMapping("/jni/sm4")
    public void gen() throws Exception {
        this.sm4Test();
    }

    public static void sm4Test() throws Exception {
        //加载c/c++编译好的库
        NativeLoader.loader("native");

        int mode = 0;
        String plainText = "test, I am encry some data using sm4 algorithm";
        System.out.println("输入的明文为:" + plainText);

        byte[] key = {(byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x10, (byte) 0x84, (byte) 0x29, (byte) 0x64, (byte) 0x50, (byte) 0xf8, (byte) 0x07, (byte) 0x00, (byte) 0x70};
        byte[] iv = {(byte)0x1f,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,
                (byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff,(byte)0x5e};

        System.out.println("-----ECB-----");
        String cipherText = SM4Jni.sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        String plainTextAfterEnc = SM4Jni.sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        System.out.println("-----CBC-----");
        mode = 1;
        cipherText = SM4Jni.sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        plainTextAfterEnc = SM4Jni.sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        System.out.println("-----CTR-----");
        mode = 2;
        cipherText = SM4Jni.sm4EncStr(plainText, key, iv, mode);
        System.out.println("加密后密文为:" + cipherText);
        plainTextAfterEnc = SM4Jni.sm4DecStr(cipherText, key, iv, mode);
        System.out.println("解密后明文为:" + plainTextAfterEnc);

        /*System.out.println("开始加密文件数据");
        encDataFromFile(pathPrefix + "news.txt", key, iv, mode);
        System.out.println("开始解密数据");
        decDataFromFile(pathPrefix + "cipher.txt", key, iv, mode);*/

    }


}
