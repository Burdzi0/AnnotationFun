package com.burdzi0.AnnotationExample.annotation;

import com.burdzi0.AnnotationExample.repository.ObjectRepository;

import java.io.FileWriter;
import java.io.IOException;

public class PersistTargetProcessor {

	private ObjectRepository repository = new ObjectRepository();

	public void process(Object object) {
		if (object.getClass().isAnnotationPresent(Database.class)) {
			System.out.println("Saving object to database");
			repository.saveObject(object);
		} else if (object.getClass().isAnnotationPresent(File.class)) {
			System.out.print("Saving object to file");

			if (object.getClass().isAnnotationPresent(JsonTransformer.class)) {
				System.out.println(" in json");
				object = new JsonTransformerProcessor().process(object);
			} else {
				System.out.println();
			}

			try (FileWriter writer = new FileWriter("file.txt", true)) {
				writer.write(object.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close() {
		repository.close();
	}
}
