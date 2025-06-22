package Pages;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class productPriceTrackerPage {
	public  WebDriver driver;
	
	ExtentReports extent;
	ExtentTest  test;
	public productPriceTrackerPage(WebDriver driver) {
		this.driver=driver;
		
		ExtentSparkReporter report = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		  report.config().setDocumentTitle("Amazon Price Tracker Report");
	        report.config().setReportName("Price Drop Check");
	        report.config().setTheme(Theme.STANDARD);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(report);
	        extent.setSystemInfo("Tester", "Sadhika");
	        this.test = extent.createTest("Amazon Product Search Test");
	        
	}

	public   int ProductSearch(String ItemSearch) throws EmailException {
		 int threshold = 10000;
	
		WebElement searchItem=driver.findElement(By.id("twotabsearchtextbox"));
		searchItem.sendKeys(ItemSearch);
		searchItem.sendKeys(Keys.ENTER);
		test.pass("Product searched successfully: " + ItemSearch);
		JavascriptExecutor js=(JavascriptExecutor)driver;
        WebElement priceProduct=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[6]/div[1]/div[1]/span[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]/span[2]/span[2]"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", priceProduct);
		 test.pass("Scrolled to product price");
		String priceText=priceProduct.getText().replace(",", "").trim();
		int currentPrice=Integer.parseInt(priceText);
		test.info("Current Price: ₹" + currentPrice);
		 if (currentPrice < threshold) {
	            sendEmail("Price Drop Alert", "Product price dropped to ₹" + currentPrice + "! Hurry up!");
	            test.pass("Email sent for price drop to ₹" + currentPrice);
	        } else {
	            test.info("Price is still above ₹" + threshold);
	        }
		 extent.flush();

	        return currentPrice;
		

			
	}

	public static void sendEmail(String Subject, String Message) throws EmailException {
		Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new org.apache.commons.mail.DefaultAuthenticator("sksadhika88@gmail.com", "cuqh lhel tmbd rwcd"));
        email.setSSLOnConnect(true);
        email.setFrom("sksadhika88@gmail.com");
        email.setSubject(Subject);
        email.setMsg(Message);
        email.addTo("sksadhika88@gmail.com");
        email.send();
        System.out.println("📩 Email Sent Successfully!");
		
	}
}

