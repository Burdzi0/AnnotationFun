package com.burdzi0.AnnotationExample.annotation;

import org.json.JSONObject;

import java.lang.reflect.Field;

public class JsonTransformerProcessor {

	public String process(Object object) {
		JSONObject jsonObject = new JSONObject();

		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field: fields) {
			field.setAccessible(true);
			try {
				jsonObject.put(field.getName(), field.get(object));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}
}
