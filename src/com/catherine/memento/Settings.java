package com.catherine.memento;

import java.util.ArrayList;
import java.util.List;

public class Settings {
	private static List<Aloy> history = new ArrayList<>();

	public void save(Aloy aloy) {
		history.add(aloy);
	}

	public Aloy load() {
		return history.get(history.size() - 1);
	}
}
