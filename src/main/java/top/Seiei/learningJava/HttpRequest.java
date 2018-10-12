package top.Seiei.learningJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;


/**
 *	后端避免跨域，使用 Net 类模拟浏览器请求网络数据
 */
public class HttpRequest {
	
		public static void main(String[] args) {
			// System.out.println(HttpRequest.sendGet("https://www.etscn.com.cn:40443/estapi/api/CutPieceEntry/SearchBagDataByPosi", "position=3a06中"));
			JSONObject data = new JSONObject();
			data.put("scene", "name:zhangSan");
			System.out.println(data);
			System.out.println(HttpRequest.sendPost("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=14_mxh7nGaiZ5IHg3BaLgJF2IWab-kGPhgv15Dz31fvIXYn_CKUD15dTDz_pW-iUhXWuicuffTAreGd5vK0okq2IAbhPL_Vyuh1w1ocZkJEfmpxJZTwZe8m_gcj7TeOP4x6RSFbsfYznAUSY2o1QSZeACALNU", data));
		}
	
	/**
	 *	GET 请求
	 *	@param url 请求地址
	 *	@param param 请求参数，形如 key1=value1&key2=value2
	 *	@return 返回响应结果字符串形式
	 * 
	 */
	public static String sendGet(String url, String param) {
		
		// 保存结果 
		String result = "";
		// 用于读取数据流
		BufferedReader in = null;
		// 请求地址
		String stringUrl = url + "?" + param;
		
		try {
			
			// 实例化 URL 实例
			URL realUrl = new URL(stringUrl);
			
			// 打开和 URL 之间的连接
			URLConnection urlConnection = realUrl.openConnection();
			
			// 设置请求头部信息
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
			// 实际连接
			urlConnection.connect();
			
            // 获取所有响应头字段
            Map<String, List<String>> map = urlConnection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
			
			// 定义 BufferedReader 输入流读取请求响应
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
            	result += line;
            }
            
		} catch (MalformedURLException e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
		}
		
		// 使用 finally 关闭输入流
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
		            System.out.println("关闭 BufferedReader 失败" + e);
					e.printStackTrace();
				}
			}
		}
		
		// 返回数值
		return result;
	}


	public static String sendPost(String url, JSONObject data) {
		
		// 保存结果 
		String result = "";
		// 用于读取数据流
		BufferedReader in = null;
		// Post请求输出流
		PrintWriter out = null;
		
		
		try {
			
			// 实例化 URL 实例
			URL realUrl = new URL(url);
			
			// 打开和 URL 之间的连接
			URLConnection urlConnection = realUrl.openConnection();
			
			// 设置请求头部信息
			urlConnection.setRequestProperty("accept", "*/*");
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			urlConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			
            // 发送POST请求必须设置如下两行
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
            
			// 获取 urlConnection 对象的输出流, utf-8  编码以防中文乱码
			//out = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
			out = new PrintWriter(urlConnection.getOutputStream());
			
			// 发送请求参数，flush输出流的缓冲
			out.print(data);
			out.flush();
			
            // 获取所有响应头字段
            Map<String, List<String>> map = urlConnection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
			
			// 定义 BufferedReader 输入流读取请求响应
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			
		} catch (MalformedURLException e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
		} catch (IOException e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
		}
        //使用finally块来关闭输出流、输入流
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (out != null) {
				out.close();
			}
		}
		return result;
	}



}
