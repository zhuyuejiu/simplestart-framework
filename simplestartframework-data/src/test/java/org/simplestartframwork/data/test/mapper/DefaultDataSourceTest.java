package org.simplestartframwork.data.test.mapper;

import java.sql.SQLException;

import org.simplestartframwork.data.datasource.DefaultDataSource;

public class DefaultDataSourceTest {
	
	public static void main(String[] args) {
		DefaultDataSource dataSource=new DefaultDataSource();
		dataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		try {
			System.out.println(dataSource.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
