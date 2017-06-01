package org.forkjoin.jdbckit.mysql;


public class NameUtils {
	
	public static final String toClassName(String str){
		StringBuilder sb=new StringBuilder();
		boolean isUp=false;
		for(int i=0;i<str.length();i++){
			char c=str.charAt(i);
			if (0==i) {
				sb.append(Character.toUpperCase(c));
			}else if(c=='_'){
				isUp=true;
			}else if(isUp){
				isUp=false;
				sb.append(Character.toUpperCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 吧驼峰和下划线间隔的 名字转换成 下划线间隔的常量
	 */
	public static final String toConstantName(String str){
		StringBuilder sb=new StringBuilder();
		int len = str.codePointCount(0, str.length());
		for (int i = 0; i < len; i++) {
			int code = str.codePointAt(i);
			if(Character.isUpperCase(code)){
				sb.append("_");
			}
			sb.appendCodePoint(Character.toUpperCase(code));
		}
		return sb.toString();
	}

	public static final String toFieldName(String str){
		StringBuilder sb=new StringBuilder();
		boolean isUp=false;
		for(int i=0;i<str.length();i++){
			char c=str.charAt(i);
			if(i ==0){
				c = Character.toLowerCase(c);
			}
			if(c=='_'){
				isUp=true;
			}else if(isUp){
				isUp=false;
				sb.append(Character.toUpperCase(c));
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
