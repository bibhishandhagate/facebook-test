package com.ecm.webautomation.pret.tests;

import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecm.webautomation.WebDriverTestNgHelper;
import com.ecm.webautomation.config.ConfigurationReader;
import com.ecm.webautomation.pret.common.CommonMethods;
import com.ecm.webautomation.pret.common.CommonMethods.CountryCode;
import com.ecm.webautomation.pret.common.CommonMethods.LanguageCode;
import com.ecm.webautomation.pret.common.FindAPretPage;
import com.ecm.webautomation.pret.dataBean.ParametersList;
import com.ecm.webautomation.pret.pages.HomePage;
import com.ecm.webautomation.pret.pages.SearchResultPage;

public class HomePageTestCases extends WebDriverTestNgHelper {
	private String validSearchText = ConfigurationReader
			.get(ParametersList.VALID_TEXT_SEARCH);
	private SearchResultPage searchResultPage;
	private FindAPretPage findAPretPage;
	private CommonMethods commonMethods;
	private HomePage homePage;

	@BeforeClass
	void setUp() {
		searchResultPage = new SearchResultPage(webDriver);
		commonMethods = new CommonMethods(webDriver);
		findAPretPage = new FindAPretPage(webDriver);
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

	@Test(description = "TC-PAM-95:HF10 - Header & Footer - Megamenu")
	void TC_PAM_91_HF_Header_Footer_SearchBar() {
		Assert.assertTrue(
				commonMethods.getSearchBoxDefaultValue().equals("Search"),
				"Default text \"Search\" is not present");
		commonMethods.getSuggestion(validSearchText);
		Assert.assertTrue(commonMethods.isSuggestionBoxDisplayed(),
				"Suggestion Box is not Displaying");
		commonMethods.clickOnSearchIcon();
		searchResultPage.verifySearchResultPage();
		webDriver.navigate().back();
		homePage.verifyPage();
		String[] invalidStrings = { "hdfdfs", "  ", "xx" };
		for (int i = 0; i < invalidStrings.length; i++) {
			commonMethods.searchForPhrase(invalidStrings[i]);
			searchResultPage.verifyNoSearchResultPage();
			webDriver.navigate().back();
		}
	}

	@Test(description = "TC-PAM-94:HF09 - Header & Footer - Breadcrumb")
	void TC_PAM_94_HF09_Header_Footer_Breadcrumb() {
		Assert.assertFalse(commonMethods.isBreadcrumbDisplayed(),
				"Bread Crumb is displaying at \"HOME PAGE\"");
		commonMethods.searchForPhrase(validSearchText);
		Assert.assertTrue(commonMethods.isBreadcrumbDisplayed(),
				"Bread Crumb is not displaying");
		commonMethods.clickOnBreadCrumbBarLink(1);
		Assert.assertFalse(commonMethods.isBreadcrumbDisplayed(),
				"Bread Crumb is displaying at \"HOME PAGE\"");
	}

	@Test(description = " TC-PAM-97:HF12 - Header & Footer - Newsletter signup")
	void TC_PAM_97_HF12_HeaderFooterNewsletterSignup() {
		LinkedList<String> emails = new LinkedList<>();
		emails.add("test.automation" + System.currentTimeMillis()
				+ "@ecommera.co.uk");
		emails.add("invalidEmailAddress");
		commonMethods.signUpNewsLetter(emails.getFirst());
		commonMethods.verifyNewsLetterSignUpSuccessMsg();
		commonMethods.signUpNewsLetter(emails.getLast());
		commonMethods.verifyNewsLetterSignUpErrorMsg();
		commonMethods.signUpNewsLetter(emails.getFirst());
		commonMethods.verifyNewsLetterSignUpSuccessMsg();
	}

	@Test(description = " TC-PAM-97:HF12 - Header & Footer - Newsletter signup")
	void TC_PAM_92_HF07HeaderFooterSuggestiveSearch() {
		commonMethods.getSuggestion(validSearchText);
		Assert.assertTrue(commonMethods.isSuggestionBoxDisplayed(),
				"Suggestion Box is not Displaying");
		commonMethods.moveCursorToLogo();
		Assert.assertTrue(commonMethods.isSuggestionBoxDisplayed(),
				"Suggestion Box is not Displaying");
		homePage.clickForNoAction();
		Assert.assertFalse(commonMethods.isSuggestionBoxDisplayed(),
				"Suggestion Box is Displaying");
		commonMethods.getSuggestion(validSearchText);
		commonMethods.clickOnSearchSuggestion(1);
		searchResultPage.verifySearchResultPage();
	}

	@Test(description = "TC-PAM-87:HF02 - Header & Footer - Country and language selector")
	void TC_PAM_87_HF02_HeaderNFooterCountryNLanguageSelector() {
		Assert.assertTrue(commonMethods.isCountrySelectorPresent(),
				"Country Selector Drop Down");
		if (commonMethods.getCountryCode().equals("?country=cn")
				|| commonMethods.getCountryCode().equals("?country=hk")) {
			Assert.assertTrue(commonMethods.isLanguageSelectorPresent(),
					"Language Selector Drop Down");
		}
		// clicking on all the country one by one from country selector and
		// verifying them for their default language
		for (int i = 0; i < commonMethods.countrySelectorGetCountryCount() + 1; i++) {
			String URL, selectedCountry;
			selectedCountry = commonMethods.countrySelectorGetCountryCode(1)
					.toLowerCase();
			String defaultLang = commonMethods.getCountryNDefaultLanguage()
					.get(selectedCountry).toLowerCase();
			commonMethods.selectCountryByIndex(1);
			URL = webDriver.getCurrentUrl().trim().toLowerCase();
			Assert.assertTrue(
					URL.contains(selectedCountry) && URL.contains(defaultLang),
					"URL does not cantain Country: " + selectedCountry
							+ " with it's default language: ");
		}

		if (webDriver.getCurrentUrl().contains(CountryCode.CHINA.getValue())) {
			commonMethods.selectCountry(CountryCode.HONG_KONG.getValue());
		} else {
			commonMethods.selectCountry(CountryCode.CHINA.getValue());
		}
		commonMethods.selectLanguage(LanguageCode.ENGLISH_GB.getValue());
		Assert.assertTrue(
				webDriver.getCurrentUrl().contains(
						LanguageCode.ENGLISH_GB.getValue().toLowerCase()),
				"URL does not contain language: "
						+ LanguageCode.ENGLISH_GB.getValue());
		if (webDriver.getCurrentUrl().contains(
				CountryCode.UNITED_KINGDOM.getValue())) {
			commonMethods.selectCountry(CountryCode.UNITED_STATES.getValue());
		} else {
			commonMethods.selectCountry(CountryCode.UNITED_KINGDOM.getValue());
		}
		String URL1 = webDriver.getCurrentUrl();
		Assert.assertTrue(URL1.contains(LanguageCode.ENGLISH_GB.getValue()
				.toLowerCase())
				|| URL1.contains(LanguageCode.ENGLISH_US.getValue()
						.toLowerCase()));
		Assert.assertFalse(commonMethods.isLanguageSelectorPresent(),
				"Language Selector Drop Down is Present");

	}

	@Test(description = "TC-PAM-88:HF03 - Header & Footer - Header Links")
	void TC_PAM_88_HF03_Header_N_Footer_HeaderLinks() {
		commonMethods.verifyHeaderSection();
		commonMethods.clickOnFindAPretLink();
		findAPretPage.verifyPage();
		LinkedList<String> emails = new LinkedList<>();
		emails.add("test.automation" + System.currentTimeMillis()
				+ "@ecommera.co.uk");
		emails.add("  ");
		emails.add("uniçode.chçracter@gmail.com");
		commonMethods.signUpNewsLetter(emails.getFirst());
		commonMethods.verifyNewsLetterSignUpSuccessMsg();
		commonMethods.signUpNewsLetter(emails.getLast());
		commonMethods.verifyNewsLetterSignUpSuccessMsg();
		commonMethods.signUpNewsLetter(emails.get(1));
		commonMethods.verifyNewsLetterSignUpErrorMsg();
	}

	@Test(description = "TC-PAM-90:HF05 - Header & Footer - Pret logo")
	void TC_PAM_90_HF05_HeaderNFooter_PretLogo() {
		commonMethods.verifyLogo(false);
		commonMethods.searchForPhrase(validSearchText);
		searchResultPage.verifySearchResultPage();
		commonMethods.verifyLogo(false);
		commonMethods.scrollDown();
		commonMethods.verifyLogo(true);
		commonMethods.clickOnPretLogo(true);
		homePage.verifyPage();
		commonMethods.verifyLogo(false);
		homePage.verifyPage();
	}

	@Test(description = "TC-PAM-104:HME07 - Homepage - Module 4 (Follow Us Links)")
	void TC_PAM_104_HME07_Homepage_Module4() {
		// This testcase will run only UK and US
		homePage.verifyUserInterface();
		homePage.clickOnFacebookIcon();
		homePage.clickOnTwitterHyperLink();
		commonMethods.clickOnPretLogo(true);
		homePage.clickOnMidColPretLogo();
		homePage.clickOnTwitIconAndHyperlink();

	}

	@Test(description = "TC-PAM-89:HF04 - Header & Footer - Social Icons (Follow Us)")
	void TC_PAM_89_HF04_HeaderNFooter_Social_Icons() {
		homePage.verifySocialIcons();
		homePage.selectCountry();
		homePage.verifySocialIcons();
		homePage.clickOnSocialSite();
		homePage.verifySocialIconWithMouseHover();
	}

	@Test(description = "TC-PAM-145:MEN01 - Our Menu - Module (Our Menu Banner)")
	void TC_PAM_145_MEN01_Our_Menu_Module() {
		homePage.verifyOurMenuBanner();
		homePage.verifyBreadCrumbOnBanner();
		//
	}

	@Test(description = "TC-PAM-148:MEN04 - Our Menu - Module (Chef’s Specials)")
	void TC_PAM_148_MEN04_Our_Menu_Module() {
		homePage.verifyChefSpecial();
		
	}

}
