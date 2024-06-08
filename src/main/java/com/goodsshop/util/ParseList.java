package com.goodsshop.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseList {
	public ParseList() {}
	
	public List<String> parseStringList(JSONObject jsonObj) {
		JSONArray checkList = jsonObj.getJSONArray("checkList");
		List<String> resultList = new ArrayList<>();
		
		for (int i = 0; i < checkList.length(); i++) {
			resultList.add(checkList.getString(i));
		}
		
		return resultList;
	}
}
