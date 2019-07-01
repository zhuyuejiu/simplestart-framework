package org.simplestartframwork.data.test.handler;

import org.simplestartframwork.data.handler.MapperMethodHandler;

public class DynameObjectTest {
	

	public void insert() {
		
		MapperMethodHandler db=new MapperMethodHandler();
		UserMapper userMapper = db.getInstance(UserMapper.class,null);
		int insert = userMapper.insert();
		System.out.println(insert);
	
	}

}
