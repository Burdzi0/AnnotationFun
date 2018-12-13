package com.burdzi0.AnnotationExample;

import com.burdzi0.AnnotationExample.annotation.JsonTransformer;
import com.burdzi0.AnnotationExample.annotation.JsonTransformerProcessor;
import com.burdzi0.AnnotationExample.annotation.PersistTargetProcessor;
import com.burdzi0.AnnotationExample.model.Cat;
import com.burdzi0.AnnotationExample.model.Person;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Cat> cats = new ArrayList<Cat>() {{
			add(new Cat("B"));
			add(new Cat("D"));
			add(new Cat("E"));
		}};

		ArrayList<Person> persons = new ArrayList<Person>() {{
			add(new Person("A","B"));
			add(new Person("C","D"));
			add(new Person("E","F"));
		}};

		PersistTargetProcessor processor = new PersistTargetProcessor();
		cats.forEach(processor::process);
		persons.forEach(processor::process);
		processor.close();
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
