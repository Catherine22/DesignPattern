package com.catherine.command;

public class Attack implements Command {

	@Override
	public void execute() {
		System.out.print("attack");
	}
}
