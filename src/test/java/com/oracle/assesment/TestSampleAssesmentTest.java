package com.oracle.assesment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import java.util.Random; 

public class TestSampleAssesmentTest {
	
	private static Log logger = LogFactory.getLog(TestSampleAssesmentTest.class);
	protected List<File> toCleanup = new ArrayList<File>();
	
	private static final String WEB_DRIVER_TYPE = System.getenv("WEB_DRIVER_TYPE");
	private static final String WEB_DRIVER_PATH = System.getenv("WEB_DRIVER_PATH");
	private static final String USER1           = System.getenv("USER1");
	private static final String PASSWORD1       = System.getenv("PASSWORD1");
	private static final String USER2           = System.getenv("USER2");
	private static final String PASSWORD2       = System.getenv("PASSWORD2");
	private static final String HOME_PAGE       = "https://qa122.aconex.com/Logon";
	private static final String baseUrl         = "https://qa122.aconex.com";
	private static final By     byMail          = By.xpath("//button[@id=\"nav-bar-CORRESPONDENCE\"]");
	private static final By     byEmptyMail     = By.xpath("//div[@id=\"nav-bar-CORRESPONDENCE-CORRESPONDENCE-CREATEMAIL\"]");
	private static final By     userName        = By.xpath("//div//input[@id=\"userName\"]");
	private static final By     passWord        = By.xpath("//div//input[@id=\"password\"]");
	private static final By     bySend          = By.xpath("//button[@id=\"btnSend\"]");
	private static final By     byButtonOk      = By.xpath("//button[@id=\"btnOk\"]");
	private static final By     byButtonMailTo  = By.xpath("//button[@id=\"btnAddTo_page\"]");
	private static final By     byButtonMailCc  = By.xpath("//button[@id=\"btnAddCc_page\"]");
	private static final By     byButtonMailBcc  = By.xpath("//button[@id=\"btnAddBCc_page\"]");
	private static final By     byMainFrame     = By.xpath("//iframe[@id=\"frameMain\"]");
	private static final By     byMeetingType   = By.xpath("//td//select[@id=\"Correspondence_correspondenceTypeID\"]");
	private static final By     byToDirectory   = By.xpath("//tr//td[3]//button");
	private static final By     byGlobalTab     = By.xpath("//li[@id=\"ACONEX_list\"]");
	private static final By     bySubjectField  = By.xpath("//input[@id=\"Correspondence_subject\"]");
	private static final By     byFirstName     = By.xpath("//input[@id=\"FIRST_NAME\"]");
	private static final By     byLastName      = By.xpath("//input[@id=\"LAST_NAME\"]");
	private static final By     byButtonSearch  = By.xpath("//button[@id=\"btnSearch_page\"]");
	private static final By     byCheckBox      = By.xpath("//input[@type=\"checkbox\"]");
	private static final By     byAttribute0    = By.xpath("//div[@id=\"multiselect0\"]");
	private static final By     byAttribute1    = By.xpath("//div[@id=\"multiselect1\"]");
	private static final By     byButtonCommit  = By.xpath("//button[@id=\"attributePanel-commit\"]");
	private static final By     byPrimaryAttribute      = By.xpath("//div[@id=\"attributeBidi_PRIMARY_ATTRIBUTE\"]//div[1]//select");
	private static final By     bySecondaryAttribute    = By.xpath("//div[@id=\"attributeBidi_SECONDARY_ATTRIBUTE\"]//div[1]//select");
	private static final By     byPrimaryAttributeAdd   = By.xpath("//button[@id=\"attributeBidi_PRIMARY_ATTRIBUTE_add\"]");
	private static final By     bySecondaryAttributeAdd = By.xpath("//button[@id=\"attributeBidi_SECONDARY_ATTRIBUTE_add\"]");
	private static final By     byDocumentTab           = By.xpath("//button[@id=\"nav-bar-DOC\"]");
	private static final By     byUploadNewDocTab       = By.xpath("//div[@id=\"nav-bar-DOC-DOC-NEW\"]");
	private static final By     byDocNo                 = By.xpath("//input[@id=\"docno_0\"]");
	private static final By     byDocRevision           = By.xpath("//input[@name=\"revision_0\"]");
	private static final By     byDocTitle              = By.xpath("//input[@name=\"title_0\"]");
	private static final By     byDocType               = By.xpath("//td//select[@id=\"doctype_0\"]");
	private static final By     byDocStatus             = By.xpath("//td//select[@id=\"docstatus_0\"]");
	private static final By     byDocDiscipline         = By.xpath("//td//select[@id=\"discipline_0\"]");
	private static final By     byDocRevDate            = By.xpath("//input[@id=\"revisiondate_0_da\"]");
	private static final By     byDocUpload             = By.xpath("//button[@id=\"btnUploadDocument\"]");
	
	
	private static WebDriver driver;
	
	@BeforeAll
	public static void loadWebDriver() {
		logger.info("type" + WEB_DRIVER_TYPE);
		logger.info("path" + WEB_DRIVER_PATH);
		System.setProperty(WEB_DRIVER_TYPE, WEB_DRIVER_PATH);	
	}
	
