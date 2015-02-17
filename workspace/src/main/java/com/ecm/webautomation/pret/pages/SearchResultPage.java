package com.ecm.webautomation.pret.pages;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.config.ConfigurationReader;
import com.ecm.webautomation.pret.common.CommonMethods;
import com.ecm.webautomation.pret.dataBean.ParametersList;

public class SearchResultPage extends WebDriverModule {

	private final By HEADER_SEARCHBOX_LOC = By.id("inptHeaderSearch");
	private final By SEARCH_ICON_LOC = By.id("btnHeaderSearch");
	private final String PRODUCT_QUICKVIEW_BTN_LOC = "#productGridContainer>div:nth-child(%d) .quicklookLink";
	private final By SEARCHFOUND_TEXT_LOC = By.className("search-found");
	private final By SORTBY_SELECTBOX_LOC = By.id("sortBy");
	private final String SALE_PRICE_LOC = ".productItem.searchResult:nth-child(%d) .price.current";
	private final String SORTING_OPTION_LOC = "#sortBy>option[value*='%s']";
	private final By PRODUCT_COUNT_LOC = By.className("primaryImg");
	private final String PRODUCT_NAME_LOC = ".productItem.searchResult:nth-child(%d) .productName";
	private final String FILTER_OPTION_LOC = ".refinementsList li:nth-child(%d)>a";
	private final String FILTER_OPTIONVALUE_LOC = ".refinementsList a[href*='%s']";
	private final By PRODUCTS_BOX_LOC = By.className("search-box");
	private final By MAIN_BANNER_IMAGE_LOC = By
			.cssSelector(".col-sm-6.img-holder>span>span>img");
	private final By MAIN_BANNER_TITLE_LOC = By.cssSelector(".col-sm-6>h1");
	private final By MAIN_BANNER_TEXT_LOC = By.cssSelector(".col-sm-6>h1");
	private final By MAIN_BANNER_DESC_LOC = By.cssSelector(".textbox>p");
	private final By BREADCRUMB_LOC = By.cssSelector(".breadcrumb");
	private final By SECOND_GRID_TITLE_LOC = By.cssSelector(".col-sm-12>h2");
	private final By SECOND_GRID_COL_LOC = By.cssSelector(".box>span>span>img");
	private final By SECOND_GRID_FIRST_COL_IMG_LOC = By
			.cssSelector(".row article:nth-child(1) img");
	private final By SECOND_GRID_FIRST_COL_TITLE_LOC = By
			.cssSelector(".row article:nth-child(1) .holder>h3");
	private final By SECOND_GRID_FIRST_COL_DESC_LOC = By
			.cssSelector(".row article:nth-child(1) .holder>p");
	private final By SECOND_GRID_FIRST_COL_BTN_LOC = By
			.cssSelector(".row article:nth-child(1) a>span");
	private final By SECOND_GRID_SEC_COL_IMG_LOC = By
			.cssSelector(".row article:nth-child(2) img");
	private final By SECOND_GRID_SEC_COL_TITLE_LOC = By
			.cssSelector(".row article:nth-child(2) .holder>h3");
	private final By SECOND_GRID_SEC_COL_DESC_LOC = By
			.cssSelector(".row article:nth-child(2) .holder>p");
	private final By SECOND_GRID_SEC_COL_BTN_LOC = By
			.cssSelector(".row article:nth-child(2) a>span");
	private final By SECOND_GRID_THIRD_COL_IMG_LOC = By
			.cssSelector(".row article:nth-child(3) img");
	private final By SECOND_GRID_THIRD_COL_TITLE_LOC = By
			.cssSelector(".row article:nth-child(3) .holder>h3");
	private final By SECOND_GRID_THIRD_COL_DESC_LOC = By
			.cssSelector(".row article:nth-child(2) .holder>p");
	private final By SECOND_GRID_THIRD_COL_BTN_LOC = By
			.cssSelector(".row article:nth-child(3) a>span");
	private final By ON_OUR_MENU_IMG_LOC = By
			.cssSelector(".search-result div:nth-child(2) span>span>img");
	private final By PRODUCT_TILTE_LOC = By
			.cssSelector(".search-result div:nth-child(2) ul>li>h4>a");
	private final By PRODUCT_IMAGE_LOC = By.cssSelector(".img-responsive");
	private String productName = "@$@@$";
	private String maxCharProduct = "12oz Camomile Tea";
	private String UpperLowerPro = "Coffee & Drinks";
	private final By ON_OUR_WEBSITE_SECTION_LOC = By
			.cssSelector(".section.section-result");
	private final By ON_OUR_WEBSITE_LOC = By.cssSelector(".col-sm-12>h2");
	private final By TRY_AGAIN_LOC = By.cssSelector(".col-xs-12>form>h2");
	private final By TRY_AGAIN_DES_LOC = By.cssSelector(".col-xs-12>form>p");
	private final By TRY_AGAIN_SEARCH_TEXT_BOX_LOC = By.cssSelector("");
	private final By TRY_AGAIN_GRID_BAR_LOC = By
			.cssSelector(".search-block.section.hidden-print");
	private static final String VIEWPRODUCT_LINK_LOC = ".search-result .search-box:nth-child(%d) .view";

