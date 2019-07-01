package org.simplestartframwork.data.test.mapper;

import java.util.List;
import java.util.Map;

import org.simplestartframwork.data.annotation.Options;
import org.simplestartframwork.data.annotation.SQL;
import org.simplestartframwork.data.annotation.SQL.SQLType;
import org.simplestartframwork.data.annotation.SQLBuilder;
import org.simplestartframwork.data.test.mapper.sqlbuilder.UserSQLBuilder;

public interface UserMapper {
	
	@SQL(type=SQLType.INSERT,value="INSERT INTO tb_user	(user_name, password) VALUES (#{ user_name }, #{password})")
	@Options(backfillParmaryKey=true,columnName="user_id",propteryName="user_id")
	int insert(Map<String,Object> entity);
	
	@SQL(type=SQLType.SELECT,value="SELECT * FROM tb_user")
	List<Map<String,Object>> findAll();
	
	@SQLBuilder(classes=UserSQLBuilder.class,method="batchInsert")
	int batchInsert(List<Map<String,Object>> entitys);

}
