package com.ecm.webautomation.pret.dataBean;

import java.io.FileInputStream;
import java.util.Properties;

import com.ecm.webautomation.config.ConfigurationReader;

public class PropFileLoader {
	private static final String storefrontPath = ConfigurationReader
			.get("storefront.path");
	private static String LOCALE = "conf/en_gb.properties";

	private static String getLocale() {

		switch (storefrontPath) {
		case "en-gb":
			LOCALE = "src/test/resources/conf/en_gb.properties";
			break;
//		case "en-us":
//			LOCALE = "src/test/resources/conf/en_gb.properties";
//			break;
		case "zh-cn":
			LOCALE = "src/test/resources/conf/zh_cn.properties";
			break;
		case "zh-hk":
			LOCALE = "src/test/resources/conf/zh_hk.properties";
			break;
		case "fr-fr":
			LOCALE = "src/test/resources/conf/fr_fr.properties";
			break;
		default:
			LOCALE = "src/test/resources/conf/en_gb.properties";
			break;
		}
		return LOCALE;

	}

	private static Properties loadLocaleProp() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(getLocale()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static String getText(String key) {
		return loadLocaleProp().getProperty(key);
	}
}
