package org.simplestartframwork.core.test.config;

import org.simplestartframwork.context.annotation.Bean;
import org.simplestartframwork.context.annotation.Config;
import org.simplestartframwork.context.annotation.Import;
import org.simplestartframwork.context.annotation.Scan;
import org.simplestartframwork.core.test.main.bean.TestBeanUtils;


@Config
@Scan(basePackages={"org.simplestartframwork.core.test.service"})
@Import(value= {BeanConfig5.class})
public class BeanConfig4 {
	
	
	@Bean
	public Object getTestBeanUtils(){
	
		TestBeanUtils tu=new TestBeanUtils();
		System.out.println("-------------------------------------------1"+tu);
		return tu;
	}
	
	@Bean
	public Object getTestBeanUtils1(){
		
		TestBeanUtils tu=new TestBeanUtils();
		System.out.println("-------------------------------------------2"+tu);
		return tu;
	}
	

}
