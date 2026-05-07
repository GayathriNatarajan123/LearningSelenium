import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowser {
	
	/**
	 * Launches the browser and opens the given URL using get method.
	 * This method initializes WebDriver and then get page source, title and the url then display them in console.
	 */
	void LaunchBrowser() {

		// Initiate web browser
		WebDriver driver = new EdgeDriver();

		driver.get("https://www.instagram.com/");

		String pageSource = driver.getPageSource();
		String title = driver.getTitle();
		String currentUrl = driver.getCurrentUrl();
		
		System.out.println("Edge Browser:");
		System.out.println(pageSource);
		System.out.println("Title: " + title);
		System.out.println("Current Url: " + currentUrl);
		
		driver.close();
	}
	
	/**
	 * Launches the browser and opens the given URL using navigate method.
	 * This method initializes WebDriver and then navigate back and forward.
	 * @throws InterruptedException
	 */
	void NavigateMethod() throws InterruptedException {
		WebDriver driver = new EdgeDriver();
		
		driver.navigate().to("https://www.instagram.com/");
		Thread.sleep(3000);
		driver.navigate().to("https://en-gb.facebook.com/");
		Thread.sleep(3000);
		
		driver.navigate().back();
		Thread.sleep(3000);
		driver.navigate().forward();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		
		driver.close();	
		
	}

	void xpathMethods() {
		WebDriver driver = new EdgeDriver(); 
		CommonClass commonClass = new CommonClass();
		try {
			driver.get("https://en-gb.facebook.com/");
			commonClass.ClosePopupFacebook(driver);
			
			WebElement userName =driver.findElement(By.xpath("//input[@name='email']"));
			userName.sendKeys("Test");
			
			
			WebElement password = driver.findElement(By.xpath("//input[contains(@type,'password')]"));
			password.sendKeys("test");
			
			driver.get("https://en-gb.facebook.com/reg/?entry_point=login&next=");
			commonClass.ClosePopupFacebook(driver);
			
			WebElement email = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
			email.sendKeys("abc@gmail.com");
			
			WebElement surName = driver.findElement(By.xpath("(//input[contains(@type,'text')])[2]"));
			surName.sendKeys("selenium");
			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());			
		}
		finally {
			driver.close();
		}
	}
}
