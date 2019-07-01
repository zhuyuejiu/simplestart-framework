package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <pre>
 * 解释类执行器
 * 
 * <pre>
 * 
 * @author ranger
 * @version 1.0
 *
 */
public class SQLParserExecutor {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(SQLParserExecutor.class.getName());

	/**
	 * 该方法至于执行一族注解解释类
	 * 
	 * @param parseres
	 * @throws Exception
	 */
	public Object execute(List<SQLParser> parseres) {
		LOG.info("-SQL SQLParserExecutor-");
		for (SQLParser sqlParser : parseres) {
			try {
				return sqlParser.handle();
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
