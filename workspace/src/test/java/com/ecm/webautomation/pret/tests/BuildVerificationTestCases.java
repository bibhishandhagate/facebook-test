package com.ecm.webautomation.pret.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ecm.webautomation.WebDriverTestNgHelper;
import com.ecm.webautomation.config.ConfigurationReader;
import com.ecm.webautomation.pret.common.CommonMethods;
import com.ecm.webautomation.pret.common.FindAPretPage;
import com.ecm.webautomation.pret.common.MegaMenuSubCatPages;
import com.ecm.webautomation.pret.common.CommonMethods.CountryCode;
import com.ecm.webautomation.pret.common.CommonMethods.LanguageCode;
import com.ecm.webautomation.pret.common.CommonMethods.SocialMediaLogo;
import com.ecm.webautomation.pret.dataBean.ParametersList;
import com.ecm.webautomation.pret.dataBean.PropFileLoader;
import com.ecm.webautomation.pret.pages.HomePage;
import com.ecm.webautomation.pret.pages.SearchResultPage;

public class BuildVerificationTestCases extends WebDriverTestNgHelper {
	private String validSearchText = ConfigurationReader
			.get(ParametersList.VALID_TEXT_SEARCH);
	private SearchResultPage searchResultPage;
	private FindAPretPage findAPretPage;
	private CommonMethods commonMethods;
	private HomePage homePage;
	private MegaMenuSubCatPages megaMenuSubCatPages;
	private String workingAtPrethrefText = PropFileLoader
			.getText("workingatpret.href.text");

	@BeforeClass
	void setUp() {
		searchResultPage = new SearchResultPage(webDriver);
		commonMethods = new CommonMethods(webDriver);
		findAPretPage = new FindAPretPage(webDriver);
		homePage = new HomePage(webDriver);
		megaMenuSubCatPages = new MegaMenuSubCatPages(webDriver);
		commonMethods.deleteAllCookies();
		commonMethods.windowMaximize();
		commonMethods.openRootBasedUrl(commonMethods.getCountryCode());
		commonMethods.loginPret();
	}

	@BeforeMethod
	void beforeMethod() {
		commonMethods.beforeMethod();
	}

	@Test(description = " TC-PAM-192:Logo")
	void TC_PAM_192_Logo() {
		commonMethods.verifyLogo(false);
		commonMethods.searchForPhrase(validSearchText);
		commonMethods.clickOnPretLogo(false);
		homePage.verifyPage();
		commonMethods.searchForPhrase(validSearchText);
		searchResultPage.viewProduct(1);
		commonMethods.clickOnPretLogo(false);
		homePage.verifyPage();
	}

	@Test(description = "TC-PAM-195:Cookie policy")
	void TC_PAM_195_Cookie_Policy() {
		commonMethods.verifyCookiePolicyMsgVisible();
	}

	@Test(description = "TC-PAM-196:Country and Language selector")
	void TC_PAM_196_Country_N_LanguageSelector() {
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

	@Test(description = "TC-PAM-197:Header links")
	void TC_PAM_197_HeaderLinks() {
		commonMethods.verifyHeaderSection();
	}

	@Test(description = "TC-PAM-198:Social icons")
	void TC_PAM_198_SocialIcons() {
		for (SocialMediaLogo mediaIcon : SocialMediaLogo.values()) {
			commonMethods.clickOnSocialMedia(mediaIcon.getValue());
			commonMethods.switchToNewWindow();
			commonMethods.verfySocialMediaPage(mediaIcon.getValue());
			webDriver.close();
			commonMethods.switchToNewWindow();
		}
	}

	@Test(description = "TC-PAM-201:Mega menu")
	void TC_PAM_201_MegaMenu() {
		Assert.assertTrue(commonMethods.isMegaMenuOurMenu(1),
				"First Mega Menu is not 'OUR MENU'");
	}

	@Test(description = "TC-PAM-204:Campaign monitor")
	void TC_PAM_204_CampaignMonitor() {
		commonMethods.verifyNewsLetterSignDisplayed();
	}

	@Test(description = "TC-PAM-205:Standard Soup page")
	void TC_PAM_205_StandardSoupPage() {
		commonMethods.clickOnMegaMenuSubCat(true, 1, "soup");
		megaMenuSubCatPages.verifyIsPageLoaded("soup");
	}

	@Test(description = "TC-PAM-210:Product Listing Page-Module")
	void TC_PAM_210_ProductListingPageModule() {
		commonMethods.clickOnMegaMenuSubCat(true, 1, "soup");
		megaMenuSubCatPages.verifyIsPageLoaded("soup");
	}

	@Test(description = "TC-PAM-210:Product Listing Page-Module")
	void TC_PAM_216_WorkForPretModule() {
		commonMethods.clickOnMegaMenuFirstSubCat(false, 2);
		megaMenuSubCatPages.verifyIsPageLoaded(workingAtPrethrefText);
	}
}
