package com.catherine.adapter;
/**
 * 举例来说，用电脑来看影片，显示器有两种插槽HDMI或VGA，假设数据流传到显示器做的事都一样，就是“显示画面”，<br>
 * 这边创建一个转接器（Adapter），根据电脑接头的类型，接到显示器相同的插槽。<br>
 * 转接头和电脑要implement相同interface，这是关键。
 * @author Catherine
 *
 */
public class CableAdapter implements MediaPlayer {

	private Monitor monitor;

	@Override
	public void play(Cable cable) {
		if (cable == Cable.HDMI) {
			monitor = new HDMIMonitor();
			monitor.inputHDMI();
		} else if (cable == Cable.VGA) {
			monitor = new VGAMonitor();
			monitor.inputVGA();
		}
	}
}
