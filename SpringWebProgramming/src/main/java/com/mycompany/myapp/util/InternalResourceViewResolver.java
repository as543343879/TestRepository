package com.mycompany.myapp.util;

public class InternalResourceViewResolver {
	private String prefix;
	private String suffix;
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		System.out.println(prefix);
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		System.out.println(suffix);
		this.suffix = suffix;
	}
	
	

}
