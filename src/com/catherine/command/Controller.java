package com.catherine.command;

import java.util.LinkedList;
import java.util.List;

public class Controller {
	private List<Command> commands;

	public Controller() {
		commands = new LinkedList<>();
	}

	public void add(Command c) {
		commands.add(c);
	}

	public void combos() {
		for (Command c : commands) {
			c.execute();
			System.out.print(" ");
		}
		System.out.print("\n");

		commands.clear();
	}
}
