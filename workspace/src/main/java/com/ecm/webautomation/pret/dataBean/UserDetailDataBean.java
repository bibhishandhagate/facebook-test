package com.ecm.webautomation.pret.dataBean;

import java.io.File;
import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ecm.webautomation.WebDriverModule;

public class UserDetailDataBean extends WebDriverModule {
	public UserDetailDataBean(WebDriver webDriver) {
		super(webDriver);
	}

	private Document document;
	private String title;
	private String firstName;
	private String lastName;
	private String email;
	private String confirmEmail;
	private String password;
	private String customerId;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String phoneNumer;
	private String mobileNumber;

	public String getDOBDay() {
		return dobDay;
	}

	protected void setDOBDay(String dOBDay) {
		dobDay = dOBDay;
	}

	public String getDOBMonth() {
		return dobMonth;
	}

	protected void setDOBMonth(String dOBMonth) {
		dobMonth = dOBMonth;
	}

	public String getDOBYear() {
		return dobYear;
	}

	public void setDOBYear(String dOBYear) {
		dobYear = dOBYear;
	}

	public String getPhoneNumer() {
		return phoneNumer;
	}

	public void setPhoneNumer(String phoneNumer) {
		this.phoneNumer = phoneNumer;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String confirmPassword;

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected Field[] getFields() {
		return this.getClass().getDeclaredFields();
	}

	public void getUserData(String userData) {
		loadXMLFile("src/test/resources/conf/UserData.xml");
		try {
			fillDataFieldsFromXML("EmptyAllData");
			fillDataFieldsFromXML(userData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
