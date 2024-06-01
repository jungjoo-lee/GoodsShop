package com.goodsshop.util;

import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SqlBuilder {
	private SqlBuilder() {}
	private static SqlBuilder instance = new SqlBuilder();
	public static SqlBuilder getInstance() { return instance;}
	
	public StringBuilder build(int type, JSONObject json) throws JsonMappingException, JsonProcessingException {
		Map<String, Object> map = new ObjectMapper().readValue(json.toString(), Map.class);
		Map<String, String> validTables = Map.of("qna", "qseq desc", "review", "rseq desc", "member", "indate desc", "notice", "nseq desc");
	    
	    if (!validTables.containsKey(map.get("table"))) {
	        throw new IllegalArgumentException("테이블 : " + map.get("table") + "없음");
	    }
	    
	    StringBuilder sql = new StringBuilder();
	    
	    if (type == 0) {
	        sql.append("select count(*) from ").append(map.get("table"));
	    } else {
	    	sql.append("select * from ").append(map.get("table"));
	    }

	    boolean firstCondition = true;
	    for (Map.Entry<String, ?> entry : map.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();

	        if (key.equalsIgnoreCase("limit") || key.equalsIgnoreCase("offset") || key.equalsIgnoreCase("type") || key.equalsIgnoreCase("keyword")
	        		|| key.equalsIgnoreCase("table") || key.equalsIgnoreCase("search") || key.equalsIgnoreCase("page") || key.equalsIgnoreCase("amount")) {
	            continue;
	        }

	        if (firstCondition) {
	            sql.append(" where ");
	            firstCondition = false;
	        } else {
	            sql.append(" and ");
	        }

	        if (value instanceof String) {
	            String stringValue = (String) value;

	            if (stringValue.equalsIgnoreCase("null")) {
	                sql.append(key).append(" IS NULL");
	            } else if (stringValue.equalsIgnoreCase("notnull")) {
	                sql.append(key).append(" IS NOT NULL");
	            } else if (stringValue.equalsIgnoreCase("all")) {
	            	int length = sql.length();
	                sql.delete(length - 7, length);
	            } else {
	                sql.append(key).append(" LIKE CONCAT('%', '").append(stringValue).append("', '%')");
	            }
	        } else {
	            sql.append(key).append(" = ").append(value);
	        }
	    }
	    
	    String keyword = (String) map.get("keyword");
	    if (keyword != null && !keyword.isEmpty()) {
	        String searchColumn = (String) map.get("search");
	        if (firstCondition) {
	            sql.append(" where ");
	        } else {
	            sql.append(" and ");
	        }
	        sql.append(searchColumn).append(" LIKE CONCAT('%', '").append(keyword).append("', '%')");
	    }
	    
	    if (type == 1) {
		    String orderBy = validTables.get(map.get("table"));
		    sql.append(" order by ").append(orderBy);
	
		    int amount = (int) map.getOrDefault("amount", 10);
		    int page = (int) map.getOrDefault("page", 1);
		    int offset = (page - 1) * amount;
		    sql.append(" LIMIT ").append(amount).append(" OFFSET ").append(offset);
	    }
	    System.out.println(sql);
	    return sql;
	}
}
