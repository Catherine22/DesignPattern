package com.catherine.mvc;

import com.catherine.mvc.models.Coupon;
import com.catherine.mvc.models.CouponDetail;
import com.google.gson.Gson;

/**
 * C, controller
 * @author Catherine
 *
 */
public class RetrieveCouponFromDB {
	private FalseListView listView;
	
	public RetrieveCouponFromDB(){
		listView = new FalseListView();
	}

	public void downloadCoupons(){
		String response = "[\n" +
	            "  {\n" +
	            "    \"ID\": \"0001\",\n" +
	            "    \"title\": \"wine\",\n" +
	            "    \"desc\": \"$1000\",\n" +
	            "    \"from\": \"6/1/2017\",\n" +
	            "    \"to\": \"12/31/2017\"\n" +
	            "  },\n" +
	            "  {\n" +
	            "    \"ID\": \"0002\",\n" +
	            "    \"title\": \"wine\",\n" +
	            "    \"desc\": \"$800\",\n" +
	            "    \"from\": \"5/1/2017\",\n" +
	            "    \"to\": \"7/10/2017\"\n" +
	            "  }\n" +
	            "]";

		Gson gson = new Gson();
		Coupon[] coupons = gson.fromJson(response, Coupon[].class);
		listView.refresh(coupons);
	}
	
	public void downloadCouponDetail(String ID){
	    String response = "[\n" +
	            "  {\n" +
	            "    \"ID\": \"0001\",\n" +
	            "    \"title\": \"wine\",\n" +
	            "    \"desc\": \"$1000\",\n" +
	            "    \"from\": \"6/1/2017\",\n" +
	            "    \"to\": \"12/31/2017\",\n" +
	            "    \"pic\": [\n" +
	            "      \"a.png\",\n" +
	            "      \"b.png\",\n" +
	            "      \"c.png\",\n" +
	            "      \"d.png\"\n" +
	            "    ],\n" +
	            "    \"info\": \"Like the alcoholic fermentation done by yeast that converts grape juice into wine, malolactic fermentation is also accomplished by the action of microbes.\"\n" +
	            "  },\n" +
	            "  {\n" +
	            "    \"ID\": \"0002\",\n" +
	            "    \"title\": \"wine\",\n" +
	            "    \"desc\": \"$800\",\n" +
	            "    \"from\": \"5/1/2017\",\n" +
	            "    \"to\": \"7/10/2017\",\n" +
	            "    \"pic\": [\n" +
	            "      \"e.png\",\n" +
	            "      \"f.png\",\n" +
	            "      \"g.png\",\n" +
	            "      \"h.png\"\n" +
	            "    ],\n" +
	            "    \"info\": \"Grape juice is very tart primarily due to the presence of tartaric acid and malic acid, which account for over 90% of the natural acidity that is found in wine. Malic acid is found in many types of fruit while tartaric acid is found only in grapes and a few other fruits.\"\n" +
	            "  }\n" +
	            "]";


		Gson gson = new Gson();
		CouponDetail[] details = gson.fromJson(response, CouponDetail[].class);
		if(ID.equals("0001"))
			listView.showDetail(details[0]);
		else if(ID.equals("0002"))
			listView.showDetail(details[1]);
	}
}
