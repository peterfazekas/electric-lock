package hu.lock.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hu.lock.model.domain.Key;

public class LockFacade {

	private final List<Key> keyList;

	public LockFacade(List<Key> keyList) {
		this.keyList = keyList;
	}
	
	public String getEqualKeyIds(String masterKey) {
		return keyList.stream()
				.filter(i -> i.getCombination().equals(masterKey))
				.map(Key::getId)
				.map(Object::toString)
				.collect(Collectors.joining(" "));
	}
	
	public int getFirstRepeatingLockId() {
		return keyList.stream()
				.filter(Key::hasDuplication)
				.map(Key::getId)
				.findFirst()
				.get();
	}
	
	public String getRandomKey(String masterKey) {
		return String.format("A code of length %d is: %s", masterKey.length(), generateKey(masterKey.length()));
	}
	
	private String generateKey(final int length) {
		Random random = new Random();
		List<Integer> numbers = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
		return IntStream.range(0, length)
			.map(i -> random.nextInt(numbers.size()))
			.map(numbers::remove)
			.mapToObj(String::valueOf)
			.collect(Collectors.joining());
	}
	
	public List<String> getOpenResults(String masterKey) {
		return keyList.stream()
				.map(i -> i.getOpenResult(masterKey))
				.collect(Collectors.toList());
	}
	
}
