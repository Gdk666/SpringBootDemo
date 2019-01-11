package com.guoz.framework.commons.utils;



import com.guoz.framework.commons.Exception.AppException;
import com.guoz.framework.commons.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SuperNormal {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	/**
	 * 格式化字段串 示例：format("Hello %s", user.getName());
	 * 
	 * @param str
	 * @param objects
	 * @return
	 */
	protected String format(String format, Object... objects) {
		return StringUtils.format(format, objects);
	}

	/**
	 * 检查对象是否为空或空串, 为空时将抛出异常
	 * 
	 * @param obj
	 * @param msg
	 * @return
	 */
	public <T> T checkNotEmpty(T obj, String msg) {
		if (obj == null) {
			throw new AppException(msg == null ? "" : msg);
		}
		if ((obj instanceof String) && ((String) obj).trim().isEmpty()) {
			throw new AppException(msg == null ? "" : msg);
		}
		return obj;
	}
	/**
	 * 批量检查参数是否为空
	 * @param objs， 格式为[obj, msg, obj, msg]
	 * @return
	 */
	public void checkNotEmpties(Object ... objs) {
		if(objs == null) {
			return;
		}
		if(objs.length % 2 != 0) {
			throw new AppException("参数长度必需为偶数");
		}
		for(int i=0; i<objs.length; i=i+2) {
			Object obj = objs[i];
			String msg = (String)objs[i+1];
			if (obj == null)
				throw new AppException(msg == null ? "值不能为空" : msg);
			if ((obj instanceof String) && ((String) obj).trim().isEmpty())
				throw new AppException(msg == null ? "值不能为空" : msg);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T checkNotEmptyAndTrim(T obj, String msg) {
		if (obj == null) {
			throw new AppException(msg == null ? "" : msg);
		}
		if ((obj instanceof String) && ((String) obj).isEmpty()) {
			throw new AppException(msg == null ? "" : msg);
		}
		if (obj instanceof String) {
			return ((T) ((String) obj).trim());
		}
		return obj;
	}

	/**
	 * 是否为空，如果是字符串时空白字符也算为空
	 * 
	 * @param obj
	 * @return
	 */
	public boolean isEmpty(Object obj) {
		if (obj instanceof String) {
			if (((String) obj).trim().isEmpty()) {
				return true;
			}
		}
		return obj == null;
	}

}
