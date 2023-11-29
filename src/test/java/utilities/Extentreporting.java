package utilities;

// Listener class used to generate extent reports


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Listener runs trigger onTestStart and onTestSuccess/onTestFailure/onTestSkipped
//Extentreport has to crate report content
public class Extentreporting extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testcontext) {
		
		// html report file name with date and time generator
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		String repName = "Test-Reports-" + timestamp + ".html";
				
		// start reporters // store report in specific location
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/extentreport-output/" + repName); 
		
		//extent-config.xml
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		// create ExtentReports
		extent = new ExtentReports();
		
		// attach reporter(s) and other optional info
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("host name", "loclahost");
		extent.setSystemInfo("Envionmet", "QA");
		extent.setSystemInfo("user name", "Raju");
		
		// report configuration using extent-config.xml

		htmlReporter.config().setDocumentTitle("Demowebapps"); // title of report
		htmlReporter.config().setReportName("Fuctional Test Report"); // name of report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of report
		htmlReporter.config().setTheme(Theme.DARK);
	}

	
	// creates a toggle for the given test, adds all log events under it    
	public void onTestSuccess(ITestResult tr) {
		
		logger = extent.createTest(tr.getName()); // crate new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
																							// information

	}

	public void onTestFailure(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // crate new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information

		String screenshotpath = System.getProperty("user.dir") + "\\screenshots\\"+tr.getName()+".png";
		
		//System.out.println(screenshotpath);
		
			
		//if (true){  
		
		try {
			logger.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
			// test with snapshot
	        logger.addScreenCaptureFromPath(screenshotpath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*
			File f = new File(screenshotpath);
				
		if (f.exists()) {
			try {
				logger.fail("screenshot is below:" + logger.addScreenCaptureFromPath(screenshotpath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			}
			
			*/
	}

	public void onTestSkipped(ITestResult tr) {

		logger = extent.createTest(tr.getName()); // crate new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE)); // send the passed information

			}
	
	public void onFinish(ITestContext testcontext) {

		extent.flush(); //push the test report

			}
	

	
	
	
	// end
}
