package com.catherine.adapter;

public class Computer implements MediaPlayer {

	@Override
	public void play() {
		CableAdapter adapter = new CableAdapter();
		adapter.play(Cable.VGA);
	}

}
