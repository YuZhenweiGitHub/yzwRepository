package com.company.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/** 
 * 说明：公共方法
 */
public class PublicUtil {
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();

		map.put("1","d");
		map.put("2","eA");
		map.put("3","A");
		map.put("4","o");
		map.put("5","u");
		map = PublicUtil.sortMapByValue(map);

		for (String key : map.keySet()) {
			System.out.println(key + "==" + map.get(key));
		}
	}
	
	public static String getPorjectPath(){
		String nowpath = "";
		nowpath=System.getProperty("user.dir")+"/";
		
		return nowpath;
	}
	
	/**
	 * 获取本机访问地址
	 * @return
	 */
	public static String getIp(){
		String ip = "";
		try {
			InetAddress inet = InetAddress.getLocalHost();
			ip = inet.getHostAddress();
			//System.out.println("本机的ip=" + ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return ip;
	}

	/**
	 * 使用 Map按value进行排序
	 * @param oriMap
	 * @return
	 */
	public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
		if (oriMap == null || oriMap.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());

		Collections.sort(entryList, new Comparator<Map.Entry<String, String>>() {
			@Override
			public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {
				return me2.getKey().compareTo(me1.getKey());
			}
		});

		Iterator<Map.Entry<String, String>> iter = entryList.iterator();
		Map.Entry<String, String> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}

}