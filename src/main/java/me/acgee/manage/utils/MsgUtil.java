package me.acgee.manage.utils;

public class MsgUtil {
	public static Message success(){
		return new Message("success");
	}
	public static Message fail(){
		return new Message("fail");
	}
	public static Message fail(String message){
		return new Message("fail",message);
	}
}
