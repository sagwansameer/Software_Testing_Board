package com.build.stb.base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ItestListener implements ITestListener{
	private UtilTest util= new UtilTest();
	private Singleton singelton= Singleton.getInstance();
	private Logger logger= LogManager.getLogger(ItestListener.class);

	public void onTestStart(ITestResult result) {
		
		
		
	}

	public void onTestSuccess(ITestResult result) {
		
	
		  
		
	}

	/*
	 * @author Sameer Sagwan: This method will be executed for every failed test case.
	 */
	public void onTestFailure(ITestResult result) {
		logger.error("Entered in OnTestFailure: ItestListner");
		logger.error(result.getName()+" testcase is failed");
		Reporter.log(result.getName()+" testcase is failed");
		if (result.getStatus()==2) {
			try {
			
				util.takeScreen(result.getName(), singelton.getDriver());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				String str= e.getMessage();
				logger.error(result.getName()+" testcase is failed and giving below error");
				logger.error(str);
				e.printStackTrace();
			}
	        }
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
