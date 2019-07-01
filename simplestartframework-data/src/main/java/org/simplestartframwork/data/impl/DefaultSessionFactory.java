package org.simplestartframwork.data.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.SessionFactory;

public class DefaultSessionFactory implements SessionFactory {

	private DataSource dataSource;
	private boolean autoCommit = false;
	private ThreadLocal<Session> threadLocal = new ThreadLocal<>();

	public boolean getAutoCommit() {
		return autoCommit;
	}

	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Session createSession() {
		try {
			if (threadLocal.get() == null) {
				DefaultSession session = new DefaultSession();

				boolean autoCommit = this.getAutoCommit();
				Connection conn = dataSource.getConnection();
				conn.setAutoCommit(autoCommit);
				session.setConnection(conn);
				threadLocal.set(session);
			}
			return threadLocal.get();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		if (threadLocal.get() != null) {
			Session session = threadLocal.get();
			session.close();
			threadLocal.remove();
		}

	}

	@Override
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;

	}

}
