package org.simplestartframwork.core.test.service.impl;

import org.simplestartframwork.context.annotation.Initialize;
import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.Scope;
import org.simplestartframwork.context.annotation.Scope.BeanDefinition;
import org.simplestartframwork.context.annotation.component.Service;
import org.simplestartframwork.core.test.dao.UserDAO;
import org.simplestartframwork.core.test.service.UserService;

/**
 * 一个普通的类，用于测试是否可以创建对象
 * @author ranger
 *
 */
@Service
@Scope(BeanDefinition.PROTOTYPE)
public class UserServiceImpl implements UserService {
	

    @Inject
	private UserDAO userDAO;
    
    @Initialize
    private void init() {
    	System.out.println("=初始化方法=");
    }
	
	@Override
	public void save(){
		System.out.println("-保存数据-Service-");
		System.out.println(userDAO);
		this.userDAO.save();
	}
	

	


}
