package com.goodsshop.util;

import java.util.List;
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
    	System.out.println(json.toString());
        Map<String, Object> map = new ObjectMapper().readValue(json.toString(), Map.class);
        Map<String, String> validTables = Map.of("qna", "qseq desc", "review_view", "rseq desc", "member_view", "indate desc",
        		"notice", "nseq desc", "goods", "gseq desc");

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
        
        if (map.containsKey("category")) {
            List<String> categories = (List<String>) map.get("category");
            if (!categories.isEmpty()) {
                if (firstCondition) {
                    sql.append(" where ");
                    firstCondition = false;
                } else {
                    sql.append(" and ");
                }
                sql.append("(");
                for (int i = 0; i < categories.size(); i++) {
                    if (i > 0) {
                        sql.append(" or ");
                    }
                    sql.append("category = '").append(categories.get(i)).append("'");
                }
                sql.append(")");
            }
        }
        
        if (map.containsKey("sc")) {
	        if (firstCondition) {
	            sql.append(" where ");
	            firstCondition = false;
	        } else {
	            sql.append(" and ");
	        }
	        sql.append("(").append("subject LIKE CONCAT('%', '").append(map.get("sc")).append("', '%')").append(" or ");
	        sql.append("content LIKE CONCAT('%', '").append(map.get("sc")).append("', '%'))");
        }
        
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.equalsIgnoreCase("table") || key.equalsIgnoreCase("page") || key.equalsIgnoreCase("amount") || key.equalsIgnoreCase("category") || key.equalsIgnoreCase("sc")) {
                continue;
            }
            
            if (key.equals("my")) {
            	if (firstCondition) {
            		sql.append(" where ");
                    firstCondition = false;
            	} else {
            		sql.append(" and ");
            	}
            	sql.append("userid").append(" = '").append(map.get("my")).append("'");
            	continue;
            }

            if (value instanceof String) {
                String stringValue = (String) value;

                if (stringValue.equalsIgnoreCase("null")) {
                    if (firstCondition) {
                        sql.append(" where ");
                        firstCondition = false;
                    } else {
                        sql.append(" and ");
                    }
                    sql.append(key).append(" IS NULL");
                } else if (stringValue.equalsIgnoreCase("notnull")) {
                    if (firstCondition) {
                        sql.append(" where ");
                        firstCondition = false;
                    } else {
                        sql.append(" and ");
                    }
                    sql.append(key).append(" IS NOT NULL");
                } else if (stringValue.equalsIgnoreCase("all")) {
                    int whereIndex = sql.lastIndexOf(" where ");
                    if (whereIndex != -1) {
                        sql.delete(whereIndex, sql.length());
                    }
                    firstCondition = true;
                } else {
                    if (firstCondition) {
                        sql.append(" where ");
                        firstCondition = false;
                    } else {
                        sql.append(" and ");
                    }
                    sql.append(key).append(" LIKE CONCAT('%', '").append(stringValue).append("', '%')");
                }
            } else {
                if (firstCondition) {
                    sql.append(" where ");
                    firstCondition = false;
                } else {
                    sql.append(" and ");
                }
                sql.append(key).append(" = ").append(value);
            }
        }

//        String keyword = (String) map.get("keyword");
//        if (keyword != null && !keyword.isEmpty()) {
//            String searchColumn = (String) map.get("search");
//            if (firstCondition) {
//                sql.append(" where ");
//                firstCondition = false;
//            } else {
//                sql.append(" and ");
//            }
//            sql.append(searchColumn).append(" LIKE CONCAT('%', '").append(keyword).append("', '%')");
//        }

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
