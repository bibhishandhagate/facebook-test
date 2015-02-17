package com.ecm.webautomation.pret.common;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.config.ConfigurationReader;
import com.ecm.webautomation.pret.dataBean.ParametersList;

public class CommonMethods extends WebDriverModule {

	public CommonMethods(WebDriver webDriver) {
		super(webDriver);
	}

	private String storeFrontHost = ConfigurationReader.get("storefront.host")
			.trim();

	private final By HEADER_LOGIN_LINK_LOC = By.cssSelector("#SignUpBtn>em");
	// private final By FINDAPRET_LINK_LOC = By
	// .cssSelector("[href*='ShopFinder']>em");
	private final By FINDAPRET_LINK_LOC = By
			.cssSelector(".find.hidden-xs>a>em");
	private final By HEADER_SIGNOUT_LINK_LOC = By.cssSelector(".welcome>a");
	private final By HEADER_SEARCHBOX_LOC = By.id("inptHeaderSearch");
	private final By SEARCH_ICON_LOC = By.id("btnHeaderSearch");
	private final By SEARCHBOX_DEFAULTVALUE_LOC = By
			.xpath("//*[@id='inptHeaderSearch']/../span");
	private final By HEADER_REGISTER_LINK_LOC = By.id("signIUp");
	private final String TOPNAVIGATION_LINK_LOC = "a[title='%s']";
	private final String FOOTER_LINK_LOC = "#footer-content-links a[href*='%s']";
	private final By FOOTER_EXPAND_LOC = By.className("upDownImg");
	private final String THIRDlEVEL_CAT_LOC = "a[title='%s']~div .third-level-link[href*='%s/%s']";
	private final By STORELOCATOR_LINK_LOC = By
			.cssSelector("a[title*='Store Locator']");
	private final By SUGGESTION_BOX_LOC = By
			.cssSelector("#colorbox+[class*='ui-autocomplete']");
	private final By BREADCRUMB_BAR_LOC = By.className("breadcrumb");
	private final String BREADCRUMB_LINK_LOC = ".breadcrumb>li:nth-child(%d)>a";
	private final By SIGNUP_TEXTFIELD_LOC = By.id("fieldEmail");
	private final By SIGNUP_BTN_LOC = By.id("btnCampaignRegister");
	private final By SIGNUP_SUCCESSMSG_LOC = By
			.cssSelector(".messageBox #divMessage");
	private final By SIGNUP_ERRORMSG_LOC = By
			.cssSelector("#SignUpHolder .messageBox #divError");
	private final String SEARCH_SUGGESTION_LOC = "[id*='ui-id-'][class='ui-menu-item']:nth-of-type(%d)";
	private final By SEARCH_SUGGESTIONS_LOC = By
			.cssSelector("[id*='ui-id-'][class='ui-menu-item']");
	private final By PRET_LOGO_LOC = By.cssSelector(".logo>a");
	private final By PRET_FIXEDLOGO_LOC = By.className("fixed-logo");
	private final By LANGUAGESELECTOR_DROPDOWN_LOC = By.id("dLabel2");
	private final By COUNTRYSELECTOR_DROPDOWN_LOC = By.id("dLabel1");
	private final String COUNTRYSELECTOR_DROPDOWN_VALUE_LOC = "#dLabel1+div .list-unstyled>li>a[href*='%s']";
	private final By COUNTRYSELECTOR_DROPDOWN_VALUES_LOC = By
			.cssSelector("#dLabel1+div .list-unstyled>li>a");
	private final String COUNTRYSELECTOR_DROPDOWN_VALUEBYINDEX_LOC = "#dLabel1+div .list-unstyled>li:nth-child(%d) a";
	private final String LANGUAGESELECTOR_DROPDOWN_VALUEBYINDEX_LOC = "#dLabel2+div .list-unstyled>li:nth-child(%d) a";
	private final String LANGUAGESELECTOR_DROPDOWN_VALUE_LOC = "#dLabel2+div .list-unstyled>li>a[href*='%s']";
	private static final By COOKIEPOLICY_MSG_LOC = By
			.cssSelector("[class*='message-bar'] .text");
	private static final String HEADER_SOCIALMEDIA_LOGO_LOC = "#header img[alt*='%s'].normal";
	private static final String LEFTMEGAMENU_LINK_LOC = "[class='nav navbar-nav'] .drop-item:nth-child(%d)";
	private static final String LEFTMEGAMENU_FIRSTSUBCAT_LINK_LOC = "[class='nav navbar-nav'] .drop-item:nth-child(%d) div a";
	private static final String LEFTMEGAMENU_SUBCAT_LINK_LOC = "[class='nav navbar-nav'] .drop-item:nth-child(%d) div a[href*='%s']";
	private static final String RIGHTMEGAMENU_LINK_LOC = ".nav.navbar-nav.navbar-right .drop-item:nth-child(%d)";
	private static final String RIGHTMEGAMENU_FIRSTSUBCAT_LINK_LOC = ".nav.navbar-nav.navbar-right .drop-item:nth-child(%d) div a";
	private static final String RIGHTMEGAMENU_SUBCAT_LINK_LOC = ".nav.navbar-nav.navbar-right .drop-item:nth-child(%d) div a[href*='%s']";

