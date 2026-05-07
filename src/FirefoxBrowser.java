import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser {
	
	/**
	 * Launches the browser and opens the given URL using get method.
	 * This method initializes WebDriver and then get page source, title and the url then display them in console.
	 */
	void LaunchBrowser() {
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.instagram.com/");
		
		
		String pageSource = driver.getPageSource();
		String title = driver.getTitle();
		String currentUrl = driver.getCurrentUrl();
		
		System.out.println("Firefox Browser:");
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
		WebDriver driver = new FirefoxDriver();
		
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

}
