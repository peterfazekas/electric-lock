package hu.lock.model.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Key {

	private final int id;
	private final String combination;

	public Key(int id, String combination) {
		this.id = id;
		this.combination = combination;
	}

	public int getId() {
		return id;
	}

	public String getCombination() {
		return combination;
	}

	
	public boolean hasDuplication() {
		return combinationMap().entrySet().stream()
				.mapToLong(Entry::getValue)
				.anyMatch(i -> i > 1);
	}
	
	private Map<String, Long> combinationMap() {
		return combination.chars()
				.mapToObj(Integer::toString)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	public String getOpenResult(String masterKey) {
		String result;
		if (!isKeyLengthMatch(masterKey)) {
			result = "incorrect length";
		} else {
			result = open(masterKey) ? "successful" : "incorrect code";
		}
		return combination + " " + result;
	}
	
	private boolean open(String good) {
		boolean matches = isKeyLengthMatch(good);
		if (matches) {
			int difference = (int) good.charAt(0) - (int) combination.charAt(0);
			for (int i = 1; i < good.length(); i++) {
				if ((difference - ((int) good.charAt(i) - (int) combination.charAt(i))) % 10 != 0) {
					matches = false;
				}
			}
		}
		return matches;
	}

	private boolean isKeyLengthMatch(String masterKey) {
		return masterKey.length() == combination.length();
	}

}
