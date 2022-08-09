package com.zee.zee5app;

//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map.Entry;
//import java.util.TreeMap;
//
//public class MapDemo {
//	public static void main(String[] args) {
//		HashMap<Integer, String> hashmap= new HashMap<>();
//		//TreeMap<Integer, String> hashmap= new TreeMap<>();
//		//LinkedHashMap<Integer, String> hashmap= new LinkedHashMap<>();
//		hashmap.put(1, "Navneet");
//		hashmap.put(2, "Ranjan");
//		hashmap.put(3, "K");
//		hashmap.put(null, "Navneet");
//		hashmap.put(4, "Navneet");
//		hashmap.put(5, null);
//		hashmap.put(6, "Ranjan");
//		hashmap.put(7, "K");
//		hashmap.put(8, "Navneet");
//		hashmap.put(9, "Navneet");
//		hashmap.put(null, "raj");
//		hashmap.put(11, "Ranjan");
//		hashmap.put(12, "K");
//		hashmap.put(-13, "Navneet");
//		hashmap.put(14, "Navneet");
//		hashmap.put(-15, "Navneet");
//		hashmap.put(16, "Navneet");
//		//System.out.println(hashmap.get(-1000));
//		//System.out.println(hashmap);
//		for (Entry<Integer, String> entry: hashmap.entrySet()) {
//			System.out.println(entry.getKey()+ "   "+entry.getValue());
//			
//		}
//		// best use for loop 
//		hashmap.forEach((k,v)->{
//			System.out.println(k+" "+v);
//			
//		});
//			
//		
//	}
//
//}
public class MapDemo {
	public static void main(String... teams) {
		try {
		int score = 1;
		System.out.print(score++);
		} catch (Throwable t) {
		System.out.print(score++);
		} finally {
		System.out.print(score++);
		}
		System.out.print(score++);
		}
}
