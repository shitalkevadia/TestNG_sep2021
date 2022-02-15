package variousconcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CRMTest {
	WebDriver driver;
	String browser = null;
	String url;
	
	//creating globle variable and storing webelement
	//By userNameLocator = By.xpath("//input[@id='username']");
	//By passWordLocator = By.xpath("//input[@id='password']");
	By signinbuttonLocator = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By dashboardHeaderLocator = By.xpath("//h2[contains(text(), 'Dashboard')]");
	
	By USERNAME_FIELD = By.xpath("//input[@id='username']");
	By PASSWORD_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
	//By SIGNIN_BUTTON_FIELD
	
	//login data globle variable
	String username = "demo@techfios.com";
	String password = "abc123";
	
	//Test or moc data globle variable
	//String fullname = "";
	//String company = "";
	//String email = "";
	//String country = "";
	
	@BeforeClass
	public void readConfig() {
		
		
		Properties prop = new Properties();
		
		try {
			InputStream input = new FileInputStream("src\\test\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used:" + browser);
			url = prop.getProperty("url");
			}
		catch(IOException e) {
			e.printStackTrace();
			
		}
			
	}
	
	@BeforeMethod
	public void init() {
		
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://www.techfios.com/billing/?ng=login/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

    @Test
	public void loginTest() {
		
		
		
		driver.findElement(USERNAME_FIELD).sendKeys(username);
		driver.findElement (PASSWORD_FIELD).sendKeys(password);
		driver.findElement(signinbuttonLocator).click();
		
		String dashboardheader = driver.findElement(dashboardHeaderLocator).getText();
		Assert.assertEquals(dashboardheader, "Dashboard1", "dashboard not found");
		
}  @Test
   public void addContact() {
	    driver.findElement(USERNAME_FIELD).sendKeys(username);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(signinbuttonLocator).click();
		
		String dashboardheader = driver.findElement(dashboardHeaderLocator).getText();
		Assert.assertEquals(dashboardheader, "Dashboard1", "dashboard not found");
		
	  
  }  
    
    
    
    
    @AfterMethod
	public void tearDown(){
    	driver.close();	
    	driver.quit();
    	
    	
    	
	}
}
