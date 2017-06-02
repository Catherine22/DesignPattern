package com.catherine.mvc;

import com.catherine.mvc.models.Coupon;
import com.catherine.mvc.models.CouponDetail;

/**
 * V, view
 * 
 * @author Catherine
 *
 */
public class FalseListView {

	public void refresh(Coupon[] data) {

		for (int i = 0; i < data.length; i++) {
			Coupon coupon = data[i];
			System.out.println("-----------------------");
			System.out.println(coupon.getTitle());
			System.out.println(coupon.getDesc());
			System.out.println(String.format("%s ~ %s", coupon.getFrom(), coupon.getTo()));
		}
		System.out.println("-----------------------");

	}

	public void showDetail(CouponDetail data) {
		System.out.println("------------Detail-----------");
		System.out.println(data.getTitle());
		System.out.println(data.getDesc());
		System.out.println(String.format("%s ~ %s", data.getFrom(), data.getTo()));
		String[] pics = data.getPic();
		System.out.print("[ ");
		for (int i = 0; i < pics.length; i++) {
			System.out.print(pics[i] + " ");
		}
		System.out.println("]");
		System.out.println(data.getInfo());
		System.out.println("------------Detail-----------");

	}
}
