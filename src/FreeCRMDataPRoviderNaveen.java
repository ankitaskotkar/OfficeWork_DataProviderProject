import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FreeCRMDataPRoviderNaveen {
	WebDriver driver ;
	
	@BeforeTest
	public void setUp(){
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\akotkar\\Downloads\\chromedriver_win32\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get("https://ui.freecrm.com/");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	}
	
	@DataProvider(name="Authenticate")
	public Object[][] getTestData() {
		
		return new Object[][] {
			{"ankitaskotkar@gmail.com", "Music#321"},
			{"ankita1","123"}
		};
	}

	@Test(dataProvider="Authenticate")
	public void login(String email, String password) {
		
		WebElement emailbox=driver.findElement(By.name("email"));
		emailbox.sendKeys(email);
		System.out.println(email);
		
		WebElement passwordbox=driver.findElement(By.name("password"));
		passwordbox.sendKeys(password);	
		System.out.println(password);	
	}
		
	@AfterMethod
	public void logOut() {
				
		driver.findElement(By.xpath("//div[starts-with(@class,'ui fluid')]")).click();
		driver.findElement(By.xpath("//div[@role=\"listbox\"]")).click();
		driver.findElement(By.cssSelector("div.menu.transition.visible>a:nth-of-type(5)")).click();			
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("password")).clear();
	}

	@AfterTest
	public void tearDown() {
		
		driver.quit();
	}
	
	
}
