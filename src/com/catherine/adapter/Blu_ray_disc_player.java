package com.catherine.adapter;

public class Blu_ray_disc_player implements MediaPlayer {

	@Override
	public void play() {
		CableAdapter adapter = new CableAdapter();
		adapter.setType(Cable.HDMI);
		adapter.play();
	}

}