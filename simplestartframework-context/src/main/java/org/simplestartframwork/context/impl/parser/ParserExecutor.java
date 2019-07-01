package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.context.Context;
import org.simplestartframwork.context.impl.parser.Parser;
import org.simplestartframwork.context.impl.parser.ParserParam;

/**
 * <pre>
 * 解释类执行器
 * <pre>
 * @author ranger
 * @version 1.0
 *
 */
public class ParserExecutor {
	// 1.创建一个Log4j的操作对象
	private static final Logger LOG = LogManager.getLogger(ParserExecutor.class.getName());
	
	public ParserExecutor(Context context,Class<?>[] configClassTypes) {
		ParserParam.set(ParserParam.OBJECT_CONTEXT, context);
		ParserParam.set(ParserParam.CONFIG_CLASS_TYPES, configClassTypes);
	}
	

	
	/**
	 * 该方法至于执行一族注解解释类
	 * @param parseres
	 * @throws Exception
	 */
	public void execute(List<Parser> parseres){
		try {
			for (Parser parser : parseres) {
				
				boolean flag = parser.handle();
				if(flag==false) {
					//break;
					throw new RuntimeException("解释器强制终止");
				}
			}
		} 	 catch (ClassNotFoundException e) {
			LOG.error("反射技术-找到类路径异常:" + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			LOG.error("反射技术-动态调用方法或者创建对象参数不匹配异常:" + e.fillInStackTrace());
			e.printStackTrace();
		} catch (SecurityException e) {
			LOG.error("检测到安全错误时引发的异常:" + e.fillInStackTrace());
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
