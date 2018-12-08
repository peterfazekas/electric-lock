package hu.lock.model.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ResultWriter {

	private final String fileName;
	
	public ResultWriter(String fileName) {
		this.fileName = fileName;
	}

	public void printAll(List<String> list) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
			list.forEach(pw::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
