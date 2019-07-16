package com.netease.course;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("header")
public class StraightHeader implements Header {
	@Value("${color}")
	private String color;
	@Value("${size}")
	private int size;
	
	@PostConstruct
	public void init() {
		System.out.println("init the header");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("destroy the header");
	}
	

	

	public void doWork() {
		System.out.println("Do work with straight header");

	}

	public String getInfo() {
		return "StraightHeader:color" + color+",size="+ size ;
	}

}
