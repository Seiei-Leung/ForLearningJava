package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.util.Base64;

/*
 *	非对称加密算法
 *	RSAkeyPair 这个类的实例就是一个公钥/私钥对，它拥有一个公钥和一个私钥
 *
 */

public class RSAkeyPair {

	// 公钥
	PublicKey publicKey;
	// 私钥
	PrivateKey privateKey;
	
	// 无参构造方法，生成公钥和私钥对
	public RSAkeyPair() throws NoSuchAlgorithmException {
		
		// 使用 KeyPariGenerator.getInstance 方法，传入算法 RSA
		KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
		
		// 表示输出的密钥长度是 1024 位
		kpGen.initialize(1024);
		
		// 获取密钥对
		KeyPair keyPair = kpGen.generateKeyPair();
		
		// 获取私匙
		this.privateKey = keyPair.getPrivate();
		// 获取公匙
		this.publicKey = keyPair.getPublic();
	}
	
	// 有参构造函数，通过传入的密钥对字节数组恢复密钥对（读取保存文件中的密钥对）
	public RSAkeyPair(byte[] sk, byte[] pk) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		// 恢复公钥，使用 X509EncodedKeySpec
		X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(pk);
		this.publicKey = keyFactory.generatePublic(pkSpec);
		
		// 恢复私钥，使用 PKCS8EncodedKeySpec
		PKCS8EncodedKeySpec skSpec = new PKCS8EncodedKeySpec(sk);
		this.privateKey = keyFactory.generatePrivate(skSpec);
		
	}
	
	// 把私钥导出为字节
	public byte[] getSk() {
		return this.privateKey.getEncoded();
	}
	
	// 把公钥导出为字节
	public byte[] getPk() {
		return this.publicKey.getEncoded();
	}
	
	// 使用公钥加密，使用了公匙加密就必须使用私匙解密，否则会报错
	public byte[] encrypt(byte[] message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		// 创建 Cipher 实例，声明使用 RSA
		Cipher cipher = Cipher.getInstance("RSA");
		
		// 使用公匙加密
		cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
		return cipher.doFinal(message);
	}
	
	// 使用私匙解密，使用了公匙加密就必须使用私匙解密，否则会报错
	public byte[] decrypt(byte[] input) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		// 创建 Cipher 实例，声明使用 RSA
		Cipher cipher = Cipher.getInstance("RSA");
		
		// 使用公匙加密
		cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
		return cipher.doFinal(input);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeySpecException {
			String message = "Hello.world!";
			RSAkeyPair keyPair = new RSAkeyPair();
			byte[] cipherText = keyPair.encrypt(message.getBytes("utf-8"));
			System.out.println("加密后使用Base64编码后的密文：" + Base64.getEncoder().encodeToString(cipherText));
			byte[] decryptText = keyPair.decrypt(cipherText);
			System.out.println("解密后的明文：" + new String(decryptText, "utf-8"));
			
			// 模仿读取文件密匙对字节，创建密匙对
			byte[] sk = keyPair.getSk();
			byte[] pk = keyPair.getPk();
			
			RSAkeyPair keyPair2 = new RSAkeyPair(sk, pk);
			System.out.println("使用字节流创建的私匙能否解密：" + new String(keyPair2.decrypt(cipherText), "utf-8"));
	}
}
