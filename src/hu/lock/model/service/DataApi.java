package hu.lock.model.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hu.lock.model.domain.Key;

public class DataApi {

	private int id;
	
	public List<Key> getKeys(String fileName) {
		List<Key> keys = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			keys = br.lines().map(i -> new Key(++id, i)).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keys;
	}
	
}
