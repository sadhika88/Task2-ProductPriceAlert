package Tests;


import java.io.IOException;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import BaseClass.BrowserSetup;
import Pages.LoginInPage;
import Pages.productPriceTrackerPage;



public class PriceAlert {
	
	@BeforeClass
	public void launchBrowser() {
		BrowserSetup.initializeBrowser();
	}
	
	@Test(priority=1)
	public void loginToAmazon() {
		LoginInPage l=new LoginInPage(BrowserSetup.driver);
		l.loginPage("9014171669", "MuzeebSadhika@123");
	}
		
	
	@Test(priority = 2)
	public void productSearchtest() throws EmailException, InterruptedException, IOException  {
		productPriceTrackerPage c=new productPriceTrackerPage(BrowserSetup.driver);
		int currentprice=c.ProductSearch("MixerGrinder");
		
		Thread.sleep(2000);
		
		 }
	@AfterClass
	public void closeBrowser() {
		BrowserSetup.tearDown();
	}
	
	
	}

