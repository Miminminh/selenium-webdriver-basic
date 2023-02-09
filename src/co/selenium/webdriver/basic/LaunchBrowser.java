package co.selenium.webdriver.basic;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class LaunchBrowser {

	public static WebDriver driver = null;

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// open the web app
		driver.navigate().to("https://www.amazon.in/");
		driver.manage().window().maximize();

		// get title
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Amazon.in"))
			System.out.println("Title matches");
		else
			System.out.println(title);

		// locate a web element
		String tagName = "";
		tagName = driver.findElement(By.cssSelector("#nav-xshop > a:nth-child(3)")).getText();
		System.out.println("Tag name: " + tagName);

		// Click to books
		WebElement searchDropdown = driver.findElement(By.cssSelector("#searchDropdownBox"));
		Actions action = new Actions(driver);
		action.click(searchDropdown).perform();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//option[@value='search-alias=stripbooks']")).click();
		Thread.sleep(2000);

		// Typing the text & click the search icon
		WebElement bookSearchElement = driver.findElement(By.id("twotabsearchtextbox"));
		bookSearchElement.sendKeys("John Grisham the firm");
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("#nav-search-submit-button")).click();
		Thread.sleep(2000);

		// Select the book
		driver.findElement(By.partialLinkText("Firm")).click();
		Thread.sleep(2000);

		// Shift the tab control
		Set<String> handles = driver.getWindowHandles();
		String windowHandle1 = driver.getWindowHandle();
		handles.remove(windowHandle1);

		String windowHandle = handles.iterator().next();
		String windowHandle2 = "";
		if (windowHandle != windowHandle1) {
			windowHandle2 = windowHandle; // Storing handle of second window handle

			// Switch control to new tab
			driver.switchTo().window(windowHandle2);
			System.out.println(windowHandle2);
		}
		Thread.sleep(2000);

		// add to cart
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(5000);

		// scroll to the end of web page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(3000);

		// Iframes
		driver.get("https://demo.automationtesting.in/Frames.html");
		WebElement frame = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(frame);
		Thread.sleep(2000);

		WebElement textbox = driver.findElement(By.xpath("//input[@type='text']"));
		textbox.sendKeys("hey");
		Thread.sleep(3000);

		driver.close();
		driver.quit();
	}
}
