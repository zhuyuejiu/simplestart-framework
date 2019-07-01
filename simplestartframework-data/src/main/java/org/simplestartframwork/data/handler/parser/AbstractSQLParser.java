package org.simplestartframwork.data.handler.parser;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.annotation.Options;



public abstract class AbstractSQLParser implements SQLParser {
	private static final Logger LOG = LogManager.getLogger(AbstractSQLParser.class.getName());
	
	private boolean isBaseType(Object parameterObje) {
		try {
			Class<? extends Object> clasz = parameterObje.getClass();
			if (clasz.isPrimitive()) {
				return true;
			}else if(parameterObje instanceof String ) {
				return true;
			}else if(((Class<?>) clasz.getField("TYPE").get(null)).isPrimitive()) {
				return true;
			}else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	private Object[] args;

	private Method method;
	private Session session;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	@SuppressWarnings("unchecked")
	public void backfill(Object id) {

		if (id != null) {
			Options optionsAnnotation = method.getDeclaredAnnotation(Options.class);
			if (optionsAnnotation != null) {
				boolean isKey = optionsAnnotation.backfillParmaryKey();
				if (isKey) {
					Object arg = this.getArgs()[0];
					// 支持Map类型的ID回填
					if (arg instanceof Map) {
						String key = optionsAnnotation.propteryName();

						Map<String, Object> map = (Map<String, Object>) arg;
						if (key != null && !key.equals("")) {
							map.put(key, id);
						} else {
							map.put("ID", arg);
						}

					}

				}
			}

		}

	}

	/**
	 * 解释SQL语句的#{}表达式
	 * @param sql
	 * @param args
	 * @return
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("unchecked")
	public String buildSQL(String sql, Object[] args){
		if (args != null && args.length > 0) {
			Object paramObject = this.getArgs()[0];
			if (paramObject instanceof Map) {

				Map<String, Object> paramMap = (Map<String, Object>) paramObject;
				Set<String> keySet = paramMap.keySet();
				

				for (String key : keySet) {
					//1.设置替换字符串的格式
					String format="\\#\\{" + key + "\\}".replaceAll(" ", "");
				    //2.去除SQL的#{}中的空格
					sql=sql.replaceAll("\\s*\\}", "\\}");
					sql=sql.replaceAll("\\#\\{\\s*", "\\#\\{");
					LOG.debug("build SQL format："+format);
					sql = sql.replaceAll(format, (String) "'" + paramMap.get(key) + "'");
				}
				LOG.info("build SQL："+sql);

			}else if( paramObject instanceof Collection<?> ) {
				Collection<Map<String,Object>> collection=(Collection<Map<String, Object>>) paramObject;
				Iterator<Map<String, Object>> iterator = collection.iterator();
				int i=0;
				while(iterator.hasNext()) {
					Map<String, Object> entity = iterator.next();
					
					Set<String> keySet = entity.keySet();
				
					for (String key : keySet) {
						//1.设置替换字符串的格式
						String format="\\#\\{list\\["+i+"\\]." + key + "\\}".replaceAll(" ", "");
					    //2.去除SQL的#{}中的空格
						sql=sql.replaceAll("\\s*\\}", "\\}");
						sql=sql.replaceAll("\\#\\{\\s*", "\\#\\{");
						LOG.debug("build SQL format："+format);
						sql = sql.replaceAll(format, (String) "'" + entity.get(key) + "'");
					
					}
					i++;
					LOG.info("build SQL："+sql);
				}
			 //判断是否是基础类型以及字符串
			}else if(this.isBaseType(paramObject)){
				StringBuilder sb=new StringBuilder(sql);
				LOG.debug("#位置："+sql.indexOf("#")+",}位置:"+ sql.indexOf("}"));
	
				sb.replace(sql.indexOf("#"), sql.indexOf("}")+1, "'"+ paramObject.toString()+"'");
				LOG.debug("SQL替换字符,转义后SQL："+sb.toString());
				sql = sb.toString();
			}
		}
		return sql;
	}

}