	private int miliseconds = Integer.parseInt(ConfigurationReader
			.get(ParametersList.TIMEOUT_MILISECOND));
	private int timeOutInSeconds = Integer.parseInt(ConfigurationReader
			.get(ParametersList.TIMEOUT_SECOND));
	private String passText = "PASS";
	private String failText = "FAIL";

	public void verifyNotVisible(By locator, String objectDescription) {
		if (!webDriver.findElement(locator).isDisplayed()) {
			Reporter.log(passText + objectDescription + " is not visible.<br>");
		} else {
			Reporter.log(failText + objectDescription + " is visible.<br>");
			Assert.assertFalse(true);
		}
	}

	public void verifyVisible(By locator, String objectDescription) {
		if (webDriver.findElement(locator).isDisplayed()) {
			Reporter.log(passText + objectDescription + " is visible.<br>");
		} else {
			Reporter.log(failText + objectDescription + " is not visible.<br>");
			Assert.assertTrue(false, failText + objectDescription
					+ " is not visible.");
		}
	}

	public void verifyPresent(By locator, String objectDescription) {
		if (isElementPresented(locator)) {
			Reporter.log(passText + objectDescription + " is present.<br>");
		} else {
			Reporter.log(failText + objectDescription + " is not present.<br>");
			Assert.assertFalse(true, failText + objectDescription
					+ " is not present.");
		}
	}

	public void verifyNotPresent(By locator, String objectDescription) {
		if (!isElementPresented(locator)) {
			Reporter.log(passText + objectDescription + " is not present.<br>");
		} else {
			Reporter.log(failText + objectDescription + " is present.<br>");
			Assert.assertFalse(true, failText + objectDescription
					+ " is present.");
		}
	}

	public void headerClickOnLogInLink(boolean hasLoggedIn) {
		if (hasLoggedIn) {
			click(HEADER_SIGNOUT_LINK_LOC);
		} else {
			click(HEADER_LOGIN_LINK_LOC);
		}
	}

	public void headerClickOnRegisterLink() {
		click(HEADER_REGISTER_LINK_LOC);
	}

	public boolean isCustomerLoggedOut() {
		return isElementPresented(HEADER_LOGIN_LINK_LOC);
	}

	public void searchForPhrase(String phrase) {
		String url = webDriver.getCurrentUrl();
		waitForElementToLoad(HEADER_SEARCHBOX_LOC, timeOutInSeconds);
		typeByLocatorAndPressEnter(HEADER_SEARCHBOX_LOC, phrase);
		waitForURLChange(url);
	}

	public void clickOnSearchIcon() {
		click(SEARCH_ICON_LOC);
	}

	public void getSuggestion(String phrase) {
		waitForElementToLoad(HEADER_SEARCHBOX_LOC, timeOutInSeconds);
		typeByLocator(HEADER_SEARCHBOX_LOC, phrase);
	}

	public void focus(By locator) {
		((JavascriptExecutor) webDriver).executeScript("arguments[0].focus()",
				findElement(locator));
	}

	public void getScrolDown() {
		((JavascriptExecutor) webDriver).executeScript("return window.title;");
		((JavascriptExecutor) webDriver).executeScript(
				"window.scrollBy(0,3000)", "");
	}

	public enum TopNavigation {

		SHOP_MAN("Shop Man"), SHOP_WOMAN("Shop Woman"), BLOG("Blog"), WORLD_OF_BALLY(
				"EXPLORE BALLY"), COLLECTIONS("Collections");

		private String value;

		private TopNavigation(String value) {
			this.value = value;
		}

		public String getNavigation() {
			return value;
		}
	}

	public enum CountryCode {

		UNITED_KINGDOM("country=uk"), UNITED_STATES("country=us"), CHINA(
				"country=cn"), HONG_KONG("country=hk"), FRANCE("country=fr");

		private String value;

