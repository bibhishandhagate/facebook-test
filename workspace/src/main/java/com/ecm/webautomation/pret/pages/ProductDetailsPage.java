/*
 * Copyright 2014 eCommera. All Rights Reserved.
 */
package com.ecm.webautomation.pret.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.pret.common.CommonMethods;

public class ProductDetailsPage extends WebDriverModule {
	private CommonMethods commonMethods;

	public ProductDetailsPage(WebDriver webDriver) {
		super(webDriver);
		commonMethods = new CommonMethods(webDriver);
	}

	private final By BAGELS_PRODUCT_BANNER_LOC = By.cssSelector(".textbox");
	private final By BREADCRUMB_LOC = By.cssSelector(".breadcrumb");
	private final By VIEW_PRODUCT_LINK_LOC = By
			.cssSelector(".row article:nth-child(1) div>div>a");
	private final By PRODUCT_IMAGE_LOC = By.cssSelector(".img-responsive");
	private final By PRODUCT_NAME_LOC = By.cssSelector(".col-sm-6.head>h1");
	private final By PRODUCT_INGREDIENTS_LOC = By.cssSelector(".head>h2");
	private final By PRODUCT_DESC_LOC = By.cssSelector(".col-sm-6.textbox>p");
	private final By PRODUCT_ALLERGEN_LOC = By
			.cssSelector(".list-inline.avoid-list");
	private final By BREADCRUMB_SECTION_LOC = By.cssSelector(".breadcrumb>a");
	// private final By
	// SOCIAL_MEDIA_LOC=By.cssSelector(".row article:nth-child(1) ul:nth-child(2) li:nth-child(1) a .normal");
	private final By SOCIAL_MEDIA_FACEBOOK_LOC = By
			.xpath("//a[@class='addthis_button_facebook']/img[2]");
	private final By SOCIAL_MEDIA_TWIT_LOC = By
			.xpath("//a[@class='addthis_button_twitter']/img[2]");
	private final By SOCIAL_MEDIA_GOOGLE_LOC = By
			.xpath("//a[@class='addthis_button_google+']/img[2]");
	private final By SOCIAL_MEDIA_INSTAGRAM_LOC = By
			.xpath("//a[@class='addthis_button_instagram']/img[2]");

	// For Twitter
	private final By EMAIL_ID_TEXTBOX_LOC = By.id("username_or_email");
	private final By EMAIL_PASS_LOC = By.id("password");
	private final By SINUP_BTN_LOC = By.cssSelector(".button.selected.submit");
	private final By TWEET_BTN_LOC = By.cssSelector(".button.selected.submit");
	private String custPass = "123456";

	public void verifyBreadCrumb() {
		commonMethods.verifyVisible(BAGELS_PRODUCT_BANNER_LOC,
				"BAGELS Banner .");
		commonMethods.verifyVisible(BREADCRUMB_LOC,
				"BreadCrumb is HOMEA >Bagels..");
	}

	public void clickOnProductList() {
		click(VIEW_PRODUCT_LINK_LOC);
		commonMethods.waitForAjaxtoComplete();
	}

	public void veifyProductImage() {
		commonMethods.verifyVisible(PRODUCT_IMAGE_LOC,
				"Energy Bagel Product is presented on PDP.");
	}

	public void verifyProductDescription() {
		commonMethods.verifyVisible(PRODUCT_NAME_LOC, "");
		commonMethods.verifyVisible(PRODUCT_INGREDIENTS_LOC, "");
		commonMethods.verifyVisible(PRODUCT_DESC_LOC, "");
		commonMethods.verifyVisible(PRODUCT_ALLERGEN_LOC, "");
	}

	public void clickOnBreadCrumbSection() {
		click(BREADCRUMB_SECTION_LOC);
		commonMethods.verifyVisible(BAGELS_PRODUCT_BANNER_LOC,
				"BAGELS Banner .");
	}

	public void clickOnSocialMedia() {
		commonMethods.waitForElementToLoad(SOCIAL_MEDIA_TWIT_LOC, timeout);
		click(SOCIAL_MEDIA_TWIT_LOC);
		sleep(2000);
		commonMethods.switchToNewWindow();
		commonMethods.waitForAjaxtoComplete();
		webDriver.close();
		commonMethods.switchToNewWindow();
	}

	public void cilckOnProductImage() {
		click(PRODUCT_IMAGE_LOC);
	}

	public void verifySocialMediaLogo() {
		commonMethods.waitForAjaxtoComplete();
		// commonMethods.verifyVisible(SOCIAL_MEDIA_FACEBOOK_LOC,
		// "Facebook logo");
		commonMethods.verifyVisible(SOCIAL_MEDIA_TWIT_LOC, "Twitter logo");
		commonMethods
				.verifyVisible(SOCIAL_MEDIA_GOOGLE_LOC, "Google Plus logo");
		// commonMethods.verifyVisible(SOCIAL_MEDIA_INSTAGRAM_LOC,
		// "Instagram logo");
	}

	public void verifytwitterLoginPage() {
		if (isElementPresented(EMAIL_ID_TEXTBOX_LOC)) {
			commonMethods.verifyVisible(EMAIL_ID_TEXTBOX_LOC, "");
			commonMethods.verifyVisible(EMAIL_PASS_LOC, "");
			commonMethods.verifyVisible(SINUP_BTN_LOC, "");
		}
		commonMethods.verifyVisible(TWEET_BTN_LOC, "");
	}

	public void loginToTwitter(String email) {
		if (isElementPresented(EMAIL_ID_TEXTBOX_LOC)) {
			typeByLocator(EMAIL_ID_TEXTBOX_LOC, email);
			typeByLocator(EMAIL_PASS_LOC, custPass);
			click(SINUP_BTN_LOC);
		}
	}

	public void clickOnTwiterLogo() {
		commonMethods.waitForElementToLoad(SOCIAL_MEDIA_TWIT_LOC, timeout);
		click(SOCIAL_MEDIA_TWIT_LOC);
		commonMethods.waitForAjaxtoComplete();
		commonMethods.switchToNewWindow();
		sleep(3000);
	}
}
