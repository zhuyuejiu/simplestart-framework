package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.annotation.SQL;

public class SelectAnnotationParser extends AbstractSQLParser {

	private static final Logger LOGGER = LogManager.getLogger(SelectAnnotationParser.class.getName());

	public SelectAnnotationParser(Session session, Method method, Object[] args) {
		this.setMethod(method);
		this.setSession(session);
		this.setArgs(args);
	}

	@Override
	public Object handle() throws SQLException {

		Method method = this.getMethod();
		SQL sqlAnnotation = method.getDeclaredAnnotation(SQL.class);

		Connection connection = this.getSession().getConnection();
		String sql = this.buildSQL(sqlAnnotation.value(), this.getArgs());
		LOGGER.debug("query sql:" + sql);
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet rs = statement.executeQuery();
		return this.reusltToMap(rs);

	}

	private Object reusltToMap(ResultSet rs) throws SQLException {
		List<Map<String, Object>> entitys = new ArrayList<Map<String, Object>>();
		ResultSetMetaData data = rs.getMetaData();
		int columnCount = data.getColumnCount();
		while (rs.next()) {
			Map<String, Object> entity = new HashMap<String, Object>();

			for (int i = 0; i < columnCount; i++) {
				String key = data.getColumnName(i + 1);
				Object value = rs.getObject(key);
				entity.put(key, value);
			}
			entitys.add(entity);
		}
		if (!entitys.isEmpty()) {
	
			LOGGER.debug("result entitys size:" + entitys.size());
			Class<?> returnType = this.getMethod().getReturnType();
			if (Collection.class.isAssignableFrom(returnType)) {
				return entitys;
			//如果返回类型是一个Map，返回第一个元素
			} else if (Map.class.isAssignableFrom(returnType)) {
				return entitys.get(0);
			}
		}
		return null;
	}

}
