package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

/*
 *	RSA 签名算法
 * 	先使用 RSA 输出公钥私钥对
 * 	再使用发送方私钥对发送数据进行签名
 * 	检验签名需要使用发送方的公钥和签名数据进行
 */


public class RSASignture {
	
	// 公钥、私钥
	PublicKey pk;
	PrivateKey sk;

	// 无参构造方法，生成公钥和私钥对
	public RSASignture() throws NoSuchAlgorithmException {
		
		// 使用 KeyPariGenerator.getInstance 方法，传入算法 RSA
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		
		// 表示输出的密钥长度是 1024 位
		keyPairGenerator.initialize(1024);
		
		// 获取密钥对，获取公钥，私钥
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		this.pk = keyPair.getPublic();
		this.sk = keyPair.getPrivate();
	}
	
	// 把私钥导出为字节
	public byte[] getSk() {
		return this.sk.getEncoded();
	}
	
	// 把公钥导出为字节
	public byte[] getPk() {
		return this.pk.getEncoded();
	}
	
	//	定义 sign 方法对数据进行签名
	public byte[] sign(byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		// 使用 Signature.getInstance 方法传入 签名算法名称如 SHA1withRSA，获取 signature 实例
		Signature signature = Signature.getInstance("SHA1withRSA");
		
		// 传入私匙，初始化签名
		signature.initSign(this.sk);
		
		// 传入明文，对明文进行签名
		signature.update(message);
		
		// 获得明文签名后的字节
		return signature.sign();
	}
	
	// 验证签名，需要传入原始信息，验证数据
	public boolean verify(byte[] message, byte[] sign) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		
		// 使用 Signature.getInstance 方法传入 签名算法名称如 SHA1withRSA，获取 signature 实例
		Signature signature = Signature.getInstance("SHA1withRSA");
		
		// 传入公匙初始化签名，表示要验证签名，只能传入公匙
		signature.initVerify(this.pk);
		
		signature.update(message);
		
		// 验证签名
		return signature.verify(sign);
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		byte[] message = "Hello,world".getBytes("utf-8");
		RSASignture rsaSignture = new RSASignture();
		
		// 获取签名
		byte[] sign = rsaSignture.sign(message);
		System.out.println("签名：" + Base64.getEncoder().encodeToString(sign));
		
		boolean verified = rsaSignture.verify(message, sign);
		System.out.println("验证结果：" + verified);
		
		// 使用别的公匙验证
		boolean verified2 = new RSASignture().verify(message, sign);
		System.out.println("使用别的公匙验证的结果：" + verified2);
		
		// 修改发送信息
		message[0] = 1;
		boolean verified3 = rsaSignture.verify(message, sign);
		System.out.println("修改发送信息后的验证结果：" + verified3);
	}
}
