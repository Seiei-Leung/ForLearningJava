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
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/*
 *	使用 DH 算法
 *	JDK1.8 可能会报错，需要配置JVM的系统变量，在 eclipse 中的 run Configurations 的 VM argument 中添加 -Djdk.crypto.KeyAgreement.legacyKDF=true
 *
 */

class Person {
	public Person(String name) {
		this.name = name;
	}
	String name;
	// 公匙
	public PublicKey publicKey;
	// 私匙
	public PrivateKey privateKey;
	// 双方协商过后通用的密匙，用于 AES 加密的
	public SecretKey secretKey;

	// 生成密匙对
	public void generateKeyPair() throws NoSuchAlgorithmException {

		// 使用 KeyPariGenerator.getInstance 方法，传入算法 DH
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");

		// 表示输出的密钥长度是 1024 位
		keyPairGenerator.initialize(512);

		// 获取密钥对
		KeyPair keyPair = keyPairGenerator.generateKeyPair();

		// 获取私匙
		this.privateKey = keyPair.getPrivate();
		// 获取公匙
		this.publicKey = keyPair.getPublic();
	}

	// 接受对方公匙，生成密匙
	public void generateSecretKey(byte[] receivedPublicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {

		KeyFactory keyFactory = KeyFactory.getInstance("DH");

		// 将对方发过来的公匙 byte 数组恢复为 PublicKey，使用 X509EncodedKeySpec
		X509EncodedKeySpec pkSpec = new X509EncodedKeySpec(receivedPublicKey);
		PublicKey receivedPK = keyFactory.generatePublic(pkSpec);

		// 使用 KeyAgreement 协商生成共同的本地密匙
		KeyAgreement keyAgreement = KeyAgreement.getInstance("DH");
		// 传入私匙
		keyAgreement.init(this.privateKey);
		// 对方公匙
		keyAgreement.doPhase(receivedPK, true);
		// 生成本地密钥，传入 AES 表示要生成一个 AES 加密的密钥
		this.secretKey = keyAgreement.generateSecret("AES");
	}

	// 发送加密信息
	public String sendMessage(String message) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

		// 使用本地密钥对信息进行 AES 加密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
		byte[] sendData = cipher.doFinal(message.getBytes("utf-8"));

		return Base64.getEncoder().encodeToString(sendData);
	}

	// 解密接受信息
	public String getMessage(String message) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		
		// 使用本地密钥对信息进行 AES 接密
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
		byte[] getData = cipher.doFinal(Base64.getDecoder().decode(message));

		return new String(getData, "utf-8");
	}
	
	// 输出相关信息
	public void print() {
		System.out.println("当前类为：" + this.name);
		System.out.println("私匙为：" + Base64.getEncoder().encodeToString(this.privateKey.getEncoded()));
		System.out.println("公匙为：" + Base64.getEncoder().encodeToString(this.publicKey.getEncoded()));
		System.out.println("本地私匙为：" + Base64.getEncoder().encodeToString(this.secretKey.getEncoded()));
		System.out.println();
	}

}

public class About_DH {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		
		// 模拟两个终端使用 DH 算法
		Person bob = new Person("Bob");
		Person alex = new Person("Alex");
		
		// 生成公匙和私匙
		bob.generateKeyPair();
		alex.generateKeyPair();
		
		// 协议生成本地密匙，传入对方的公匙，publickey 实例使用 getEncoded 方法可以获取 byte 数组
		alex.generateSecretKey(bob.publicKey.getEncoded());
		bob.generateSecretKey(alex.publicKey.getEncoded());
		
		// 输出相关信息，只有本地密匙相同
		alex.print();
		bob.print();
		
		// Alex 向 Bob 发送加密过的密文， Bob 要解密
		String alexToBob = alex.sendMessage("Hello,world");
		System.out.println("Bob解密后的明文：" + bob.getMessage(alexToBob));
	}

}
