package org.simplestartframwork.data.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.annotation.SQL;
import org.simplestartframwork.data.annotation.SQL.SQLType;
import org.simplestartframwork.data.annotation.SQLBuilder;
import org.simplestartframwork.data.handler.parser.SQLBuilderAnnotationParser;
import org.simplestartframwork.data.handler.parser.SQLParser;
import org.simplestartframwork.data.handler.parser.SQLParserExecutor;
import org.simplestartframwork.data.handler.parser.SelectAnnotationParser;
import org.simplestartframwork.data.handler.parser.UpdateAnnotationParser;

/**
 * <pre>
 *  MapperMethodHandler类：用于对DynamicObject对象创建的代理对象处理代理逻辑
 * </pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public class MapperMethodHandler implements InvocationHandler {

	private Session session;

	@SuppressWarnings("unchecked")
	public <T> T getInstance(Class<T> mapperClassType, Session session) {
		this.session = session;

		// MapperMethodHandler handler = new MapperMethodHandler();
		ClassLoader loader = mapperClassType.getClassLoader();
		return (T) Proxy.newProxyInstance(loader, new Class[] { mapperClassType }, this);

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if (!Object.class.equals(method.getDeclaringClass())) {
			List<SQLParser> parsers = new ArrayList<SQLParser>();
			SQL sqlAnnotation = method.getDeclaredAnnotation(SQL.class);
			SQLBuilder sqlBuilder =method.getDeclaredAnnotation(SQLBuilder.class);
			if (sqlAnnotation != null) {
				if (isDMLOperation(sqlAnnotation)) {

					parsers.add(new UpdateAnnotationParser(session, method, args));
				} else if (isDQLOperation(sqlAnnotation)) {
					parsers.add(new SelectAnnotationParser(session, method, args));
				}
		
			}else if(sqlBuilder!=null) {
				parsers.add(new SQLBuilderAnnotationParser(session, method, args));
			}
			SQLParserExecutor spe = new SQLParserExecutor();

			return spe.execute(parsers);
		}
		return null;
	}

	protected boolean isDMLOperation(SQL sqlAnnotation) {

		SQLType type = sqlAnnotation.type();
		if (SQLType.INSERT.equals(type) || SQLType.UPDATE.equals(type) || SQLType.DELETE.equals(type)) {
			return true;
		}
		return false;
	}

	protected boolean isDQLOperation(SQL sqlAnnotation) {

		SQLType type = sqlAnnotation.type();
		if (SQLType.SELECT.equals(type)) {
			return true;
		}
		return false;
	}

}