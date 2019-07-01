package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.annotation.SQLBuilder;

public class SQLBuilderAnnotationParser extends AbstractSQLParser{

	public SQLBuilderAnnotationParser(Session session, Method method, Object[] args) {
		this.setMethod(method);
		this.setSession(session);
		this.setArgs(args);
	}

	@Override
	public Object handle() throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Method method= this.getMethod();
		SQLBuilder sqlBuilder = method.getDeclaredAnnotation(SQLBuilder.class);
		System.out.println(sqlBuilder.method());
		 Class<?>[] builders = sqlBuilder.classes();
		 Class<?> builderClass = builders[0];
		 Object instance = builderClass.newInstance();
		 Method[] methods = builderClass.getDeclaredMethods();
		 for (Method builderMethod : methods) {
			 String name = builderMethod.getName();
			 if (name.equals(sqlBuilder.method())) {
				 Object sqlObject = builderMethod.invoke(instance, this.getArgs()[0]);
				 Session session = this.getSession();
				 Connection connection = session.getConnection();
				 String sql = sqlObject.toString();
				 String buildSQL = this.buildSQL(sql, this.getArgs());
				 PreparedStatement statement = connection.prepareStatement(buildSQL);
				 int count = statement.executeUpdate();
				 statement.close();
				 return count;
			}
		}
	
		return null;
	}

}
