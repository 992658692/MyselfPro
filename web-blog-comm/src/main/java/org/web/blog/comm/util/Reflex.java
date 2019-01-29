package org.web.blog.comm.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflex {

	public static void printMethodMessage(Class<?> c) {
		System.out.println("类的名称是：" + c.getName());
		//得到类对应的方法集
		Method[] m = c.getMethods();
		System.out.println("======原有方法=======");
		method(m);
		Method[] m1 = c.getDeclaredMethods();
		System.out.println("======原有方法=======");
		method(m1);
	}
	
	public static void printFieldMessage(Class<?> c) {
		Field[] fi = c.getFields();
		System.out.println("======原有成员变量=======");
		field(fi);
		Field[] fi1 = c.getDeclaredFields();
		System.out.println("======自定义成员变量=======");
		field(fi1);
	}
	
	public static void printConMessage(Class<?> c) {
		Constructor<?>[] cs = c.getConstructors();
		System.out.println("======原有构造函数=======");
		constructor(cs);
		Constructor<?>[] cs1 = c.getDeclaredConstructors();
		System.out.println("======自定义构造函数=======");
		constructor(cs1);
	}
	
	
	private static void method(Method[] m) {
		for (Method method : m) {
			Class<?> returnType = method.getReturnType();//获得方法的返回类型对应的类
			System.out.println(returnType.getName() + "");//获得方法返回类型对应类的名称
			System.out.println(method.getName() + "(");//获得方法名
			
			Class<?>[] paramType = method.getParameterTypes();//获得方法的入参类型的类数组
			for(Class<?> p : paramType) {
				if (p == paramType[paramType.length - 1]) {
					System.out.println(p.getName());
				} else {
					System.out.println(p.getName() + ",");
				}
			}
			System.out.println(")");
		}
	}
	
	private static void constructor(Constructor[] cs) {
		for (Constructor<?> con : cs) {
			System.out.println(con.getName() + "(");
			Class<?>[] pt = con.getParameterTypes();
			for (Class<?> type : pt) {
				if (type != pt[pt.length - 1]) {
					System.out.println(type.getName() + ",");
				} else {
					System.out.println(type.getName());
				}
			}
			System.out.println(")");
		}
	}
	
	private static void field(Field[] fi) {
		for (Field f : fi) {
			Class<?> fieldType = f.getType();
			String fieldName = f.getName();
			System.out.println(fieldType + ":" + fieldName);
		}
	}
}
