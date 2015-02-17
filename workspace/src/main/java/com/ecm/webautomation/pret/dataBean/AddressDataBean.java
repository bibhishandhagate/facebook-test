package com.ecm.webautomation.pret.dataBean;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ecm.webautomation.WebDriverModule;
import com.ecm.webautomation.config.ConfigurationReader;

public class AddressDataBean extends WebDriverModule {

	public AddressDataBean(WebDriver webDriver) {
		super(webDriver);
	}

	private Document document;

	private String title;
	private String firstName;
	private String lastName;
	private String flatNo;
	private String addLine1;
	private String addLine2;
	private String country;
	private String county;
	private String city;
	private String postCode;
	private String phoneNumber;
	private String email;
	private String addressName;
	private String orderNo;
	private String countryCode;
	private String stateCode;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddLine1() {
		return addLine1;
	}

	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}

	public String getCity() {
		return city;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getAddLine2() {
		return addLine2;
	}

	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void loadXMLFile(String filePath) {
		try {
			document = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(new File(filePath));
		} catch (Exception e) {
		}
	}

	public void fillDataFieldsFromXML(String parentTag) {
		NodeList nodeListParent = document.getElementsByTagName(parentTag);
		NodeList nodeListChild = nodeListParent.item(0).getChildNodes();
		for (int i = 1; i < nodeListChild.getLength(); i = i + 2) {
			try {
				setFieldDataWithXMLData(nodeListChild.item(i).getNodeName(),
						nodeListChild.item(i).getTextContent());
			} catch (DOMException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setFieldDataWithXMLData(String childTagName,
			String childTagValue) {
		Field[] declaredFields = getFields();
		for (Field field : declaredFields) {
			if (field.getName().equalsIgnoreCase(childTagName)) {
				try {
					field.set(this, childTagValue);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}

	public void getAddressData(String addressProfile) {
		String bmSiteDisplayName = ConfigurationReader.get("bmSiteDisplayName");

		switch (bmSiteDisplayName) {
		case "Bally GB":
			loadXMLFile("src/test/resources/conf/AddressDataUK.xml");
			break;
		case "Bally CH":
			loadXMLFile("src/test/resources/conf/AddressDataCH.xml");
			break;
		case "Bally DE":
			loadXMLFile("src/test/resources/conf/AddressDataDE.xml");
			break;
		case "Bally EU":
			loadXMLFile("src/test/resources/conf/AddressDataEU.xml");
			break;
		case "Bally FR":
			loadXMLFile("src/test/resources/conf/AddressDataFR.xml");
			break;
		case "Bally IT":
			loadXMLFile("src/test/resources/conf/AddressDataIT.xml");
			break;
		case "Bally US":
			loadXMLFile("src/test/resources/conf/AddressDataUS.xml");
			break;
		default:
			loadXMLFile("src/test/resources/conf/AddressDataUK.xml");
			break;
		}

		try {
			fillDataFieldsFromXML("EmptyAllData");
			fillDataFieldsFromXML(addressProfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
