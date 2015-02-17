/*
3 * Copyright 2013 eCommera. All Rights Reserved.
 */
package com.ecm.webautomation.pret.dataBean;

/**
 * 
 * This class contains a list of parameters names to be used in order to replace
 * the need of hard-coding values into the scripts.
 * 
 * @author cvetan.saykov
 * 
 */

public final class ParametersList {

	private ParametersList() {
	}

	public static final String custPwd = "custPwd";

	// # ||||| User Personal Info Data |||||
	public static final String customerTitle = "customerTitle";
	public static final String customerFirstName = "customerFirstName";
	public static final String customerLastName = "customerLastName";
	public static final String customerDOB_Day = "customerDOB_Day";
	public static final String customerDOB_Month = "customerDOB_Month";
	public static final String customerDOB_Year = "customerDOB_Year";

	// # ||||| Search Data |||||
	public static final String invalidProduct = "invalidProduct";
	public static final String INVALIDPRODUCTID = "invalidProductId";
	public static final String productSimple = "productSimple";
	public static final String variationProductId1 = "variationProductId1";
	public static final String variationProductId2 = "variationProductId2";
	public static final String AUTODEFAULTPRODPREORDER = "AutoDefaultProdPreOrder";
	public static final String AUTODEFAULTPRODBACKORDER = "AutoDefaultProdBackOrder";
	public static final String AUTODEFAULTPRODOUTOFSTOCK = "AutoDefaultProdOutOfStock";
	public static final String VALID_TEXT_SEARCH = "ValidTextSearch";
	public static final String SEARCHBALLY = "searchBally";

	// # ||||| Payment Methods Data |||||
	public static final String ccHolderNameAUTHORISED = "ccHolderNameAUTHORISED";
	public static final String ccHolderNameCAPTURED = "ccHolderNameCAPTURED";
	public static final String ccHolderNameERROR = "ccHolderNameERROR";
	public static final String ccHolderNameREFUSED = "ccHolderNameREFUSED";

	public static final String ccNumberVisa = "ccNumberVisa";
	public static final String ccNumberAmex = "ccNumberAmex";
	public static final String ccNumberMasterCard = "ccNumberMasterCard";
	public static final String ccNumberMaestro = "ccNumberMaestro";

	public static final String ccMonth = "ccMonth";
	public static final String ccYear = "ccYear";

	public static final String ccAmexCVV = "ccAmexCVV";
	public static final String ccNonAmexCVV = "ccNonAmexCVV";

	public static final String ppUserName = "ppUserName";
	public static final String ppPassword = "ppPassword";

	// # ||||| Delivery Option
	public static final String storeNameGB = "storeNameGB";

	// # ||||| Automation Catalogue Data |||||
	public static final String COUPON_CODE = "couponCode";

	// # ||||| Default Time Outs |||||
	public static final String TIMEOUT_MILISECOND = "timeOutMSec";
	public static final String TIMEOUT_SECOND = "timeOutSec";
	// # ||||| Get Mixed Content Warnings |||||
	public static final String logMixedContentWarnings = "logMixedContentWarnings";

	// # ||||| Mailing |||||
	public static final String PASSWORDRESETMAIL_SUBJECT = "passwordResetMailSubject";

	// BM
	public static final String BMSITEDISPLAYNAME = "bmSiteDisplayName";
	public static final String BMSITEDISPLAYNAMEGB = "bmSiteDisplayNameGB";
	public static final String BMSITEDISPLAYNAMECH = "bmSiteDisplayNameCH";
	public static final String BMSITEDISPLAYNAMEEU = "bmSiteDisplayNameEU";
	public static final String BMSITEDISPLAYNAMEFR = "bmSiteDisplayNameFR";
	public static final String BMSITEDISPLAYNAMEDE = "bmSiteDisplayNameDE";
	public static final String BMSITEDISPLAYNAMEIT = "bmSiteDisplayNameIT";
	public static final String BMSITEDISPLAYNAMEUS = "bmSiteDisplayNameUS";

	public static final boolean getMixedContentWarnings() {
		if (logMixedContentWarnings.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
}
