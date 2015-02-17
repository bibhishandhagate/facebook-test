package com.ecm.webautomation.pret.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.pret.common.CommonMethods;

public class HomePage extends WebDriverModule {
	private CommonMethods commonMethods;

	public HomePage(WebDriver webDriver) {
		super(webDriver);
		commonMethods = new CommonMethods(webDriver);
	}

	private final By HOMEPAGE_LOC = By
			.cssSelector(".auto-holder.add-nav.hidden-print");
	// Footer Column Logos Locators
	private final By LEFT_COL_TWIT_FOLLOW_US_LOC = By
			.cssSelector(".block>div>div:nth-child(1)");
	private final By MID_COL_PRET_FOLLOW_US_LOC = By
			.cssSelector(".block>div>div:nth-child(2)");
	private final By RIGHT_COL_FACEBOOK_FOLLOW_US_LOC = By
			.cssSelector(".block>div>div:nth-child(3)");
	private final By LEFT_TWIT_LOGO_LOC = By
			.cssSelector(".block>div>div:nth-child(1)  img:nth-of-type(1)");
	private final By MID_PRET_LOGO_LOC = By
			.cssSelector(".block>div>div:nth-child(2)  img:nth-of-type(1)");
	private final By RIGHT_FACEBOOK_LOGO_LOC = By
			.cssSelector(".block>div>div:nth-child(3)  img:nth-of-type(1)");
	private final By LEFT_COL_TWIT_DESC_LOC = By
			.cssSelector(".block>div>div:nth-child(1)  p:nth-child(4)");
	private final By RIGHT_COL_FACEBOOK_DESC_LOC = By
			.cssSelector(".block>div>div:nth-child(3)  p:nth-child(4)");
	private final By LEFT_TWIT_HYPERPER_LIC_LOC = By
			.cssSelector(".block>div>div:nth-child(1) h4>a");
	private final By RIGHT_FACEBOOK_HYPERPER_LIC_LOC = By
			.cssSelector(".block>div>div:nth-child(3)  h4>a");
	private final By TWITER_PRET_LOGO_LOC = By
			.cssSelector(".ProfileAvatar-image");
	private final By FACEBOOK_PRET_LOGO_LOC = By.cssSelector(".profilePic.img");
	private final By SOCIAL_ICON_F_BOOK_LOC = By
			.cssSelector(".block.hidden-print>ul>li:nth-child(1) .normal");
	private final By SOCIAL_ICON_GOOGLE_LOC = By
			.cssSelector(".block.hidden-print>ul>li:nth-child(2) .normal");
	private final By SOCIAL_ICON_TWITTER_LOC = By
			.cssSelector(".block.hidden-print>ul>li:nth-child(3) .normal");
	private final By SOCIAL_ICON_INSTAGRAM_LOC = By
			.cssSelector(".block.hidden-print>ul>li:nth-child(4) .normal");
	private final By SOCIAL_ICON_PINTEREST_LOC = By
			.cssSelector(".block.hidden-print>ul>li:nth-child(5) .normal");
	// Top Banner Locators
	private final By COUNTRY_DROPDOWN_AERROW_LOC = By
			.cssSelector("#dLabel1 .caret");
	private final String COUNTY_DROPDOWN_VALUE_LOC = ".dropdown-menu>ul>li:nth-child(%d) a";
	// For Product Page
	// private final By OUR_MENU_DROPDOWN_LOC = By
	// .cssSelector(".col-holder div:nth-child(1) ul>li:nth-child(1) a>span");
	private final By OUR_MENU_DROPDOWN_LOC = By
			.xpath("//div[@id='bs-example-navbar-collapse-1']/div/div[1]/ul/li[1]/a");

