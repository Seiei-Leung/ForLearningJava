package top.Seiei.learningJava;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AboutMapDemo {

	public static void main(String[] args) {
		Map<String, Integer> studentMap = new HashMap<String, Integer>();
		studentMap.put("Seiei", 18);
		studentMap.put("Taka", 20);
		System.out.println("======================");
		System.out.println("使用 keyset 循环获取数据");
		for (String keyString : studentMap.keySet()) {
			System.out.println(keyString + "-->" + studentMap.get(keyString));
		}
		System.out.println("======================");
		System.out.println("使用 entrySet 循环获取数据");
		for (Entry<String, Integer> entry : studentMap.entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}
		System.out.println("======================");
		System.out.println("使用 TreeMap 循环获取有序数据");
		// 添加 comparator 实现接口，根据字母大小倒序排列
		Map<String, Integer> studentTreeMap = new TreeMap<String, Integer>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return -1 * o1.compareTo(o2);
			}
		});
		studentTreeMap.put("Seiei", 18);
		studentTreeMap.put("Taka", 20);
		studentTreeMap.put("Weiwei", 16);
		for (String keyString : studentTreeMap.keySet()) {
			System.out.println(keyString + "-->" + studentTreeMap.get(keyString));
		}
		
		
	}

}
