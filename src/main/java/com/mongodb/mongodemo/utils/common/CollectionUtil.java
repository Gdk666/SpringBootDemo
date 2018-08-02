package com.mongodb.mongodemo.utils.common;

import java.util.Collection;

/**
 * 集合操作
 * @author Administrator
 *
 */
public class CollectionUtil {
	/**
	 * 连接集合元素，空的跳过
	 * @param ls
	 * @param split
	 * @return
	 */
	public static String join(Collection<?> ls, String split){
		if(split == null) {
			split = ";";
		}
		StringBuffer sb = new StringBuffer();
		for(Object obj : ls){
			if(StringUtils.isTrimEmpty(obj)){
				continue;
			}
			sb.append(obj + ";");
		}
		if(sb.length() > 0){
			return sb.substring(0, sb.length()-1);
		}
		return "";
	}
}