	private final By OUR_MENU_FIRST_PRODUCT = By
			.cssSelector(".col-holder div:nth-child(1) ul>li:nth-child(1) .col-holder  div:nth-child(1) ul>li:nth-child(1) a");
	private final By OUR_MENU_IMG_LOC = By
			.cssSelector(".list-inline>li:nth-child(3) a>div>div>span:nth-child(1) span>img");
	private final By OUR_MENU_LOC = By
			.cssSelector(".list-inline>li:nth-child(3) a>em");
	private final By BREADCRUMB_LOC = By.cssSelector(".breadcrumb");
	private final By ON_BANNER_OUR_MENU_LOC = By.cssSelector(".auto-holder>h1");
	private final By OUR_MENU_DESC_LOC = By
			.xpath("//main[@id='main']/section[1]/div/div/div");
	private final By SHEF_SPECIAL_TITLE_LOC=By.xpath("//main[@id='main']/div[2]/section[2]/div/div/div/div/h2");
	

	private final By CHEF_SPECIALS_IMG_LOC = By
			.cssSelector(".list-inline>li:nth-child(2) a>div>div>span:nth-child(1) span>img");
	private final By CHEF_SPECIAL_LOC = By
			.cssSelector(".list-inline>li:nth-child(2) a>em");

	public void verifyPage() {
		commonMethods.verifyPresent(HOMEPAGE_LOC, "Home Page");
	}

	public void clickForNoAction() {
		click(By.cssSelector(".visual .holder"));
	}

	public void verifyUserInterface() {
		commonMethods.verifyPresent(LEFT_COL_TWIT_FOLLOW_US_LOC,
				"FOLLOW US ON TWITTER");
		commonMethods.verifyPresent(MID_COL_PRET_FOLLOW_US_LOC, "PRET");
		commonMethods.verifyPresent(RIGHT_COL_FACEBOOK_FOLLOW_US_LOC,
				"FOLLOW US ON FACEBOOK");
		commonMethods.verifyPresent(LEFT_TWIT_LOGO_LOC, "Twitter logo");
		commonMethods.verifyPresent(MID_PRET_LOGO_LOC, "PRET logo");
		commonMethods.verifyPresent(RIGHT_FACEBOOK_LOGO_LOC, "Facebook logo");
		commonMethods.verifyPresent(LEFT_COL_TWIT_DESC_LOC,
				"Who knew quinoa was related to beetroot and tumbleweed?");
		commonMethods.verifyPresent(RIGHT_COL_FACEBOOK_DESC_LOC,
				"Pret A Manger’s Genuine, bona fide Facebook");
		commonMethods.verifyPresent(LEFT_TWIT_HYPERPER_LIC_LOC,
				"FOLLOW US ON TWITTER");
		commonMethods.verifyPresent(RIGHT_FACEBOOK_HYPERPER_LIC_LOC,
				"FOLLOW US ON FACEBOOK");
	}

	public void clickOnTwitterHyperLink() {
		waitForElementToLoad(LEFT_TWIT_HYPERPER_LIC_LOC, timeout);
		click(LEFT_TWIT_HYPERPER_LIC_LOC);
		sleep(3000);
		commonMethods.switchToNewWindow();
		commonMethods.waitForElementToBePresent(TWITER_PRET_LOGO_LOC, timeout);
		webDriver.close();
		commonMethods.switchToNewWindow();
	}

	public void clickOnFacebookIcon() {
		waitForElementToLoad(RIGHT_FACEBOOK_LOGO_LOC, timeout);
		click(RIGHT_FACEBOOK_LOGO_LOC);
		sleep(3000);
		commonMethods.switchToNewWindow();
		commonMethods
				.waitForElementToBePresent(FACEBOOK_PRET_LOGO_LOC, timeout);
		webDriver.close();
		commonMethods.switchToNewWindow();
	}

	public void clickOnTwitterLogo() {
		waitForElementToLoad(LEFT_TWIT_LOGO_LOC, timeout);
		click(LEFT_TWIT_LOGO_LOC);
		sleep(3000);
		commonMethods.switchToNewWindow();
		commonMethods.waitForElementToBePresent(TWITER_PRET_LOGO_LOC, timeout);
		webDriver.close();
		commonMethods.switchToNewWindow();
	}

	public void clickOnMidColPretLogo() {
		waitForElementToLoad(MID_PRET_LOGO_LOC, timeout);
		click(MID_PRET_LOGO_LOC);
		commonMethods.waitForAjaxtoComplete();
	}

