package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/*
 *	Base64 编码
 */

public class AboutBase64Demo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String string = "Hello\u00ff编码测试";

		// base64编码
		// 字符串转成 字节数组后，使用 getEncoder 进行编码
		String bs64 = Base64.getEncoder().encodeToString(string.getBytes("utf-8"));
		System.out.println(bs64);

		// 使用 getDecoder 进行解码
		String oriString = new String(Base64.getDecoder().decode(bs64), "utf-8");
		System.out.println(oriString);

		// 某些标准的base64编码在url中会引起冲突，所以使用getUrlEncoder，其实就是将 + 号和 / 号换成 - 号和 _
		bs64 = Base64.getUrlEncoder().encodeToString(string.getBytes("utf-8"));
		System.out.println(bs64);
		oriString = new String(Base64.getUrlDecoder().decode(bs64), "utf-8");
		System.out.println(oriString);
	}
}
