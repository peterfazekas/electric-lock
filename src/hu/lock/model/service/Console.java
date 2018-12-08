package hu.lock.model.service;

import java.util.Scanner;

public class Console {

	private final Scanner scanner = new Scanner(System.in);
	
	public String read(String text) {
		System.out.print(text);
		return scanner.next();
	}
}