	public void clickOnTwitIconAndHyperlink() {
		clickOnTwitterLogo();
		waitForElementToLoad(LEFT_TWIT_HYPERPER_LIC_LOC, timeout);
		click(LEFT_TWIT_HYPERPER_LIC_LOC);
		sleep(3000);
		commonMethods.switchToNewWindow();
		commonMethods.waitForElementToBePresent(TWITER_PRET_LOGO_LOC, timeout);
	}

	public void verifySocialIcons() {
		commonMethods.verifyPresent(SOCIAL_ICON_F_BOOK_LOC, "Facebook logo");
		commonMethods.verifyPresent(SOCIAL_ICON_GOOGLE_LOC, "Google Plus logo");
		commonMethods.verifyPresent(SOCIAL_ICON_TWITTER_LOC, "Twitter logo");
		commonMethods
				.verifyPresent(SOCIAL_ICON_INSTAGRAM_LOC, "Instagram logo");
		commonMethods
				.verifyPresent(SOCIAL_ICON_PINTEREST_LOC, "Pinterest logo");
	}

	public void selectCountry() {
		click(COUNTRY_DROPDOWN_AERROW_LOC);
		sleep(3000);
		clickByCssSelector(String.format(COUNTY_DROPDOWN_VALUE_LOC, 1));
		commonMethods.loginPret();
		commonMethods.waitForAjaxtoComplete();
	}

	public void clickOnSocialSite() {
		waitForElementToLoad(SOCIAL_ICON_TWITTER_LOC, timeout);
		sleep(3000);
		click(SOCIAL_ICON_TWITTER_LOC);
		commonMethods.switchToNewWindow();
		commonMethods.waitForElementToBePresent(TWITER_PRET_LOGO_LOC, timeout);
		webDriver.close();
		commonMethods.switchToNewWindow();
	}

	public void verifySocialIconWithMouseHover() {
		moveToElement(findElement(SOCIAL_ICON_F_BOOK_LOC));
		commonMethods.verifyVisible(SOCIAL_ICON_F_BOOK_LOC,
				"Color Should be Blue");
		moveToElement(findElement(SOCIAL_ICON_GOOGLE_LOC));
		commonMethods.verifyVisible(SOCIAL_ICON_GOOGLE_LOC,
				"Color should be Red");
		moveToElement(findElement(SOCIAL_ICON_TWITTER_LOC));
		commonMethods.verifyVisible(SOCIAL_ICON_TWITTER_LOC,
				"Color should be sky Blue");
		moveToElement(findElement(SOCIAL_ICON_INSTAGRAM_LOC));
		commonMethods.verifyVisible(SOCIAL_ICON_INSTAGRAM_LOC,
				"Color Shoul be green");
		moveToElement(findElement(SOCIAL_ICON_PINTEREST_LOC));
		commonMethods.verifyVisible(SOCIAL_ICON_PINTEREST_LOC,
				"Color should be Red");
	}

	public void clickOnOurMenu() {
		moveToElement(findElement(OUR_MENU_DROPDOWN_LOC));
		click(OUR_MENU_DROPDOWN_LOC);
		click(OUR_MENU_FIRST_PRODUCT);
		commonMethods.waitForAjaxtoComplete();
	}

	public void verifyOurMenuBanner() {
		if (isElementPresented(OUR_MENU_IMG_LOC)) {
			commonMethods.verifyVisible(OUR_MENU_LOC, "Our Menu Banner....");
			click(OUR_MENU_LOC);
			commonMethods.waitForAjaxtoComplete();
		}
	}

	public void verifyBreadCrumbOnBanner() {
		commonMethods.verifyVisible(BREADCRUMB_LOC, "Home > Our Menu");
	}
	

	public void verifyChefSpecial() {
		if (isElementPresented(CHEF_SPECIALS_IMG_LOC)) {
			commonMethods
					.verifyVisible(CHEF_SPECIAL_LOC, "Our Menu Banner....");
			click(CHEF_SPECIAL_LOC);
			commonMethods.waitForAjaxtoComplete();
		}
	}
	public void verifySpecing(){
		commonMethods.verifyVisible(SHEF_SPECIAL_TITLE_LOC, "SPICY CHICKEN & AVOCADO SALAD WRAP");
	}

}
