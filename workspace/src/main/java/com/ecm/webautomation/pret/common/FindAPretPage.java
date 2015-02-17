package com.ecm.webautomation.pret.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ecm.webautomation.WebDriverModule;

public class FindAPretPage extends WebDriverModule {
	private CommonMethods commonMethods;

	private static final By FINDAPRET_FORM_LOC = By.id("myForm");

	public FindAPretPage(WebDriver webDriver) {
		super(webDriver);
		commonMethods = new CommonMethods(webDriver);
	}

	public void verifyPage() {
		commonMethods.verifyPresent(FINDAPRET_FORM_LOC, "Find A Pret Form");
	}

}
