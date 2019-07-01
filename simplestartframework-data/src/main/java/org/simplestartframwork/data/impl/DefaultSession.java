package org.simplestartframwork.data.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.handler.MapperMethodHandler;


public class DefaultSession implements Session {

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	protected void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public <T> T getMaper(Class<T> mapper) {
		MapperMethodHandler synamicObject=new MapperMethodHandler();
	
		return 	synamicObject.getInstance(mapper,this);
	}

	@Override
	public void commit() {
		try {
			this.getConnection().commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void close() {
		try {
			this.getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