	@BeforeEach
	public void createWebDriverInstance( ) {
		if( WEB_DRIVER_TYPE.contains("chrome"))
		    driver = new ChromeDriver();
		else if (WEB_DRIVER_TYPE.contains("firefox"))
			driver = new FirefoxDriver();
	}
	
	@AfterEach
	public void CloseBrowserInstance( ) {
		driver.quit();
	}

	@Ignore
	public void testSendBlankMailDefault_Positive() throws TimeoutException, InterruptedException {
		
		logger.info("Prepare all the reqquired input");
		String[] firstName              = {"Kenton"};
		String[] lastName               = {"Tillman"};
		String[] secondaryAttributeList = { "WP1.01 - Piling" };
		
		logger.info("Login in to the page with " + USER1 + "credentials");
		Login(null, USER1, PASSWORD1, "Mr Patrick O'Leary");
		
		logger.info(" Open Blank Mail Page ");
		open_BlankMailTab();
		
		logger.info(" Compose address details for the mail ");
		composeAdressDetails("Meeting Minutes", firstName, lastName, false, false);
		
		logger.info("Set the mail subject");
		sendKeys(driver, bySubjectField, "Demo Mail", true);
			
		//can call for attribute 0 which is not mandatory field
		logger.info("Pick the attributes for the mail");
		composeAttributeDetails(byAttribute1, null, secondaryAttributeList);
		
		logger.info("Send the mail");
		clickOrSubmit(driver, bySend, false);
		
	}
	
	@Test
	public void testUploadAndVerifyDocument() throws TimeoutException, InterruptedException {
		
		Login(null, USER2, PASSWORD2, "Professor Kenton Tillman");
		
		clickOrSubmit(driver, byDocumentTab, false);
		click(driver.findElement(byUploadNewDocTab));
		
		// create instance of Random class 
        Random rand = new Random(); 
		
		switchFrame(driver, byMainFrame, null, -1);
	    sendKeys(driver, byDocNo, "Doc"+rand.nextInt(1000), true);
	    sendKeys(driver, byDocRevision, "qqq", true);
	    sendKeys(driver, byDocTitle, "Sample", true);
		
	    selectByVisibleText(driver, byDocType, "Audit Reports");	
	    selectByVisibleText(driver, byDocStatus, "Draft");
	    selectByVisibleText(driver, byDocDiscipline, "Civil");
			
	    sendKeys(driver, byDocRevDate, "12/11/2020", true);
	
	/*  Haven't got a chance to Verify this part of code as https://qa122.aconex.com/ server was down from second half of Friday(20th)
		driver.findElement(By.xpath("//span[@id=\"clickToUpload\"]")).click();
		driver.findElement(By.xpath("//div//span[@id=\"clickToUpload\"]")).sendKeys("/home/administrator/Subject.html");
		
		//or
		  
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Setting value for "style" attribute to make textbox visible
		js.executeScript("arguments[0].style.display='block';", element);
		driver.findElement(By.xpath("//div//span[@id=\"clickToUpload\"]")).sendKeys("\"/home/administrator/Subject.html\"");*/
 
	    clickOrSubmit(driver, byDocUpload, false);
		
		/* Rest of the implementation needs https://qa122.aconex.com/ to be up and running*/
	}
	
	public void composeAdressDetails(String meetingType, String[] firstName, String[] lastName, boolean addToCc, boolean addToBcc) throws TimeoutException {
		
		selectByVisibleText(driver, byMeetingType, meetingType);
		
		clickOrSubmit(driver, byToDirectory, false);
		clickOrSubmit(driver, byGlobalTab, false);
		
		if( firstName.length >0 || lastName.length>0 ) {
			
		   for (int i=0; i<firstName.length; i++) {
			   
			    sendKeys(driver, byFirstName, firstName[i], true);
			    sendKeys(driver, byLastName, lastName[i], true);
			    clickOrSubmit(driver, byButtonSearch, false);
		        setCheckbox(driver, byCheckBox, true);
		        
		        if(addToCc)
		           clickOrSubmit(driver, byButtonMailCc, false);
		        else if (addToBcc)
		           clickOrSubmit(driver, byButtonMailBcc, false);
		        else
		           clickOrSubmit(driver, byButtonMailTo, false);
		   }
		}
		clickOrSubmit(driver, byButtonOk, false);	
	}
	
	public void composeAttributeDetails(By byAttribute, String[] primaryAttributeListValue, String[] secondaryAttributeListValue) throws TimeoutException  {
        
		clickOrSubmit(driver, byAttribute, false);
		
		if (primaryAttributeListValue != null) {
		    select(driver, byPrimaryAttribute, primaryAttributeListValue);
		    clickOrSubmit(driver, byPrimaryAttributeAdd, false);
	    }
		
		if (secondaryAttributeListValue != null) {
			select(driver, bySecondaryAttribute, secondaryAttributeListValue);
		    clickOrSubmit(driver, bySecondaryAttributeAdd, false);
	    }
		
		clickOrSubmit(driver, byButtonCommit, false);
	}
	
