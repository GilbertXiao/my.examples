package com.shravan.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapEntryExample {
	public static void main(String[] args) {
		Map<String,String> m = new HashMap<String,String>();
		m.put("Key1","Val1");
		
		Iterator it =m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println(""+pairs.getKey());
			System.out.println(""+pairs.getValue());
			
		}
		
		
	}
}
