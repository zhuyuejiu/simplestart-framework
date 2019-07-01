package org.simplestartframwork.core.test.main.component;

import org.simplestartframwork.context.annotation.Initialize;
import org.simplestartframwork.context.annotation.Inject;
import org.simplestartframwork.context.annotation.Matching;
import org.simplestartframwork.context.annotation.Scope;
import org.simplestartframwork.context.annotation.Value;
import org.simplestartframwork.context.annotation.Scope.BeanDefinition;
import org.simplestartframwork.context.annotation.component.Universal;
import org.simplestartframwork.core.test.pojo.User;
import org.simplestartframwork.core.test.service.UserService;

@Universal
@Scope(BeanDefinition.PROTOTYPE)
public class ConstructorInjectService {
	private UserService userService1;
	private UserService userService2;
	private String name;
	@Initialize
	public void init() {
		System.out.println("=========初始化方法==========");
	}
	
	private User user;
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
		
	}

   
	@Inject
	public ConstructorInjectService(@Matching(value="userServiceImpl") UserService userService1,@Matching(value="userServiceImpl2") UserService userService2, @Value("张三") String name){
		
		this.userService1=userService1;
		this.userService2=userService2;
		this.name=name;
	}
	
	
	
	public void test(){
		System.out.println("--测试没有组件注解的类-");
		userService1.save();
		userService2.save();
		System.out.println(name);
	}

}