	public void open_BlankMailTab() throws TimeoutException  {
		
		clickOrSubmit(driver, byMail, false);
		clickOrSubmit(driver, byEmptyMail, false);
		
		switchFrame(driver, byMainFrame, null, -1);
		assertEquals(driver.findElement(By.xpath("//h1[@id=\"editMailHeading\"]")).getText(), "New Mail");
	}
	
	public void Login(String url, String name, String password, String accountNameToValidate) throws TimeoutException, InterruptedException {
		
		if (url == null)
		    get(baseUrl);
		else 
			get(url);
		
		assertTrue( isCurrent("/Logon"), "Current page url mismatch");
		assertTrue( driver.getTitle()!=null, "Initial page is not loaded" );
		
		sendKeys(driver, userName, name, true);
		sendKeys(driver, passWord, password, true);
		
		clickOrSubmit(driver, By.id("login"), true);
		
		assertEquals(accountNameToValidate, findElement(driver, By.xpath("//span[@class=\"nav-userDetails\"]")).getAttribute("title"), "User details doesn't match");
		assertTrue(isCurrent("/Logon"), "Home page not loaded, link doesn't match");
		
	}
	
    public static void waitUtilCondition(WebDriver driver, ExpectedCondition<?> condition, Integer timeoutSeconds) throws TimeoutException {
		 if (timeoutSeconds == null) {
		     timeoutSeconds = 3;
		 }
		 WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);   
		 wait.until(condition);
     }
	
	 public WebDriver get(String url) {
		 driver.get(url);
		 logger.info("Get page = " + url);
		 return driver;
     }
	 
	  /**
	   * check if the current page is the same as one specified in the path
	   */
    public boolean isCurrent(String path) {
	    String url = baseUrl + path;
	    String actual = driver.getCurrentUrl();
	    logger.info("url : " + url);
	    logger.info("actual : " + actual);
	    return actual.equals(url);
	}
	  
	public static WebElement findElement(WebDriver driver, By by) throws TimeoutException {

	    WebElement found = null;
		try {
		    waitUtilCondition(driver, ExpectedConditions.visibilityOfElementLocated(by), 20);
		    found = driver.findElement(by);
		} catch (NoSuchElementException e) {
		      // ignore
		}
		return found;
    }
	  
	public static void sendKeys(WebDriver driver, By by, String text, boolean returnOnNull) throws TimeoutException {

         if (returnOnNull && text == null) {
		     return;
		 }

		 logger.info("sendKeys " + by + " = " + text);
		 WebElement element = findElement(driver, by);    

		 element.clear(); 
		 if (text != null) {
		    element.sendKeys(text);
		 }
		    
		 assertTrue( element.getText()!= null );
		 assertEquals(text, element.getAttribute("value"), "Keys are not passed to text field");
	}
	  
	public static void clickOrSubmit(WebDriver driver, By by, boolean isSubmit) throws TimeoutException {

		waitUtilCondition(driver, ExpectedConditions.elementToBeClickable(by), 30);
		WebElement button = driver.findElement(by);
		if (isSubmit)
		   button.submit();
		else
		   button.click();
    }

	public void switchFrame(WebDriver driver, By by, String name, int id) throws TimeoutException {
		 
		 if (name != null)
			 driver.switchTo().frame(name);
		 else if (id >= 0)
			 driver.switchTo().frame(id);
		 else
		     driver.switchTo().frame(findElement(driver, by));		 
	}
	 
	public static void select(WebDriver driver, By by, String[] selectValue) throws TimeoutException {

	     WebElement select = findElement(driver, by);
		 select(select, selectValue);
	}

    public static void select(WebElement selectWE, String[] selectValue) {
   
	     Select select = new Select(selectWE);
		 for( int i=0; i<selectValue.length; i++ ) {
		      select.selectByValue(selectValue[i]);
		 }
		    
		 List<WebElement> actualSelectedItems = select.getAllSelectedOptions();
		 assertTrue(actualSelectedItems.size() == selectValue.length, "Selected values length doesn't match");
		    
		 for( int i=0; i<selectValue.length; i++ ) {
		      assertEquals(selectValue[i], actualSelectedItems.get(i).getText(),"selected values doesn't match" );  
		 }
    }
		  
		  
	public static void selectByVisibleText(WebDriver driver, By by, String optionText) throws TimeoutException {

        if (optionText == null) {
		   return;
		}

		logger.info("SelectByVisibleText " + by + " = " + optionText);
		WebElement selectWE = findElement(driver, by);
		Select select = new Select(selectWE);
		select.selectByVisibleText(optionText);
		    
		assertEquals(optionText, select.getFirstSelectedOption().getText(),"selected values doesn't match" );
    }
		   
    public static void setCheckbox(WebDriver driver, By by, Boolean check) throws TimeoutException {

        logger.info("SetCheckbox " + by + " = " + check);
        if (check == null) {
			return;
	    }   

	    WebElement checkbox = findElement(driver, by);
	    if (checkbox != null && checkbox.isSelected() != check) {
			checkbox.click();
	    }
			    
	    assertTrue(checkbox.isSelected() == check);
    }

    public static void click(WebElement element) {
        element.click();
	}

}
