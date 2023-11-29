package testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;


import utilities.Readconfig;


public class Baseclass {
	
	Readconfig readconfig= new Readconfig();

	public String baseurl = readconfig.getbasenurl();
	public String username = readconfig.getusername();
	public String password = readconfig.getpassword();
	public static WebDriver driver;
	
	public static Logger log;
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String brow) {
		
		
		log = Logger.getLogger("Demoweapps");
		PropertyConfigurator.configure("log4j.properties");
		
		if (brow.equals("chrome"))
		{
//		System.setProperty("webdriver.chrome.driver",readconfig.getchromepath() );
		System.out.println("before chrome driver");
		driver = new ChromeDriver();
		System.out.println("browser launched successfully ....");
		}else if (brow.equals("firefox")) {
//			System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath() );
			driver = new FirefoxDriver();
			System.out.println("browser launched successfully ....");
		}else if (brow.equals("ie")) {
//			System.setProperty("webdriver.ie.driver",readconfig.getiepath() );
			driver = new InternetExplorerDriver();
			System.out.println("Orange url launched successfully ....");
		}
		
		// wait for some time for each element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseurl);
		System.out.println("OrangeHRM url is launched successfully ....");
	}

	@AfterTest
	public void teardown() throws InterruptedException {
		
		log.info("end");

		Thread.sleep(1000);
		//driver.quit();
	}

}