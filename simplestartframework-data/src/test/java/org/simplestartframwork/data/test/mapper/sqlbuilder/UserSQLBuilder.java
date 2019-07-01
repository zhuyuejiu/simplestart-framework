package org.simplestartframwork.data.test.mapper.sqlbuilder;

import java.util.List;
import java.util.Map;

public class UserSQLBuilder {
	
	public String batchInsert(List<Map<String,Object>> entitys) {
		String sql="INSERT INTO tb_user	(user_name, password)	VALUES ";
		StringBuilder sb=new StringBuilder(sql);
		for (int i = 0; i <entitys.size(); i++) {
			sb.append("(#{list["+i+"].user_name }, #{list["+i+"].password})");
			if(i!=(entitys.size()-1)) {
				sb.append(",");
			}
		}
		System.out.println(sb.toString());
		return  sb.toString();
	}

}
