package com.ecm.webautomation.pret.common;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.ecm.webautomation.WebDriverModule;

public class MegaMenuSubCatPages extends WebDriverModule {

	public MegaMenuSubCatPages(WebDriver webDriver) {
		super(webDriver);
	}

	public void verifyIsPageLoaded(String subcat) {
		String url = webDriver.getCurrentUrl().toLowerCase();
		Assert.assertTrue(url.contains(subcat.toLowerCase()), "Url is: " + url
				+ " it doesn't contain " + subcat);
	}
}
