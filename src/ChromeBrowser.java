
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromeBrowser {

	/**
	 * Launches the browser and opens the given URL using get method. This method
	 * initializes WebDriver and then get page source, title and the url then
	 * display them in console.
	 */
	void LaunchBroswer() {
		// Initialise the web driver
		WebDriver driver = new ChromeDriver();

		// get browser
		driver.get("https://www.instagram.com/");

		// get page source
		String pageSource = driver.getPageSource();
		System.out.println(pageSource);

		// get page title
		String title = driver.getTitle();
		System.out.println("\nTitle: " + title);

		// get current url
		String currentURL = driver.getCurrentUrl();
		System.out.println("\nCurrent URL: " + currentURL);

		driver.close();

	}

	/**
	 * Launches the browser and opens the given URL using navigate method. This
	 * method initializes WebDriver and then navigate back and forward.
	 * 
	 * @throws InterruptedException
	 */
	void NavigateMethod() {
		WebDriver driver = new ChromeDriver();
		try {

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
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			driver.quit();
		}
	}

	void ClosePopup(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement declineBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._a9--._ap36._a9_1")));
		declineBtn.click();
		System.out.println("Declined cookies");
	}

	/**
	 * Launches the browser and opens the given URL using get method. This method
	 * initializes WebDriver and then closes the pop-up cookie,by clicking decline
	 * button. Uses primary locator to get the field details. Use sendKeys method to
	 * pass the values to the fields. Primary locators like-id,name, classname,
	 * tagname, linktext, partiallinktext is used. Close method is used to close the
	 * given URL. Try-catch is used to catch the exceptions.
	 */
	void LocatorMethods() {
		WebDriver driver = new ChromeDriver();
		try {

			driver.get("https://www.instagram.com/");

			ClosePopup(driver);

			WebElement userName = driver.findElement(By.id("_R_32d9lplkldcpbn6b5ipamH1_"));
			userName.sendKeys("sivag5000@gmail.com");

			WebElement password = driver.findElement(By.name("pass"));
			password.sendKeys("testing*987654321");

			WebElement tagName = driver.findElement(By.tagName("div"));
			String name = tagName.getText();
			System.out.println(name);

			// WebElement className = driver.findElement(By.className("x1lliihq x193iq5w
			// x6ikm8r x10wlt62 xlyipyv xuxw1ft")); //--- Exception: Compound class names
			// not permitted

			// className.click();

			WebElement linkText = driver.findElement(By.linkText("Fill in form"));
			// -- sometimes works but sometimes getting Exception: element click
			// intercepted: so adding below line for click function
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// linkText);

			linkText.click();
			driver.navigate().back();
			Thread.sleep(1000);

			WebElement partialLinkText = driver.findElement(By.partialLinkText("Fill in "));
			partialLinkText.click();
			// --- Exception: element click intercepted:below is the fix
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// partialLinkText);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			// driver.close();
		}
	}

	/**
	 * Launches the browser and opens the given URL using get method. This method
	 * initializes WebDriver. Select the elements using cssSelector. Find the
	 * element using id tag and class tag. Find element inside the nested tag using
	 * class tag. Find and automate dropdown button.
	 */
	void CssSelectorMethod() {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.wikipedia.org/");
			// Get the web element using the id tag.
			WebElement search = driver.findElement(By.cssSelector("input#searchInput"));
			search.sendKeys("phone");
			Thread.sleep(1000);

			// Get the web element using class tag.
			WebElement btnSearch = driver
					.findElement(By.cssSelector("button.pure-button.pure-button-primary-progressive"));
			// btnSearch.click();

			// Get the web element using class tag which is inside another tag-selecting the
			// tag's class inside the another tag ie child tag
			WebElement btnlink = driver.findElement(By.cssSelector("div.central-featured-lang.lang2 a.link-box"));
			btnlink.click();

			// Automate link button
			WebElement btnLink1 = driver.findElement(By.cssSelector("a#js-link-box-ru"));
			btnLink1.click();

			// Automate dropdown button
			WebElement btnDropDown = driver.findElement(By.cssSelector("select#searchLanguage"));
			Select selectBtnDropDown = new Select(btnDropDown);
			selectBtnDropDown.selectByValue("ta");
			btnSearch.click();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			driver.close();
		}
	}

	void xpathMethods() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		CommonClass commonClass = new CommonClass();

		
			driver.get("https://en-gb.facebook.com/");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[text()='Decline optional cookies'])[1]")).click();
			//commonClass.ClosePopupFacebook(driver);

			WebElement userName = driver.findElement(By.xpath("//input[@name='email']"));
			userName.sendKeys("Test");

			WebElement password = driver.findElement(By.xpath("//input[contains(@type,'password')]"));
			password.sendKeys("test");
			Thread.sleep(2000);
			
			driver.get("https://en-gb.facebook.com/reg/?entry_point=login&next=");
			Thread.sleep(2000);			
			
			//commonClass.ClosePopupFacebook(driver);

			WebElement email = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
			email.sendKeys("abc@gmail.com");

			WebElement surName = driver.findElement(By.xpath("(//input[contains(@type,'text')])[2]"));
			surName.sendKeys("selenium");

			WebElement name = driver.findElement(By.xpath("//span[text()='Get started on Facebook']"));
			System.out.println(name.getText());

			WebElement name1 = driver.findElement(By.xpath("//span[contains(text(),'started ')]"));
			System.out.println(name1.getText());

		
	}

	/**
	 * Launches the browser and opens the given URL using get method. This method
	 * initializes WebDriver. Locate the web element using locators. Perform the
	 * move actions Hover-over,click,double click, right-click, drag and drop.
	 */
	void actionsMethods() {
		// Initiate an object reference for the WebDriver
		WebDriver driver = new ChromeDriver();

		// Initiate an object reference for Actions class for the webpage
		Actions actions = new Actions(driver);

		try {
			// Launch the webpage
			driver.get("https://demo.guru99.com/test/drag_drop.html");
			Thread.sleep(1000);

			// Identify the locator for the webelement
			WebElement btnLiveProject = driver.findElement(By.linkText("Live Project"));

			// Use actions obj reference to perform automation-hover over
			actions.moveToElement(btnLiveProject).perform();

			// Identify the locator for the webelement
			WebElement ddSelenium = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[1]"));

			// Use actions obj reference to perform automation-click
			actions.click(ddSelenium).perform();

			Thread.sleep(1000);

			// Identify the locator for the webelement
			WebElement btnYahoo = driver.findElement(By.linkText("Yahoo"));
			// Use actions obj reference to perform automation-double click
			actions.doubleClick(btnYahoo).perform();

			driver.navigate().back();
			Thread.sleep(1000);

			// drag and drop
			WebElement drag = driver.findElement(By.xpath("(//a[@class='button button-orange'])[2]"));
			WebElement drop = driver.findElement(By.xpath("//ol[@id='amt7']/li"));

			actions.dragAndDrop(drag, drop).build().perform();
			Thread.sleep(1000);

			WebElement drag1 = driver.findElement(By.xpath("(//a[@class='button button-orange'])[5]"));
			WebElement drop1 = driver.findElement(By.xpath("//ol[@id='bank']/li"));
			actions.dragAndDrop(drag1, drop1).build().perform();
			Thread.sleep(1000);

			WebElement drag2 = driver.findElement(By.xpath("(//a[@class='button button-orange'])[6]"));
			WebElement drop2 = driver.findElement(By.xpath("//ol[@id='loan']/li"));
			actions.dragAndDrop(drag2, drop2).build().perform();
			Thread.sleep(1000);

			WebElement drag3 = driver.findElement(By.xpath("(//a[@class='button button-orange'])[4]"));
			WebElement drop3 = driver.findElement(By.xpath("//ol[@id='amt8']/li"));
			actions.dragAndDrop(drag3, drop3).build().perform();
			Thread.sleep(1000);

			// Right click
			WebElement rightClick = driver.findElement(By.xpath("//div[@class='top-banner col-md-8']"));
			actions.contextClick(rightClick).perform();

			Thread.sleep(1000);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			driver.close();
		}

	}

	void JsMethods() {
		WebDriver driver = new ChromeDriver();
		try {
			
			driver.get("https://www.bigbasket.com/");
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

			Thread.sleep(1000);

			javascriptExecutor.executeScript("window.scroll(0,3000)");
			Thread.sleep(1000);
			
			javascriptExecutor.executeScript("window.scroll(0,-3000)");
			Thread.sleep(1000);
			
			
			// scroll till end of the page
			javascriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			Thread.sleep(2000);
			
			javascriptExecutor.executeScript("window.scroll(0,0)");
			Thread.sleep(3000);
			
			//locating the webelement
			WebElement imgFruit = driver.findElement(By.xpath("(//img[@loading='lazy'])[21]"));
			
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)",imgFruit);
			Thread.sleep(4000);
			
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(false)",imgFruit);
			Thread.sleep(4000);
			
			
			
			javascriptExecutor.executeScript("arguments[0].click()",imgFruit);
			Thread.sleep(5000);			
			
						
			//refresh the current page
			javascriptExecutor.executeScript("history.go(0)");
			Thread.sleep(5000);
			
			//go back to previous page
			javascriptExecutor.executeScript("history.back()");
			Thread.sleep(5000);
			
			WebElement btnBanana = driver.findElement(By.xpath("(//img[@decoding='async'])[7]"));
			btnBanana.click();
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			driver.quit();		
		}

	}

	void TakesScreenshotMethods() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.bigbasket.com/");

		TakesScreenshot ts = (TakesScreenshot) driver;
		File scFile = ts.getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scFile, new File("C:\\Users\\GayathriN\\Desktop\\screenshot.png"));

			Date d = new Date();
			System.out.println(d);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void Task() {
		WebDriver driver = new ChromeDriver();
		Actions actions = new Actions(driver);

		try {

			Robot robot = new Robot();
			driver.get("https://demo.guru99.com/test/drag_drop.html");

			WebElement ddSelenium = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[1]"));
			actions.click(ddSelenium).perform();

			WebElement lkLogin = driver.findElement(By.linkText("Login"));
			actions.click(lkLogin).perform();

			WebElement txtemail = driver.findElement(By.id("email"));
			// txtemail.sendKeys("abc@gmail.com");
			txtemail.click();
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_SHIFT);

			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_B);
			robot.keyRelease(KeyEvent.VK_B);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyPress(KeyEvent.VK_QUOTE);
			robot.keyRelease(KeyEvent.VK_QUOTE);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_G);
			robot.keyRelease(KeyEvent.VK_G);
			Thread.sleep(400);
			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_I);
			robot.keyRelease(KeyEvent.VK_I);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_L);
			robot.keyRelease(KeyEvent.VK_L);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_PERIOD);
			robot.keyRelease(KeyEvent.VK_PERIOD);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_C);
			robot.keyRelease(KeyEvent.VK_C);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_O);
			Thread.sleep(400);

			robot.keyPress(KeyEvent.VK_M);
			robot.keyRelease(KeyEvent.VK_M);
			Thread.sleep(400);

			WebElement txtpass = driver.findElement(By.name("passwd"));
			txtpass.sendKeys("abc");

			TakesScreenshot ts = (TakesScreenshot) driver;
			File scLogin = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scLogin, new File("C:\\Users\\GayathriN\\Desktop\\login.png"));

			Thread.sleep(3000);

			driver.navigate().back();

			Thread.sleep(3000);

			WebElement btn5000 = driver.findElement(By.xpath("(//a[@class='button button-orange'])[4]"));
			WebElement ph5000 = driver.findElement(By.xpath("//ol[@id='amt7']/li"));
			actions.dragAndDrop(btn5000, ph5000).build().perform();

			File scFile = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scFile, new File("C:\\Users\\GayathriN\\Desktop\\task.png"));

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			driver.close();
		}
	}

	void AlertMethod() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://leafground.com/alert.xhtml;jsessionid=node015fl45afvq0mw12reqsa6b4h9d15741348.node0");
		Thread.sleep(2000);
		//simple alert
		driver.findElement(By.xpath("(//span[text()='Show'])[1]")).click();
		
		Alert popupsimple = driver.switchTo().alert();
		Thread.sleep(2000);
		popupsimple.accept();
		Thread.sleep(2000);
		
		//confirm alert
		driver.findElement(By.xpath("(//span[text()='Show'])[2]")).click();
		Thread.sleep(2000);
		Alert popupConfirm = driver.switchTo().alert();
		Thread.sleep(2000);
		popupConfirm.dismiss();
		Thread.sleep(2000);
		
		//Prompt alert
		driver.findElement(By.xpath("//button[@id='j_idt88:j_idt104']")).click();
		Thread.sleep(2000);
		Alert popupPrompt = driver.switchTo().alert();
		popupPrompt.sendKeys("testing");
		Thread.sleep(2000);
		popupPrompt.accept();
		Thread.sleep(2000);
		
		//Sweet alert
		driver.findElement(By.xpath("(//span[text()='Show'])[6]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-minus']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-plus']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//span[@class='ui-icon ui-icon-closethick'])[3]")).click();
		Thread.sleep(2000);		
		
	}
	
	void FramesMethod() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://leafground.com/frame.xhtml;jsessionid=node0l3up8rls7kwb1a3g6kbte3b2p15772771.node0");
		
		//switch to frame 0 to click the button
		driver.switchTo().frame(0);
		Thread.sleep(2000);		
		driver.findElement(By.id("Click")).click();
		Thread.sleep(2000);		
		
		//switch back out of the frame
		driver.switchTo().defaultContent();
		
		//switch to frame 1 to get the text
		driver.switchTo().frame(1);
		
		System.out.println(driver.findElement(By.id("Click")).getText());
		Thread.sleep(2000);	
		
		//switch back out of the frame
		driver.switchTo().defaultContent();
		
		//switch to frame 2 to click the button
		driver.switchTo().frame(2);
		driver.switchTo().frame(0);
		driver.findElement(By.id("Click")).click();
		
		driver.switchTo().parentFrame();
		List<WebElement> lstframe2 = driver.findElements(By.tagName("iframe"));
		int s2 = lstframe2.size();
		System.out.println(s2);
		
		driver.switchTo().parentFrame();
		List<WebElement> lstform = driver.findElements(By.tagName("iframe"));
		System.out.println(lstform.size());
		
		
	}
	
	void WebtableMethods() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.get("https://qavbox.github.io/demo/webtable/");
		
		WebElement tb1 = driver.findElement(By.id("table01"));
		
		System.out.println("------Header-------");
		WebElement tbHeader = tb1.findElement(By.tagName("thead"));
		String txtHeader = tbHeader.getText();
		System.out.println(txtHeader);
		
		System.out.println("------Body-------");
		//WebElement tbBody = tb1.findElement(By.tagName("tbody")); or below code --both code works
		WebElement tbBody = driver.findElement(By.tagName("tbody"));
		String txtBody = tbBody.getText();
		System.out.println(txtBody);
		
		System.out.println("------Row-------");
		WebElement tbrow = driver.findElement(By.xpath("//table[@id='table01']//tbody//tr[3]"));
		String txtrow = tbrow.getText();
		System.out.println(txtrow);
		
		System.out.println("------Cell-------");
		//WebElement tbcell = tb1.findElement(By.xpath("//table[@id='table01']//tbody//tr[2]//td[3]"));
		//table[@id='table01']/tbody/tr[2]/td[3]---this also works
		WebElement tbcell = tb1.findElement(By.xpath("((//table[@id='table01']//tbody//tr)[2]//td)[3]"));
		String txtcell = tbcell.getText();
		System.out.println(txtcell);
		Thread.sleep(2000);
		
		tb1.findElement(By.xpath("//table[@id='table01']//tbody//tr[1]//td[1]")).click();
		Thread.sleep(2000);
		tb1.findElement(By.xpath("//table[@id='table01']//tbody//tr[1]//td[5]")).click();
		Thread.sleep(2000);
		
		//scroll the webtable
		WebElement tb2 = driver.findElement(By.id("table02"));
		Thread.sleep(4000);
		CommonClass commonClass = new CommonClass();
		JavascriptExecutor js = (JavascriptExecutor) driver;	    
	    js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", tb2);
		//commonClass.scrollTable(driver,tb2, ScrollAction.BOTTOM);
		Thread.sleep(4000);
		js.executeScript("arguments[0].scrollTop = 0;", tb2);
		//commonClass.scrollTable(driver,tb2,ScrollAction.TOP);
		Thread.sleep(4000);
		
		driver.close();
	}
	
	void SelectMethod() throws InterruptedException {
		//Open the window in incognito window
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
		//Added Thread.sleep(5000) to test the condition if popup did not appear.In the mean while, ill manually close the popup. 
		//Thread.sleep(5000);
		try {
			driver.findElement(By.xpath("//p[text()='Manage options']")).click();
			driver.findElement(By.xpath("(//p[text()='Confirm choices'])[1]")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fc-dialog-container")));
			System.out.println("Popup handled successfully");
		}
		catch(Exception ex) {
			System.out.println("Popup did not appear, continuing...");
		}
		//Thread.sleep(3000);
		//driver.manage().timeouts().implicitlyWait(Duration.ofHours(2));
		
		WebElement ddCountry = driver.findElement(By.tagName("select"));
		//WebElement ddCountry = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("select")));
		Select sddCountry = new Select(ddCountry);
		
		sddCountry.selectByContainsVisibleText("Angola");
		Thread.sleep(1000);
		sddCountry.selectByIndex(10);
		Thread.sleep(1000);
		sddCountry.selectByValue("ARG");
		Thread.sleep(1000);
		
		//Get the size of the dropdown
		List<WebElement> lstCountry = sddCountry.getOptions();
		System.out.println(lstCountry.size());
		
		for(WebElement country : lstCountry) {
			System.out.println(country.getText());
		}
		
	
	}
	
	void XpathSiblingMethod() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://qavbox.github.io/demo/webtable/");
		//following sibling
		WebElement cellName = driver.findElement(By.xpath("//td[text()='Functional']/following-sibling::td"));
		System.out.println(cellName.getText());
		
		//Preceding sibling-immedialty next and the first cell in the row
		WebElement cellPreceding = driver.findElement(By.xpath("//td[text()='Functional']/preceding-sibling::td"));
		cellPreceding.click();
		
		//following sibling:next to next sibling
		WebElement cellPreceding1 = driver.findElement(By.xpath("//td[text()='Functional']/following-sibling::td[3]"));
		cellPreceding1.click();
		
		WebElement cellPreceding3 = driver.findElement(By.xpath("//td[text()='GUI']/preceding-sibling::td"));
		cellPreceding3.click();
		
		//ancestor and sibling used together.
		WebElement cellPreceding2 = driver.findElement(By.xpath("//a[text()='Selenium']/ancestor::td/following-sibling::td[2]"));
		cellPreceding2.click();

		
	}
	
	
	void FacebookDropdown(){
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.get("https://en-gb.facebook.com/reg/?entry_point=login&next=");	
		
		driver.findElement(By.xpath("(//span[text()='Decline optional cookies'])[1]")).click();
		
	    By yearDropdown = By.xpath("//span[text()='Year']/ancestor::div[@role='combobox']");	    
	    wait.until(ExpectedConditions.elementToBeClickable(yearDropdown)).click();    
	   
		
	    
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='2025']"))).click();	  
	  
	}
	

}
