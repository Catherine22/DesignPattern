package com.catherine.memento;

public class Aloy {
	private int XP;
	private String weapon;
	private String ammo;
	private String outfit;
	private long timestamp;

	public Aloy(int XP, String weapon, String ammo, String outfit) {
		this.XP = XP;
		this.ammo = ammo;
		this.weapon = weapon;
		this.outfit = outfit;
		timestamp = System.currentTimeMillis() / 1000L;
	}

	@Override
	public String toString() {
		return "Aloy [XP=" + XP + ", weapon=" + weapon + ", ammo=" + ammo + ", outfit=" + outfit + ", timestamp="
				+ timestamp + "]";
	}

}
