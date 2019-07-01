package org.simplestartframwork.core.test.main.bean;

import org.simplestartframwork.context.annotation.Initialize;
import org.simplestartframwork.core.test.pojo.User;

public class TestBeanUtils {
	

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

	public void test() {
		System.out.println("--测试成功-");
	
	}

}
