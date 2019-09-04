package org.simplestartframwork.data;

import java.sql.Connection;

public interface Session {

	<T> T getMaper(Class<T> mapper);
	
	Connection getConnection();

	void commit();

	
	void close();

}
