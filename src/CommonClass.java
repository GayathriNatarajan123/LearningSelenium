import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonClass {
	
	void ClosePopupFacebook(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement declineBtn = wait.until(
			    ExpectedConditions.elementToBeClickable(
			        By.xpath("//span[text()='Decline optional cookies']")
			    )
			);
			declineBtn.click();


		System.out.println("Declined cookies");
		
	}
	
	public void scrollTable(WebDriver driver, WebElement table, ScrollAction scrollAction) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;	    
	    js.executeScript("arguments[0]."+ scrollAction + ";", table);
	 }
	



}

//"//span[text()='Decline optional cookies']/parent::*"