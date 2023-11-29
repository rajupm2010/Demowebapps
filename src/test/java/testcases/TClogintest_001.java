package testcases;


import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobjects.Loginpage;

public class TClogintest_001 extends Baseclass {


	@Test
	public void logintest() {

		Loginpage lp = new Loginpage(driver);
		lp.setusername(username);
		lp.setpassword(password);
		lp.clicksubmit();

		System.out.println("logintest executed successfull");
		String actualtitle =driver.getTitle();
		
		System.out.println(actualtitle);
		
		
		String expectedtitle ="OrangeHRM";
		
		Assert.assertEquals(actualtitle, expectedtitle);
		
		System.out.println("end of the program");
	}


	

	
	

}
