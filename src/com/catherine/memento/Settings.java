package com.catherine.memento;

import java.util.HashMap;
import java.util.Map;

public class Settings {
	private static Map<Integer, Aloy> history = new HashMap<>();

	public int save(Aloy aloy) {
		int id = history.size();
		history.put(id, aloy);
		return id;
	}

	public Aloy loadLatest() {
		int id = history.size() - 1;
		return history.get(id);
	}

	public Aloy load(int id) {
		return history.get(id);
	}
}
