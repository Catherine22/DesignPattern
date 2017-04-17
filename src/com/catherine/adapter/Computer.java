package com.catherine.adapter;

public class Computer implements MediaPlayer {

	@Override
	public void play() {
		CableAdapter adapter = new CableAdapter();
		adapter.setType(Cable.VGA);
		adapter.play();
	}

}
