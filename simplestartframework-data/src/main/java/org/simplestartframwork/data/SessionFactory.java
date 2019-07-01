package org.simplestartframwork.data;

import javax.sql.DataSource;

public interface SessionFactory {

	Session createSession();
	
	void setDataSource(DataSource dataSource);
	


	void close();

	DataSource getDataSource();

	void setAutoCommit(boolean autoCommit);

}
