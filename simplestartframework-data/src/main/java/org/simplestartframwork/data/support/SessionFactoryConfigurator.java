package org.simplestartframwork.data.support;



import java.lang.annotation.Annotation;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplestartframework.core.utils.PackageScanUtils;
import org.simplestartframwork.context.utils.ContextUtils;
import org.simplestartframwork.data.Session;
import org.simplestartframwork.data.SessionFactory;

public class SessionFactoryConfigurator {
	
	private static final Logger LOGGER=LogManager.getLogger(SessionFactoryConfigurator.class.getName());

	private String[] mapperPackages;
	private SessionFactory sessionFactory;
	private Class<? extends Annotation> componentAnnotation;

	public Class<? extends Annotation> getComponentAnnotation() {
		return componentAnnotation;
	}

	public void setComponentAnnotation(Class<? extends Annotation> componentAnnotation) {
		this.componentAnnotation = componentAnnotation;
	}

	public String[] getMapperPackages() {
		return mapperPackages;
	}

	public void setMapperPackages(String... mapperPackages) {
		this.mapperPackages = mapperPackages;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void configurator() {
      
		Set<String> classNames = PackageScanUtils.getClassNames(mapperPackages, true);
		LOGGER.debug("mapper classes:"+classNames);
		for (String className : classNames) {
			
			Class<?> mapper;
			try {
				mapper = Class.forName(className);
		
				Session session = sessionFactory.createSession();
				Annotation annotation = mapper.getDeclaredAnnotation(componentAnnotation);
				if(annotation!=null) {
					Object maperObject = session.getMaper(mapper);
					LOGGER.debug("--create mapper object："+maperObject.getClass().getSimpleName());
					ContextUtils.insertObjectByDefaultName(maperObject);
				}
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

		}
	}
	
}
