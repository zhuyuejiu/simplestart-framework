package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface SQLParser {

	/**
	 * <pre>
	 * 所有的SQL操作注解的解释，通过该方法实现
	 * 
	 * 
	 * </pre>
	 * 
	 * @throws SQLException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	Object handle() throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException;

}
