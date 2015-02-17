package com.ecm.webautomation.pret.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.pret.dataBean.AddressDataBean;

public class StoreLocatorPages extends WebDriverModule {

	private final By ADDRESS_TEXTFIELD_LOC = By.id("searchAddress");
	private final By COUNTRY_DROPDOWN_LOC = By.id("searchCountry");
	private final By FIND_BUTTON_LOC = By.cssSelector(".btn-primary");
	private final String STORENAME_LINK_LOC = "#storesLocatorList>div:nth-child(%d) .storeSearchResultsName>a";

	public StoreLocatorPages(WebDriver webDriver) {
		super(webDriver);
	}

	public void findAStore(AddressDataBean addressDataBean) {
		typeByLocator(ADDRESS_TEXTFIELD_LOC, addressDataBean.getPostCode());
		selectByValue(COUNTRY_DROPDOWN_LOC, addressDataBean.getCountryCode());
		click(FIND_BUTTON_LOC);
		waitForAjaxtoComplete();
	}

	public void getStoreDetails(int storeIndex) {
		click(By.cssSelector(String.format(STORENAME_LINK_LOC, storeIndex)));
	}

	public String getStoreName(int storeIndex) {
		return getTextFromLocator(
				By.cssSelector(String.format(STORENAME_LINK_LOC, storeIndex)))
				.trim();
	}

}
