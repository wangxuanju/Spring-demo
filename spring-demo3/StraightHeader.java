package com.netease.course;

import java.util.Map;

public class StraightHeader implements Header {
	private String color;
	private int size;

	//基于构造方法的依赖注入方法
	//public StraightHeader(String color,int size) {
	//	this.color = color;
	//	this.size = size;
 	//}
	
	
	//基于setter方法的依赖注入方法
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	

	public void doWork() {
		System.out.println("Do work with straight header");

	}

	public String getInfo() {
		return "StraightHeader:color" + color+",size="+ size ;
	}

}
