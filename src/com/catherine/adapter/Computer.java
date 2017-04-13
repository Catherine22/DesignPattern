package com.catherine.adapter;

public class Computer implements MediaPlayer {

	@Override
	public void play(Cable cable) {
		CableAdapter adapter = new CableAdapter();
		adapter.play(cable);
	}

}
