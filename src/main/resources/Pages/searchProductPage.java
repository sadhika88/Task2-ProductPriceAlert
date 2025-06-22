package Pages;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class searchProductPage {

	public static void ProductSearch(WebDriver driver,String ItemSearch) throws EmailException {
		int thresholdPrice=2000;
		WebElement searchItem=driver.findElement(By.id("twotabsearchtextbox"));
		searchItem.sendKeys("Mixer grinder");
		searchItem.sendKeys(Keys.ENTER);
		JavascriptExecutor js=(JavascriptExecutor)driver;
        WebElement priceProduct=driver.findElement(By.xpath("//span[normalize-space()='1,124']"));
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", priceProduct);
		String priceText=priceProduct.getText().replace(",", "").trim();
		int currentPrice=Integer.parseInt(priceText);
		if(currentPrice<thresholdPrice) {
			sendEmail(currentPrice);
		}
			else {
				System.out.println("Price is very high " +currentPrice);
			}
			
	}

	private static void sendEmail(int currentPrice) throws EmailException {
		Email  email=new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		 email.setAuthenticator(new DefaultAuthenticator("sksadhika88@gmail.com", "MunniMastan@123"));
		 email.setStartTLSEnabled(true);

	     email.setFrom("sksadhika88@gmail.com");
	     email.setSubject("🔔 Price Drop Alert");
	     email.setMsg("Hi,\n\nThe product price has dropped to ₹" + currentPrice + ", below your set limit!\nCheck it on Amazon now.");
	     email.addTo("shaiksadhika648@gmail.com");

	     email.send();
	     System.out.println("✅ Email sent successfully with price ₹" + currentPrice);

		
	}
}

