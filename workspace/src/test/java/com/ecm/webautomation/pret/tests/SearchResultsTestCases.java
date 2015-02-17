package com.ecm.webautomation.pret.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecm.webautomation.WebDriverTestNgHelper;
import com.ecm.webautomation.config.ConfigurationReader;
import com.ecm.webautomation.pret.common.CommonMethods;
import com.ecm.webautomation.pret.dataBean.ParametersList;
import com.ecm.webautomation.pret.pages.HomePage;
import com.ecm.webautomation.pret.pages.ProductDetailsPage;
import com.ecm.webautomation.pret.pages.SearchResultPage;

public class SearchResultsTestCases extends WebDriverTestNgHelper {
	private String validSearchText = ConfigurationReader
			.get(ParametersList.VALID_TEXT_SEARCH);
	private ProductDetailsPage productDetailsPage;
	private SearchResultPage searchResultPage;
	private CommonMethods commonMethods;
	private HomePage homePage;
	private String invalidProduct = "hdfdf";

	@BeforeClass
	void setUp() {
		productDetailsPage = new ProductDetailsPage(webDriver);
		searchResultPage = new SearchResultPage(webDriver);
		commonMethods = new CommonMethods(webDriver);
		homePage = new HomePage(webDriver);
		commonMethods.windowMaximize();
		commonMethods.deleteAllCookies();
		commonMethods.openRootBasedUrl(commonMethods.getCountryCode());
		commonMethods.loginPret();
	}

	@BeforeMethod
	void beforeMethod() {
		commonMethods.beforeMethod();
	}

	@Test(description = "TC-PAM-122:SRP05 - Search Results Page - No search results page")
	void TC_PAM_122_SRP05_Search_Results_Page_No_Search_Results_Page() {
		commonMethods.searchForPhrase(invalidProduct);
		searchResultPage.verifyNoSearchResultPage();
		searchResultPage.verifyMainBannerOnNoSRP();
		searchResultPage.verifyBreadCrumb();
		searchResultPage.verifySecondGridColumn();
		searchResultPage.verifySecondGridColumnNContent();
		searchResultPage.clickOnViewOurMenu();
	}

	@Test(description = "TC-PAM-158:SRP01 - Search Results Page - Grid 1 (Main banner)")
	void TC_PAM_158_SRP01_Search_Results_Page_Grid_1() {
		commonMethods.searchForPhrase(validSearchText);
		searchResultPage.verifySearchResultPage();
		searchResultPage.verifyBreadCrumb();
		searchResultPage.verifyMainBannerOnNoSRP();
	}

	@Test(description = "TC-PAM-159:SRP02 - Search Results Page - Search results")
	void TC_PAM_159_SRP02_Search_Results_Page_Search_Results() {
		commonMethods.searchForPhrase(invalidProduct);
		searchResultPage.verifyNoSearchResultPage();
		commonMethods.searchForPhrase(validSearchText);
		searchResultPage.verifySearchResultPage();
		searchResultPage.verifyProductImage();
		searchResultPage.verifyProductName();
		searchResultPage.clickOnProductName();
		searchResultPage.verifyProductImg();
		searchResultPage.verifySerchFunctionality();
		searchResultPage.verifyMaximumChar();
		searchResultPage.verifyUpperAndLowerCase();
		searchResultPage.verifySearchTextBoxNBtn();
		searchResultPage.verifySerchFunctionality();
		webDriver.navigate().back();
	}

	@Test(description = "TC-PAM-160:SRP03 - Search Results Page - Search articles (On Our Website)")
	void TC_PAM_160_SRP03_Search_Results_Page_Search_Articles() {
		// Not Enable....
	}

	@Test(description = "TC-PAM-161:SRP04 - Search Results Page - Grid 1 (Try again Search bar)")
	void TC_PAM_161_SRP04_Search_Results_Page_Grid1() {
		commonMethods.searchForPhrase(invalidProduct);
		commonMethods.waitForAjaxtoComplete();
		searchResultPage.verifyTryAgainGrid();
		searchResultPage.verifyTryAgainGridbar();
		// not enable...
	}

}
