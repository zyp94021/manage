package me.acgee.manage.utils;

import com.alibaba.fastjson.JSON;

public class Message {
	private String flag;
	private String message;
	
	
	public Message(String flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}

	public Message(String flag) {
		super();
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
