package com.catherine.memento;

/**
 * 备忘录模式用于记录状态，比如游戏存档
 * @author Catherine
 *
 */
public class World {

	private int XP;
	private String weapon;
	private String ammo;
	private String outfit;

	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getAmmo() {
		return ammo;
	}

	public void setAmmo(String ammo) {
		this.ammo = ammo;
	}

	public String getOutfit() {
		return outfit;
	}

	public void setOutfit(String outfit) {
		this.outfit = outfit;
	}

	public Aloy getState() {
		return new Aloy(XP, weapon, ammo, outfit);
	}
}
