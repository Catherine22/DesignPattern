package com.catherine.mvc.models;
/**
 * M, model
 * @author Catherine
 *
 */
public class Coupon {
	private String to;

	private String title;

	private String desc;

	private String ID;

	private String from;

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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public String toString() {
		return "ClassPojo [to = " + to + ", title = " + title + ", desc = " + desc + ", ID = " + ID + ", from = " + from
				+ "]";
	}

}
