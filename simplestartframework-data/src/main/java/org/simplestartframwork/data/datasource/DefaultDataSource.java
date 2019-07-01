package org.simplestartframwork.data.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class DefaultDataSource implements DataSource {

	private Connection connection;

	private String driverClassName;

	private String password;

	private String url;

	private String username;

	@Override
	public Connection getConnection() throws SQLException {

		try {
			Class.forName(driverClassName);

			Connection conn = DriverManager.getConnection(url, username, password);
		
			return conn;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		this.username = username;
		this.password = password;
		try {
			Class.forName(driverClassName);
			return this.getConnection();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null;

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {

		return connection.isWrapperFor(iface);
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {

		return connection.unwrap(iface);
	}

}