		private CountryCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum LanguageCode {

		CHINESE_CHINA("zh-CN"), CHINESE_HK("zh-HK"), FRANCE("fr-FR"), ENGLISH_GB(
				"en-GB"), ENGLISH_US("en-US");

		private String value;

		private LanguageCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum SecondaryCategory {

		SHOES("shoes"), BAGS("bags"), ACCESSORIES("accessories"), READY_TO_WEAR(
				"ready%20to%20wear");

		private String value;

		private SecondaryCategory(String value) {
			this.value = value;
		}

		public String getCategory() {
			return value;
		}
	}

	public enum SocialMediaLogo {
		FACEBOOK("Facebook"), GOOGLE_PLUS("Google"), TWITTER("Twitter"), INSTAGRAM(
				"Instagram"), PINTEREST("Pinterest");
		private String socialMedia;

		private SocialMediaLogo(String socialMedia) {
			this.socialMedia = socialMedia;
		}

		public String getValue() {
			return socialMedia;
		}
	}

	public enum ThirdCategory {

		BOOTS_N_BOOTIES("boots"), LACE_UPS("lace");

		private String value;

		private ThirdCategory(String value) {
			this.value = value;
		}

		public String getCategory() {
			return value;
		}
	}

	public void clickOnTopNavigationLink(String linkName) {
		clickByCssSelector(String.format(TOPNAVIGATION_LINK_LOC, linkName));
	}

	public enum FooterLink {

		STORE_LOCATOR("Stores-Find"), ASSISTANCE("cs-landing"), LEGAL_AREA(
				"legal-area"), RETURNS_POLICY("legal-area"), CONTACT_US(
				"CustomerService-ContactUs"), FAQ("faq");

		private String value;

		private FooterLink(String value) {
			this.value = value;
		}

		public String getLink() {
			return value;
		}
	}

	public void clickOnFooterLink(String linkName) {
		click(FOOTER_EXPAND_LOC);
		if (linkName.equals(FooterLink.FAQ.getLink())) {
			clickByCssSelector(String.format(FOOTER_LINK_LOC,
					FooterLink.ASSISTANCE.getLink()));
			switchToNewWindow();
			clickByCssSelector(String.format("a[href*='%s']",
					FooterLink.FAQ.getLink()));
		} else {
			click(By.cssSelector(String.format(FOOTER_LINK_LOC, linkName)));
			switchToNewWindow();
		}
	}

	public void switchToNewWindow() {
		Set<String> handles = webDriver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		// iterate through your windows
		while (it.hasNext()) {
			String newWin = it.next();
			webDriver.switchTo().window(newWin);
		}
	}

	public void switchOffNewWindow() {
		webDriver.close();
		switchToNewWindow();
	}

	public void clickOnThirdLevelCategory(String primaryCat,
			String secondaryCat, String thirdCat) {
		moveToElement(findElement(By.cssSelector(String.format(
				TOPNAVIGATION_LINK_LOC, primaryCat))));
		sleep(miliseconds);
		clickByCssSelector(String.format(THIRDlEVEL_CAT_LOC, primaryCat,
				secondaryCat, thirdCat));
	}

	public void getStoreLocatorLandingPage() {
		click(STORELOCATOR_LINK_LOC);
	}

	public void scrollDown() {
		((JavascriptExecutor) webDriver).executeScript("return window.title;");
		((JavascriptExecutor) webDriver).executeScript(
				"window.scrollBy(0,3000)", "");
	}

	public void beforeMethod() {
		openRootBasedUrl("");
	}

	public String getCountry() {
		String countryCode = storeFrontHost.substring(4, 11);
		return countryCode;
	}

	public String getCountryCode() {
		String countryCode = "?country=uk";
		switch (getCountry()) {
		case "pret-us":
			countryCode = "?country=us";
			break;
		case "pret-cn":
			countryCode = "?country=cn";
			break;
		case "pret-hk":
			countryCode = "?country=hk";
			break;
		case "pret-fr":
			countryCode = "?country=fr";
			break;
		}
		return countryCode;
	}

	public String getSearchBoxDefaultValue() {
		return getTextFromLocator(SEARCHBOX_DEFAULTVALUE_LOC).trim();
	}

	public boolean isSuggestionBoxDisplayed() {
		sleep(3000);
		return isElementDisplayed(SUGGESTION_BOX_LOC);
	}

	public boolean isBreadcrumbDisplayed() {
		return isElementDisplayed(BREADCRUMB_BAR_LOC);
	}

	public void clickOnBreadCrumbBarLink(int linkNum) {
		clickByCssSelector(String.format(BREADCRUMB_LINK_LOC, linkNum));
	}

	public void signUpNewsLetter(String email) {
		typeByLocator(SIGNUP_TEXTFIELD_LOC, email);
		click(SIGNUP_BTN_LOC);
	}

	public void verifyNewsLetterSignDisplayed() {
		verifyVisible(SIGNUP_TEXTFIELD_LOC, "News Letter Sign Up");
	}

	public void verifyNewsLetterSignUpSuccessMsg() {
		verifyVisible(SIGNUP_SUCCESSMSG_LOC,
				"News Letter Sign Up Success Message");
	}

	public void verifyNewsLetterSignUpErrorMsg() {
		verifyVisible(SIGNUP_ERRORMSG_LOC, "News Letter Sign Up Error Message");
	}

	public void loginPret() {
		if (isElementDisplayed(By.id("txtName"))) {
			typeByLocator(By.id("txtName"), "PretAManger");
			typeByLocator(By.id("txtPassword"), "pam123");
			click(By.id("btnSubmit"));
			sleep(5000);
		}
	}

	public String getSearchSuggestion(int index) {
		return getTextFromLocator(
				By.cssSelector(String.format(SEARCH_SUGGESTION_LOC, index + 1)))
				.trim();
	}

	public int getSearchSuggestionCount() {
		return findElements(SEARCH_SUGGESTIONS_LOC).size();
	}

	public void clickOnSearchSuggestion(int index) {
		clickByCssSelector(String.format(SEARCH_SUGGESTION_LOC, index + 1));
	}

	public void moveCursorToLogo() {
		moveToElement(findElement(PRET_LOGO_LOC));
	}

	public void clickOnPretLogo(boolean isFixed) {
		if (isFixed) {
			click(PRET_FIXEDLOGO_LOC);
		} else {
			click(PRET_LOGO_LOC);
		}
	}

	public void verifySearchBox() {
		verifyVisible(HEADER_SEARCHBOX_LOC, "Search Box");
		verifyVisible(SEARCH_ICON_LOC, "Magnifying Glass Icon in Search Box");
	}

	public boolean isLanguageSelectorPresent() {
		return isElementPresented(LANGUAGESELECTOR_DROPDOWN_LOC);
	}

	public boolean isCountrySelectorPresent() {
		return isElementPresented(COUNTRYSELECTOR_DROPDOWN_LOC);
	}

	public void selectCountry(String countryCode) {
		click(COUNTRYSELECTOR_DROPDOWN_LOC);
		click(By.cssSelector(String.format(COUNTRYSELECTOR_DROPDOWN_VALUE_LOC,
				countryCode)));
		loginPret();
	}

	public void selectLanguage(String languageCode) {
		click(LANGUAGESELECTOR_DROPDOWN_LOC);
		click(By.cssSelector(String.format(LANGUAGESELECTOR_DROPDOWN_VALUE_LOC,
				languageCode)));
	}

	public void selectCountryByIndex(int index) {
		click(COUNTRYSELECTOR_DROPDOWN_LOC);
		click(By.cssSelector(String.format(
				COUNTRYSELECTOR_DROPDOWN_VALUEBYINDEX_LOC, index)));
		loginPret();
	}

	public void selectLanguageByIndex(int index) {
		click(LANGUAGESELECTOR_DROPDOWN_LOC);
		click(By.cssSelector(String.format(
				LANGUAGESELECTOR_DROPDOWN_VALUEBYINDEX_LOC, index)));
	}

	public String countrySelectorGetCountryCode(int index) {
		String countryCode = findElement(
				By.cssSelector(String.format(
						COUNTRYSELECTOR_DROPDOWN_VALUEBYINDEX_LOC, index)))
				.getAttribute("href").trim().split("//")[2];
		return countryCode;
	}

	public String languageSelectorGetLanguageCode(int index) {
		String languageCode = findElement(
				By.cssSelector(String.format(
						LANGUAGESELECTOR_DROPDOWN_VALUEBYINDEX_LOC, index)))
				.getAttribute("href").split("lang=")[1].split("&")[0];
		return languageCode;
	}

	public LinkedHashMap<String, String> getCountryNDefaultLanguage() {
		LinkedHashMap<String, String> countryNLocale = new LinkedHashMap<>();
		countryNLocale.put("?country=uk", "en-gb");
		countryNLocale.put("?country=us", "en-us");
		countryNLocale.put("?country=cn", "zh-cn");
		countryNLocale.put("?country=hk", "zh-hk");
		countryNLocale.put("?country=fr", "fr-fr");
		return countryNLocale;
	}

	public int countrySelectorGetCountryCount() {
		return findElements(COUNTRYSELECTOR_DROPDOWN_VALUES_LOC).size();
	}

	public void verifyHeaderSection() {
		verifyPresent(FINDAPRET_LINK_LOC, "Find A Pret Link");
		verifyPresent(HEADER_LOGIN_LINK_LOC, "Sign In Link");
	}

	public void clickOnFindAPretLink() {
		click(FINDAPRET_LINK_LOC);
	}

	public void verifyLogo(boolean isFixed) {
		if (isFixed) {
			verifyVisible(PRET_FIXEDLOGO_LOC, "Pret Fixed Logo");
		} else {
			verifyVisible(PRET_LOGO_LOC, "Pret Logo");
		}
	}

	public void verifyCookiePolicyMsgVisible() {
		verifyVisible(COOKIEPOLICY_MSG_LOC, "Cookie Policy Msg");
	}

	public void clickOnSocialMedia(String socialMedia) {
		clickByCssSelector(String.format(HEADER_SOCIALMEDIA_LOGO_LOC,
				socialMedia));
		sleep(miliseconds);
	}

	public void verfySocialMediaPage(String socialMedia) {
		String currenUrl = webDriver.getCurrentUrl().trim().toLowerCase();
		if (socialMedia.equals("Google"))
			currenUrl = currenUrl + "pret";
		Assert.assertTrue(currenUrl.contains(socialMedia.toLowerCase())
				&& currenUrl.contains("pret"), "On Click " + socialMedia
				+ " icon, URL:" + currenUrl + " is opening.");
	}

	public void mouseHoverToMegaMenu(boolean isLeft, int MenuSeq) {
		if (isLeft) {
			moveToElement(findElement(By.cssSelector(String.format(
					LEFTMEGAMENU_LINK_LOC, MenuSeq))));
		} else {
			moveToElement(findElement(By.cssSelector(String.format(
					RIGHTMEGAMENU_LINK_LOC, MenuSeq))));
		}
	}

	public boolean isMegaMenuOurMenu(int leftMenuSeq) {
		moveToElement(findElement(By.cssSelector(String.format(
				LEFTMEGAMENU_LINK_LOC, leftMenuSeq))));
		return findElement(
				By.cssSelector(String.format(LEFTMEGAMENU_FIRSTSUBCAT_LINK_LOC,
						leftMenuSeq))).getAttribute("href").contains(
				"categories");
	}

	public void clickOnMegaMenuSubCat(boolean isLeft, int menuSeq, String subCat) {
		if (isLeft) {
			moveToElement(findElement(By.cssSelector(String.format(
					LEFTMEGAMENU_LINK_LOC, menuSeq))));
			clickByCssSelector(String.format(LEFTMEGAMENU_SUBCAT_LINK_LOC,
					menuSeq, subCat));
		} else {
			moveToElement(findElement(By.cssSelector(String.format(
					RIGHTMEGAMENU_LINK_LOC, menuSeq))));
			clickByCssSelector(String.format(RIGHTMEGAMENU_SUBCAT_LINK_LOC,
					menuSeq, subCat));
		}

	}

	public String getTextFromMegaMenu(boolean isLeft, int menuSeq) {
		String menuText;
		if (isLeft) {
			menuText = getTextFromLocator(
					By.cssSelector(String
							.format(LEFTMEGAMENU_LINK_LOC, menuSeq))).trim();
		} else {
			menuText = getTextFromLocator(
					By.cssSelector(String.format(RIGHTMEGAMENU_LINK_LOC,
							menuSeq))).trim();
		}
		return menuText;
	}

	public String getTextFromMegaMenuFirstSubCat(boolean isLeft, int menuSeq) {
		String subCat;
		if (isLeft) {
			subCat = getTextFromLocator(
					By.cssSelector(String.format(
							LEFTMEGAMENU_FIRSTSUBCAT_LINK_LOC, menuSeq)))
					.trim();
		} else {
			subCat = getTextFromLocator(
					By.cssSelector(String.format(
							RIGHTMEGAMENU_FIRSTSUBCAT_LINK_LOC, menuSeq)))
					.trim();
		}
		return subCat;
	}

	public void clickOnMegaMenuFirstSubCat(boolean isLeft, int menuSeq) {
		if (isLeft) {
			click(By.cssSelector(String.format(
					LEFTMEGAMENU_FIRSTSUBCAT_LINK_LOC, menuSeq)));
		} else {
			click(By.cssSelector(String.format(
					RIGHTMEGAMENU_FIRSTSUBCAT_LINK_LOC, menuSeq)));
		}
	}
}
