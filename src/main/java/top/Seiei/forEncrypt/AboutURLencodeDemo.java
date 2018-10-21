package top.Seiei.forEncrypt;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AboutURLencodeDemo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "value=中 文";
		String url = URLEncoder.encode(str, "UTF-8");
		System.out.println(url);
		String str2 = URLDecoder.decode(url, "utf-8");
		System.out.println(str2);
	}
}
