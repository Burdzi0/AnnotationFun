package com.burdzi0.AnnotationExample;

import com.burdzi0.AnnotationExample.annotation.JsonTransformer;
import com.burdzi0.AnnotationExample.annotation.JsonTransformerProcessor;
import com.burdzi0.AnnotationExample.model.Cat;
import com.burdzi0.AnnotationExample.model.Person;

import java.lang.annotation.Annotation;

public class Main {

	public static void main(String[] args) {
		System.out.println(createStrings(JsonTransformer.class, new Person("ABC", "DEF")));
		System.out.println(createStrings(JsonTransformer.class, new Cat("Kitty")));
	}

	public static String createStrings(Class<? extends Annotation> annotationClass, Object object) {
		return object.getClass().isAnnotationPresent(annotationClass) ?
				transform(object) : object.toString();
	}

	private static String transform(Object object) {
		JsonTransformerProcessor processor = new JsonTransformerProcessor();
		return processor.process(object);
	}
}
