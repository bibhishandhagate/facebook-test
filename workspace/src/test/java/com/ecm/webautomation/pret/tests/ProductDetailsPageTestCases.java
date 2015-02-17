package com.ecm.webautomation.pret.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecm.webautomation.WebDriverTestNgHelper;
import com.ecm.webautomation.pret.common.CommonMethods;
import com.ecm.webautomation.pret.pages.HomePage;
import com.ecm.webautomation.pret.pages.ProductDetailsPage;

public class ProductDetailsPageTestCases extends WebDriverTestNgHelper {
	private ProductDetailsPage productDetailsPage;
	private CommonMethods commonMethods;
	private HomePage homePage;

	@BeforeClass
	void setUp() {
		productDetailsPage = new ProductDetailsPage(webDriver);
		commonMethods = new CommonMethods(webDriver);
		homePage = new HomePage(webDriver);
		commonMethods.deleteAllCookies();
		commonMethods.windowMaximize();
		commonMethods.openRootBasedUrl(commonMethods.getCountryCode());
		commonMethods.loginPret();
	}

	@BeforeMethod
	void beforeMethod() {
		commonMethods.beforeMethod();
	}

	@Test(description = "TC-PAM-138:PDP01-Product Details Page-Product details and image")
	void TC_PAM_138_PDP01_Product_Details_Page_Product_Details_And_Image() {
		homePage.clickOnOurMenu();
		productDetailsPage.verifyBreadCrumb();
		productDetailsPage.clickOnProductList();
		productDetailsPage.veifyProductImage();
		productDetailsPage.verifyProductDescription();
		productDetailsPage.clickOnBreadCrumbSection();
		productDetailsPage.clickOnProductList();
		productDetailsPage.clickOnSocialMedia();
		homePage.selectCountry();
		productDetailsPage.cilckOnProductImage();
	}

	@Test(description = " TC-PAM-140:PDP03-Product Details Page-Social Product Share Links (Add this)")
	void TC_PAM_140_PDP03_Product_Details_Page_Social_Product_Share_Links() {
		String emailId = "automation" + System.currentTimeMillis()
				+ "@ecommera.co.uk";
		homePage.clickOnOurMenu();
		productDetailsPage.clickOnProductList();
		productDetailsPage.verifySocialMediaLogo();
		productDetailsPage.clickOnTwiterLogo();
		productDetailsPage.verifytwitterLoginPage();
		productDetailsPage.loginToTwitter(emailId);
		commonMethods.switchOffNewWindow();
	}

	@Test(description = "TC-PAM-141:PDP04-Product Details Page-Product Tastes Great With")
	void TC_PAM_141_PDP04_Product_Details_Page_Product_Tastes_Great_With() {

	}

	@Test(description = "TC-PAM-143:PDP06-Product Details Page-Nutritional Table - default Information")
	void TC_PAM_143_PDP06_Product_Details_Page_Nutritional_Table_Default_Information() {
		homePage.clickOnOurMenu();
		productDetailsPage.clickOnProductList();
	}

}
