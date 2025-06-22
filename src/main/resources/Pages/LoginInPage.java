package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginInPage{

	public static  void login(WebDriver driver,String email,String pwd) {
		driver.get("https://www.amazon.in/");
		WebElement p=driver.findElement(By.xpath(" //span[@class='nav-line-2 ']"));
		Actions s=new Actions(driver);
		s.moveToElement(p).build().perform();
		driver.findElement(By.xpath("//*[normalize-space()='Sign in']")).click();
		driver.findElement(By.name("email")).sendKeys("9014171669");
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		driver.findElement(By.id("ap_password")).sendKeys("MuzeebSadhika@123");
		driver.findElement(By.id("signInSubmit")).click();
	}
	
}
