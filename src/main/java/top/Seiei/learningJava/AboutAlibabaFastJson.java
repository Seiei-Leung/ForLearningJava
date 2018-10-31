package top.Seiei.learningJava;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/*
 *	关于 fastJSON 测试
 */

public class AboutAlibabaFastJson {

	static String jsonObjStr = "{\"name\":\"Seiei\",\"age\":\"19\"}";
	static String jsonArrayStr = "[{\"name\":\"Seiei\",\"age\":\"19\"},{\"name\":\"Taka\",\"age\":\"21\"}]";
	
	// JSON格式的字符串转换为 JSONObject 对象或 JSONArray 对象
	// JSONObject对象可以调用诸如 getString(String key) 的方法获取键值，查看源码发现是获取 Map对应的 object值，再进行强制转换
	static void strToJSONObjet() {
		// 转换为 JSONObject
		JSONObject jsonObject = JSON.parseObject(jsonObjStr);
		System.out.println(jsonObject.getString("name"));
		System.out.println(jsonObject.getInteger("age"));
		// JSONObject 转换为JSON 格式的字符串
		System.out.println(JSON.toJSONString(jsonObject));
		
		// 转换为 JSONArray
		JSONArray jsonArray = JSON.parseArray(jsonArrayStr);
		
		for (Object item: jsonArray) {
			JSONObject jsonObjectItem = (JSONObject) item;
			System.out.println(jsonObjectItem.getString("name"));
			System.out.println(jsonObjectItem.getInteger("age"));
		}
	}

	// JSON格式的字符串转化为 JavaBean
	// JSON数组格式字符串转化为 JavaBean
	static void strToJavaBean() {
		
		// JSON 格式的字符串转化为 JavaBean
		Student seiei = JSON.parseObject(jsonObjStr, new TypeReference<Student>() {});
		System.out.println("JSON 格式的字符串转化为 JavaBean");
		System.out.println(seiei.getName());
		
		// JSON数组格式字符串转化为 JavaBean数组
		//	不能使用 List，只能使用 ArrayList
		ArrayList<Student> list = JSON.parseObject(jsonArrayStr, new TypeReference<ArrayList<Student>>() {});
	
		for (Student item:list) {
			System.out.println(item.getName());
			System.out.println(item.getAge());
		}
	}
	
	// JavaBean 转换为JSON格式的字符串
	static void JavaBeanToStr() {
		Student seiei = new Student();
		seiei.setAge(18);
		seiei.setName("seiei");
		System.out.println(JSON.toJSONString(seiei));
	}
	
	// JavaBean 转换为JSONObject
	static void JavaBeanToJSONObject() {
		Student seiei = new Student();
		seiei.setAge(18);
		seiei.setName("seiei");
		JSONObject jsonObject = (JSONObject) JSON.toJSON(seiei);
		System.out.println(jsonObject.getString("name"));
	}
	
	public static void main(String[] args) {
//		AboutAlibabaFastJson.strToJSONObjet();
// 		AboutAlibabaFastJson.strToJavaBean();
// 		AboutAlibabaFastJson.JavaBeanToStr();
// 		AboutAlibabaFastJson.JavaBeanToJSONObject();
	}
}

class Student {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	String name;
	Integer age;
}
