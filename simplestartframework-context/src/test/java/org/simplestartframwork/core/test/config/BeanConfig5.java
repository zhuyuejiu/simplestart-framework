package org.simplestartframwork.core.test.config;

import org.simplestartframwork.context.annotation.Config;
import org.simplestartframwork.context.annotation.Import;
import org.simplestartframwork.context.annotation.Scan;


@Config
@Scan(basePackages={"org.simplestartframwork.core.test.controller"})
@Import(value= {BeanConfig2.class})
public class BeanConfig5 {
	
	

	

	

}
