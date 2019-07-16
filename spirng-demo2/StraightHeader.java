package com.netease.course;

import java.util.Map;

public class StraightHeader implements Header {
	private String color;
	private int size;
	
	public StraightHeader(String color,int size) {
		this.color = color;
		this.size = size;
		
	}
	
	//public StraightHeader(Map<String,String> paras) {
	//	this.color = paras.get("Header");
	//	this.size = Integer.valueOf(paras.get("size"));
	//}

	public void doWork() {
		System.out.println("Do work with straight header");

	}

	public String getInfo() {
		return "StraightHeader:color" + color+",size="+ size ;
	}

}
