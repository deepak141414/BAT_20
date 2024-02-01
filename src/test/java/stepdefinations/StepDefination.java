package stepdefinations;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AdminPage;
import pageObject.Vendor;
import utilities.ReadConfig;

public class StepDefination extends Base {
	
	@Before
	public void setup() throws Exception {
		System.out.println("Set up method execution");
		readconfig=new ReadConfig();
		
		String browser=readconfig.getbrowser();
		
		if(browser.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}else if(browser.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}else if(browser.equalsIgnoreCase("IE")) {
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
			Thread.sleep(2000);
		}
	}

	@Given("User Launch Chrome Browser")
	public void user_launch_chrome_browser() {
	    
		//driver=new FirefoxDriver();
		
		ad=new AdminPage(driver);
		
	}

	@When("User open url {string}")
	public void user_open_url(String url) {
		driver.get(url);
	    
	}

	@When("User enter Email as {string} and password as {string}")
	public void user_enter_email_as_and_password_as(String email, String password) {
		ad.setUserName(email);
		ad.setPassword(password);
	    
	}

	@When("User Click on Login button")
	public void user_click_on_Login_button() {
		ad.clickonLogin();
	   
	}

	@Then("User verify Page title should be {string}")
	public void user_verify_page_title_should_be(String title) {
		
		Assert.assertEquals(driver.getTitle(), title);
	    
	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
	    
	}
	
	@When("User click on vendor item")
	public void user_click_on_vendor_item() throws Exception {
		vendor=new Vendor(driver);
		vendor.clickOnVendors();
		Thread.sleep(2000);
		
		
	}

	@Then("User can view vendor page")
	public void user_can_view_vendor_page() throws Exception {
		Assert.assertEquals("vendors / nopcommerce administration",vendor.getPageTitle());
		Thread.sleep(2000);
		
	
	}

	@When("User enter Vendor name as {string} and password as {string}")
	public void user_enter_vendor_name_as_and_password_as(String venName, String venemail) throws Exception {
		vendor.searchName(venName);
		vendor.searchEmail(venemail);
		Thread.sleep(2000);
	    
	}

	@When("User click on Search button")
	public void user_click_on_search_button() throws Exception {
		vendor.clickOnSearchButton();
		Thread.sleep(2000);
	   
	}

	@After
	public void tearDown(Scenario sc) throws Exception {
		System.out.println("Tear down method execute after every Scenario");	
		
		if(sc.isFailed()==true) {
		
		
		String filePath="C:\\Users\\Deepak\\eclipse-workspace\\BddCucumberMavenProject\\captureScreenshot\\failedScreenshot.png";
		
		TakesScreenshot scrShot=(TakesScreenshot)driver;
		
		File scrFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		File destFile=new File(filePath);
		
		FileUtils.copyFile(scrFile, destFile);
		
		Thread.sleep(2000);
		
		}
		driver.quit();
	
	}
}