	private CommonMethods commonMethods;
	private int miliseconds = Integer.parseInt(ConfigurationReader
			.get(ParametersList.TIMEOUT_MILISECOND));
	private int timeOutSec = Integer.parseInt(ConfigurationReader
			.get(ParametersList.TIMEOUT_SECOND));

	public SearchResultPage(WebDriver webDriver) {
		super(webDriver);
		commonMethods = new CommonMethods(webDriver);
	}

	public enum SortingOption {

		PRICE_LOW_TO_HIGH("price-low-to-high"), PRICE_HIGH_TO_LOW(
				"price-high-to-low"), BEST_MATCHES("best-matches"), PRODUCTNAME_A_TO_Z(
				"%20A%20-%20Z"), PRODUCTNAME_Z_TO_A("%20Z%20-%20A"), BEST_SELLER(
				"Sellers"), NEW_ARRIVAL("New%20Arrival");

		private String sortingOption;

		private SortingOption(String sortingOption) {
			this.sortingOption = sortingOption;
		}

		public String getSortingOption() {
			return sortingOption;
		}
	}

	public void clickOnProductImage(int imageSequence) {
		webDriver
				.findElements(
						By.cssSelector(".productName.text-uppercase>strong"))
				.get(imageSequence - 1).click();
		sleep(miliseconds);
	}

	public void selectSortByOption(String sortingOption) {
		click(SORTBY_SELECTBOX_LOC);
		clickByCssSelector(String.format(SORTING_OPTION_LOC, sortingOption));
		waitForAjaxtoComplete();
		sleep(miliseconds);
	}

	public double getProductSalePrice(int prodSequence) {
		String salePrice = getTextFromLocator(
				By.cssSelector(String.format(SALE_PRICE_LOC, prodSequence + 3)))
				.trim();
		if (salePrice.contains("-")) {
			salePrice = salePrice.split("-")[0];
		}

		return Double.parseDouble(salePrice.substring(1));

	}

	public int getProductCount() {
		return findElements(PRODUCT_COUNT_LOC).size();
	}

	public String getProductName(int productSequence) {
		return getTextFromLocator(
				By.cssSelector(String.format(PRODUCT_NAME_LOC,
						productSequence + 3))).trim();
	}

