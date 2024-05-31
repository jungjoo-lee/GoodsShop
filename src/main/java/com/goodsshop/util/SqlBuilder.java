package com.goodsshop.util;

import java.util.Map;

public class SqlBuilder {
	private SqlBuilder() {}
	private static SqlBuilder instance = new SqlBuilder();
	public static SqlBuilder getInstance() { return instance;}
	
	public StringBuilder build(String table, Map<String, ?> map) {
		Map<String, String> validTables = Map.of("qna", "qseq desc", "review", "rseq desc", "member", "indate desc", "notice", "nseq desc");
	    
	    if (!validTables.containsKey(table)) {
	        throw new IllegalArgumentException("테이블 : " + table + "없음");
	    }
	    
	    StringBuilder sql = new StringBuilder();
	    
	    if (map.containsKey("type") && "total".equals(map.get("type"))) {
	        sql.append("select count(*) from ").append(table);
	    } else {
	    	sql.append("select * from ").append(table);
	    }

	    boolean firstCondition = true;
	    for (Map.Entry<String, ?> entry : map.entrySet()) {
	        String key = entry.getKey();
	        Object value = entry.getValue();

	        if (key.equalsIgnoreCase("limit") || key.equalsIgnoreCase("offset") || key.equalsIgnoreCase("type")) {
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
	            } else if (stringValue.equalsIgnoreCase("not null")) {
	                sql.append(key).append(" IS NOT NULL");
	            } else {
	                sql.append(key).append(" LIKE CONCAT('%', ").append(stringValue).append(", '%')");
	            }
	        } else {
	            sql.append(key).append(" = ").append(value);
	        }
	    }

	    String orderBy = validTables.get(table);
	    sql.append(" order by ").append(orderBy);

	    if (map.containsKey("limit")) {
	        sql.append(" limit ").append(map.get("limit"));
	    }

	    if (map.containsKey("offset")) {
	        sql.append(" offset ").append(map.get("offset"));
	    }

	    return sql;
	}
}
