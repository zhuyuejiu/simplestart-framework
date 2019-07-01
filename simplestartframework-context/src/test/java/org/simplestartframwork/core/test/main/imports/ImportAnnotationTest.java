package org.simplestartframwork.core.test.main.imports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.simplestartframwork.context.annotation.Config;
import org.simplestartframwork.context.annotation.Import;
import org.simplestartframwork.core.test.config.BeanConfig;


public class ImportAnnotationTest {

	@Test
	public void imp() {
		List<Class<?>> configList = new ArrayList<Class<?>>();
		Class<?>[] arr = { BeanConfig.class};
		//configList.addAll(Arrays.asList(arr));
		for (int i = 0; i < arr.length; i++) {
			Class<?> configClassType = arr[i];
			Config configAnnotation = configClassType.getDeclaredAnnotation(Config.class);
			if (configAnnotation != null) {
				List<Class<?>> doImport = doImport(arr[i]);
				configList.addAll(doImport);
			}

		}
		for (Class<?> class1 : configList) {
			System.out.println(class1.getName());
		}

	}

	protected List<Class<?>> doImport(Class<?> configClassType) {
		List<Class<?>> configList = new ArrayList<Class<?>>();
		configList.add(configClassType);
		 int count=0;
		 while (true) {
			
			Class<?>[] importConfigClassTypes = this.getClass(configList.get(count));
			if (importConfigClassTypes == null) {
				break;
			} else {
				List<Class<?>> importConfigClassTypesList = Arrays.asList(importConfigClassTypes);
				configList.addAll(importConfigClassTypesList);
				count++;
			}

		}
		
		return configList;

	}

	public Class<?>[] getClass(Class<?> configClassType) {
		Import importAnnotation = configClassType.getDeclaredAnnotation(Import.class);
		if (importAnnotation != null && importAnnotation.value() != null) {
			return importAnnotation.value();
		}
		return null;
	}

}
