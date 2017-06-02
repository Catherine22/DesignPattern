package com.catherine.mvc.models;

public class CouponDetail {
	private String to;

	private String title;

	private String desc;

	private String ID;

	private String[] pic;

	private String from;

	private String info;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String[] getPic() {
		return pic;
	}

	public void setPic(String[] pic) {
		this.pic = pic;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "ClassPojo [to = " + to + ", title = " + title + ", desc = " + desc + ", ID = " + ID + ", pic = " + pic
				+ ", from = " + from + ", info = " + info + "]";
	}
}