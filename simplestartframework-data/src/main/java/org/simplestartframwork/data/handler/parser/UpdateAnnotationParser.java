package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.annotation.SQL;

public class UpdateAnnotationParser extends AbstractSQLParser {

	private static final Logger LOGGER = LogManager.getLogger(UpdateAnnotationParser.class.getName());

	public UpdateAnnotationParser(Session session, Method method, Object[] args) {
		this.setMethod(method);
		this.setSession(session);
		this.setArgs(args);
	}

	@Override
	public Object handle() throws SQLException {
		LOGGER.info("--UpdateAnnotationParser-handle");
		Method method = this.getMethod();
		SQL sqlAnnotation = method.getDeclaredAnnotation(SQL.class);

		int count = 0;

		Connection connection = this.getSession().getConnection();
		String sql = this.buildSQL(sqlAnnotation.value(), this.getArgs());
		LOGGER.debug("excuteUpdate sql:"+sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		count = statement.executeUpdate();
		this.backfill(count);
		return count;

	}

}
