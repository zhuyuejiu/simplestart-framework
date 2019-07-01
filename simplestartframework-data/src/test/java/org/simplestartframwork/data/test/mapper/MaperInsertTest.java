package org.simplestartframwork.data.test.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.SessionFactory;
import org.simplestartframwork.data.datasource.DefaultDataSource;
import org.simplestartframwork.data.impl.DefaultSessionFactory;

public class MaperInsertTest {
	
	private UserMapper userMapper= null;

	@Before
	public void before() {
		DefaultDataSource dataSource = new DefaultDataSource();
		dataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		SessionFactory sessionFactory = new DefaultSessionFactory();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setAutoCommit(true);
        Session session = sessionFactory.createSession();
        userMapper = session.getMaper(UserMapper.class);
	}

	@Test
	public void insert() {

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("user_name", "李四");
		hashMap.put("password", "1222");
		int insert = userMapper.insert(hashMap);
		System.out.println(insert);
		System.out.println(hashMap.get("user_id"));
	

	}

	@Test
	public void findALL() {

		List<Map<String, Object>> entitys = userMapper.findAll();
		for (Map<String, Object> entity : entitys) {
			System.out.println(entity.get("user_name"));
		}
	}

	@Test
	public void batchInsert() {
		try {
			List<Map<String, Object>> entitys=new ArrayList<Map<String,Object>>();
			HashMap<String, Object> hashMap1 = new HashMap<String, Object>();
			hashMap1.put("user_name", "张三");
			hashMap1.put("password", "333");
			
			HashMap<String, Object> hashMap2 = new HashMap<String, Object>();
			hashMap2.put("user_name", "李四");
			hashMap2.put("password", "1222");
			
			entitys.add(hashMap1);
			entitys.add(hashMap2);
			
			int insert = userMapper.batchInsert(entitys);
			System.out.println(insert);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