	public boolean verifySorting(String sortingOption) {
		boolean flag = true;
		double price_A = 0, price_B = 0;
		int i;
		if (sortingOption.equals(SortingOption.PRICE_LOW_TO_HIGH
				.getSortingOption())) {
			selectSortByOption(SortingOption.PRICE_LOW_TO_HIGH
					.getSortingOption());
			for (i = 1; i <= getProductCount() - 1; i++) {
				price_A = getProductSalePrice(i);
				price_B = getProductSalePrice(i + 1);
				if (price_A > price_B) {
					flag = false;
					Reporter.log("For low to high price sorting price: "
							+ price_A + " is comming first than price: "
							+ price_B);
					break;
				}
			}
		} else if (sortingOption.equals(SortingOption.PRICE_HIGH_TO_LOW
				.getSortingOption())) {
			selectSortByOption(SortingOption.PRICE_HIGH_TO_LOW
					.getSortingOption());
			for (i = 1; i <= getProductCount() - 1; i++) {
				price_A = getProductSalePrice(i);
				price_B = getProductSalePrice(i + 1);
				if (price_B > price_A) {
					flag = false;
					Reporter.log("For high to low price sorting price: "
							+ price_B + " is comming first than price: "
							+ price_A);
					break;
				}
			}
		} else if (sortingOption.equals(SortingOption.PRODUCTNAME_A_TO_Z
				.getSortingOption())) {
			selectSortByOption(SortingOption.PRODUCTNAME_A_TO_Z
					.getSortingOption());
			String[] sortedProductName = new String[getProductCount()];
			for (i = 1; i <= getProductCount(); i++) {
				sortedProductName[i - 1] = getProductName(i);
			}
			Arrays.sort(sortedProductName);
			for (i = 1; i <= getProductCount(); i++) {
				if (!sortedProductName[i - 1].equals(getProductName(i))) {
					flag = false;
					Reporter.log("According to sorting product name should be:"
							+ sortedProductName[i - 1] + " /n but it is:"
							+ getProductName(i));
					break;
				}
			}
		}
		return flag;
	}

	public void applyFilter(int filterByAtNum, String filterValue) {
		clickByCssSelector(String.format(FILTER_OPTION_LOC, filterByAtNum));
		sleep(miliseconds);
		clickByCssSelector(String.format(FILTER_OPTIONVALUE_LOC, filterValue));
		sleep(miliseconds);
	}

	public enum FilterValue {

		SHOW_IN_STOCK_ONLY("instock"), FUXIA("fuxia"), KID("KID"), PLAIN(
				"PLAIN"), MM5("5%20mm%20%20%2f%200%2e2%20in"), ZEROTO250(
				"shoes&pmin=0&pmax=250"), AW14("AW14"), TWOFIFTY_TO_500(
				"&pmax=500"), BLACK("black"), THIRTYNINE_POINT5("39.5");

		private String filterValue;

		private FilterValue(String filterValue) {
			this.filterValue = filterValue;
		}

		public String getFilterValue() {
			return filterValue;
		}
	}

	public void getProductQuickView(int ProductSequence) {
		clickByCssSelector(String.format(PRODUCT_QUICKVIEW_BTN_LOC,
				ProductSequence + 3));
	}

	public void verifyNoSearchResultPage() {
		commonMethods
				.verifyNotPresent(PRODUCTS_BOX_LOC,
						"No Search Result page is not displaying Products Box is present");
	}

	public void verifySearchResultPage() {
		commonMethods.verifyPresent(PRODUCTS_BOX_LOC, "Products Box");
	}

	public void verifyImageAndText() {
		commonMethods.verifyVisible(MAIN_BANNER_IMAGE_LOC, "Image...");
		commonMethods.verifyVisible(MAIN_BANNER_TEXT_LOC, "Sorry...");
	}

	public void verifyMainBannerOnNoSRP() {
		commonMethods.verifyVisible(MAIN_BANNER_TITLE_LOC,
				"Please Try Again....");
		commonMethods.verifyVisible(MAIN_BANNER_TEXT_LOC, "Sorry...");
		commonMethods
				.verifyVisible(MAIN_BANNER_DESC_LOC,
						"Our Menu is full of lot of good things, but not that. Please try again.");
		commonMethods.verifyVisible(MAIN_BANNER_IMAGE_LOC, "Image...");
	}

	public void verifyBreadCrumb() {
		commonMethods.verifyVisible(BREADCRUMB_LOC, "Home > Search Results");
	}

	public void verifySecondGridColumn() {
		commonMethods.verifyVisible(SECOND_GRID_TITLE_LOC,
				"why don’t you have a look at...");
		commonMethods.verifyVisible(SECOND_GRID_COL_LOC, "Three Images...");
	}

