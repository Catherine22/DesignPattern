package com.catherine.data_access_object;

public class Contact {
	public final static int BLOCK_PHONE_CALL = 0x0001;
	public final static int BLOCK_SMS = 0x0010;
	private int ID;
	private String name;
	private int block;

	public int getID() {
		return ID;
	}

	public void setID(int ID){
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	@Override
	public String toString() {
		return "Contact [ID=" + ID + ", name=" + name + ", block=" + block + "]";
	}

}
