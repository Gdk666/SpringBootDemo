package com.guoz.framework.commons.utils.common;


import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.guoz.framework.commons.Exception.AppException;

import java.io.*;

/**
 * 序列化助手类，使用hession
 * @author Administrator
 *
 */
public class SerializeUtils {

	public static Object hessianDeserialize(byte[] by) throws IOException {
		if (by == null)
			throw new NullPointerException();
		return hessianDeserialize(new ByteArrayInputStream(by));
	}

	public static Object hessianDeserialize(InputStream input)
			throws IOException {
		return new HessianInput(input).readObject();
	}

	public static byte[] hessianSerialize(Object obj) {
		if (obj == null)
			throw new NullPointerException();

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			HessianOutput ho = new HessianOutput(os);
			ho.writeObject(obj);
			return os.toByteArray();
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

	public static void hessianSerialize(Object obj, OutputStream out)
			throws IOException {
		HessianOutput ho = new HessianOutput(out);
		ho.writeObject(obj);
	}

	public static byte[] javaSerialize(Object obj) throws Exception {
		if (obj == null)
			throw new NullPointerException();

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(obj);
		return os.toByteArray();
	}

	public static Object javaDeserialize(byte[] by) throws Exception {
		if (by == null)
			throw new NullPointerException();

		ByteArrayInputStream is = new ByteArrayInputStream(by);
		ObjectInputStream in = new ObjectInputStream(is);
		return in.readObject();
	}
	/*
	 * public static void main(String[] args) throws IOException{ Map<String,
	 * Object> obj = new HashMap<>(); obj.put("name", "tome"); byte[] bytes =
	 * SerializeUtils.hessianSerialize(obj);
	 * System.out.println(SerializeUtils.hessianDeserialize(bytes)); }
	 */
}