	public void verifySecondGridColumnNContent() {
		commonMethods.verifyVisible(SECOND_GRID_FIRST_COL_IMG_LOC, "Image...");
		commonMethods
				.verifyVisible(SECOND_GRID_FIRST_COL_TITLE_LOC, "Our menu");
		commonMethods.verifyVisible(SECOND_GRID_FIRST_COL_DESC_LOC,
				"Lorem ipsum dolor sit amet...");
		commonMethods.verifyVisible(SECOND_GRID_FIRST_COL_BTN_LOC,
				"View Our menu");
		commonMethods.verifyVisible(SECOND_GRID_SEC_COL_IMG_LOC, "Image...");
		commonMethods.verifyVisible(SECOND_GRID_SEC_COL_TITLE_LOC,
				"find a pret");
		commonMethods.verifyVisible(SECOND_GRID_SEC_COL_DESC_LOC,
				"Lorem ipsum dolor sit amet");
		commonMethods.verifyVisible(SECOND_GRID_SEC_COL_BTN_LOC, "find a pret");
		commonMethods.verifyVisible(SECOND_GRID_THIRD_COL_IMG_LOC, "Image...");
		commonMethods.verifyVisible(SECOND_GRID_THIRD_COL_TITLE_LOC,
				"our story");
		commonMethods.verifyVisible(SECOND_GRID_THIRD_COL_DESC_LOC,
				"Lorem ipsum dolor sit amet");
		commonMethods.verifyVisible(SECOND_GRID_THIRD_COL_BTN_LOC,
				"read our story");
	}

	public void clickOnViewOurMenu() {
		commonMethods.waitForElementToBePresent(SECOND_GRID_FIRST_COL_BTN_LOC,
				timeout);
		click(SECOND_GRID_FIRST_COL_BTN_LOC);
		commonMethods.waitForAjaxtoComplete();
	}

	public void verifyProductImage() {
		commonMethods.verifyVisible(ON_OUR_MENU_IMG_LOC, "Image....");
	}

	public void verifyProductName() {
		commonMethods.verifyVisible(PRODUCT_TILTE_LOC, "Sushi, Salad & Soups");
	}

	public void clickOnProductName() {
		click(PRODUCT_TILTE_LOC);
		commonMethods.waitForAjaxtoComplete();
	}

	public void verifyProductImg() {
		commonMethods.verifyVisible(PRODUCT_IMAGE_LOC, "");
	}

	public void verifySerchFunctionality() {
		commonMethods.searchForPhrase(productName);
		Assert.assertTrue(isMessageDisplayed(), "Sorry ! Please try again.... ");
	}

	public boolean isMessageDisplayed() {
		sleep(3000);
		return isElementDisplayed(MAIN_BANNER_TEXT_LOC);
	}

	public void verifyMaximumChar() {
		commonMethods.searchForPhrase(maxCharProduct);
		Assert.assertTrue(isMessageDisplayed(),
				"THE SEARCH IS OVER Hopefully....... ");
	}

	public void verifyUpperAndLowerCase() {
		commonMethods.searchForPhrase(UpperLowerPro);
		Assert.assertTrue(isMessageDisplayed(),
				"THE SEARCH IS OVER Hopefully....... ");
	}

	public void verifySearchTextBoxNBtn() {
		commonMethods.verifyVisible(HEADER_SEARCHBOX_LOC, "Search TextBox");
		commonMethods.verifyVisible(SEARCH_ICON_LOC, "Search Button");
	}

	public void verifyTryAgainGrid() {
		commonMethods.waitForElementToLoad(TRY_AGAIN_LOC, timeout);
		commonMethods.verifyVisible(TRY_AGAIN_LOC, "TRY AGAIN?");
		commonMethods.verifyVisible(TRY_AGAIN_DES_LOC, "");
		commonMethods.verifyVisible(TRY_AGAIN_SEARCH_TEXT_BOX_LOC, "");
	}

	public void verifyTryAgainGridbar() {
		commonMethods.verifyVisible(TRY_AGAIN_GRID_BAR_LOC, "");
	}

	public void viewProduct(int prodSeq) {
		clickByCssSelector(String.format(VIEWPRODUCT_LINK_LOC, prodSeq + 1));
	}

}
