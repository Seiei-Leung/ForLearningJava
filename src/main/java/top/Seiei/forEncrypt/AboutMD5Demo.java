package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 *	MD5 加密
 */

public class AboutMD5Demo {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String str = "Hello,world";
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes("utf-8"));
		byte[] bytes = md5.digest(); // 获取字节数组

		// 字节数组转换为十六进制数字显示，其中 `%032x`
		// 是格式，`%x`表示使用十六进制显示数字，`032`表示使用该十六进制的位数为32个，数字小则开头自动补零
		System.out.println(String.format("%032x", new BigInteger(bytes)));

		// 可以分开添加，效果一样
		md5 = null;
		md5 = MessageDigest.getInstance("MD5");
		md5.update("Hello,".getBytes("utf-8"));
		md5.update("world".getBytes("utf-8"));
		byte[] bytes2 = md5.digest();

		System.out.println((new BigInteger(bytes)).equals(new BigInteger(bytes2)));
	}

}
