package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginInPage{
public  static WebDriver driver;
public LoginInPage(WebDriver driver) {
	this.driver=driver;
}
	public  void loginPage(String email,String pwd) {
		
		WebElement p=driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
		Actions s=new Actions(driver);
		s.moveToElement(p).build().perform();
		driver.findElement(By.xpath("//*[normalize-space()='Sign in']")).click();
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.id("ap_password")).sendKeys(pwd);
		driver.findElement(By.id("signInSubmit")).click();
	}
	
}
