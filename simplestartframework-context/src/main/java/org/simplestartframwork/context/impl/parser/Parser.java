package org.simplestartframwork.context.impl.parser;

import java.lang.reflect.InvocationTargetException;

/**
 * 声明一个解释器接口
 * @author ranger
 *
 */
public interface Parser {
	
	
	/**
	 * 所有的注解的解释，通过该方法实现
	 * @return 返回一个标识，判断是否中断下一个解释器的执行
	 * 1.如果为 true：表示下一个解释器可以继续执行
	 * 2.如果为false，表示中断下一个解释器执行
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 * @throws Exception
	 */
	boolean handle() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException;

}
