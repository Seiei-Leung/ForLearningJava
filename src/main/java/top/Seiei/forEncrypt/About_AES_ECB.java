package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/*
 *	cipher: 密码，加密
 *	spec: 投机；说明书；细则
 *	
 */


public class About_AES_ECB {

	// 使用 AES 算法，指定工作模式为 ECB，填充模式为 PKCS5Padding
	static final String CIPHER_NAME = "AES/ECB/PKCS5Padding"; 
	
	// 加密
	public static byte[] encrypt(byte[] secretKey, byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		// 使用 Cipher.getInstance() 方法，传入加密算法的名字，工作模式以及填充模式，从而得到一个 Cipher 实例
		Cipher cipher = Cipher.getInstance(CIPHER_NAME);
		
		// 使用传入的密匙创建 AES 的 SecretKeySpec 实例
		SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
		
		// cipher 使用 init 方法初始化为加密模式，并传入密匙
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		
		// 最后使用 doFinal 传入明文，得到加密后的密文的字节数组
		return cipher.doFinal(input);
	}
	
	// 解密，仅需要在初始化模式时，初始为 解密模式即可
	public static byte[] decrypt(byte[] secretKey, byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		// 使用 Cipher.getInstance() 方法，传入加密算法的名字，工作模式以及填充模式，从而得到一个 Cipher 实例
		Cipher cipher = Cipher.getInstance(CIPHER_NAME);
		
		// 使用传入的密匙创建 AES 的 SecretKeySpec 实例
		SecretKeySpec keySpec = new SecretKeySpec(secretKey, "AES");
		
		// cipher 使用 init 方法初始化为解密模式，并传入密匙
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		
		// 最后使用 doFinal 传入明文，得到加密后的密文的字节数组
		return cipher.doFinal(input);
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// 明文
		String input = "Hello.world";
		// abs 的密匙长度最短是 128 位，即需要 16 个字符
		String secretKey = "1234567890abcdef";

		System.out.println("加密明文是：" + input + "，秘钥是：" + secretKey);
		
		// 加密, 注意传入参数类型是字节数组，注意 utf-8 编码
		byte[] cipherText = About_AES_ECB.encrypt(secretKey.getBytes("utf-8"),input.getBytes("utf-8"));
		
		// 用 base64 输出密文字节数组
 		System.out.println("base64编码过后的密文：" + Base64.getEncoder().encodeToString(cipherText));
		
		// 解码
		String decryptString = new String(About_AES_ECB.decrypt(secretKey.getBytes("utf-8"), cipherText), "utf-8");
		System.out.println("解码：" + decryptString);
	}

}
