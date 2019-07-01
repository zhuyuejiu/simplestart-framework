package org.simplestartframwork.core.test.dao.impl;

import org.simplestartframwork.context.annotation.Scope;
import org.simplestartframwork.context.annotation.Scope.BeanDefinition;
import org.simplestartframwork.context.annotation.component.Persistent;
import org.simplestartframwork.core.test.dao.UserDAO;

@Persistent
@Scope(BeanDefinition.PROTOTYPE)
public class UserDAOImpl implements UserDAO {
	
	public void save(){
		System.out.println("-保存数据-dao-");
	}

}
