package org.simplestartframwork.core.test.main.value;

import org.simplestartframwork.context.annotation.Value;
import org.simplestartframwork.context.annotation.component.Service;

@Service
public class UserService {
	
	@Value(value="12")
	private Integer age;
	
	private int status;
	
	private String name;
	private String password;
	private String sex ;
	
	@Value(value="男")
	public void setStatus(String sex) {
		this.sex = sex;
	}
	
	public void setName(@Value("张三") String name,  @Value("123456") int status) {
		this.name=name;
		this.status=status;
	}

	public void login() {
		System.out.println("学生名："+age);
		System.out.println("状态："+status);
	
		System.out.println("名字："+name+",密码:"+password);
		System.out.println("性别："+sex);
	}
	
	

}
