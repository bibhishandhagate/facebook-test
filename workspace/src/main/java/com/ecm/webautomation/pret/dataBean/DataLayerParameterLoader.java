/*
 * Copyright 2013 eCommera. All Rights Reserved.
 */
package com.ecm.webautomation.pret.dataBean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DataLayerParameterLoader {

	private static Properties properties;
	private static String DATA_LAYER_PROPERTIES = "src/test/resources/conf/data-layer.properties";

	DataLayerParameterLoader() {
		
	}

	private static Properties getDataLayerPropertiesSet() throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(DATA_LAYER_PROPERTIES));
		return properties;
	}

	public static String getValue(String key) {
		return properties.getProperty(key);
	}

	public static String getDataLayerProperties(String key) {
		try {
			return getDataLayerPropertiesSet().getProperty(key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}
}
