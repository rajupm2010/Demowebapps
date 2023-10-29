package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;


// Listener runs trigger onTestStart and onTestSuccess/onTestFailure/onTestSkipped
// TestNG will update in report
import org.testng.TestListenerAdapter;

import testcases.Baseclass;

public class Listeners extends TestListenerAdapter {

	public void onTestStart(ITestResult br) {

		System.out.println("onTestStart");
	}

	public void onTestSuccess(ITestResult br) {

		System.out.println("onTestSucess");
	}
	
	public void onTestFailure(ITestResult br) {

		System.out.println("onTestFailuer");
		
		if(br.getStatus()== ITestResult.FAILURE) {
			
			// capture screenshot
			File path = ((TakesScreenshot) Baseclass.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(path, new File(".//Screenshots//image.png"));
				System.out.println("screen shot capture is successfull");
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
			
			
			
	}
	
	
	public void onTestSkipped(ITestResult br) {

		System.out.println("onTestSkipped");
						
		}
	}
	
	
