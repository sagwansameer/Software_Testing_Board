package com.build.stb.base;

import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ItestListener implements ITestListener{
	private UtilTest util= new UtilTest();
	private Singleton singelton= Singleton.getInstance();

	public void onTestStart(ITestResult result) {
		System.out.println("this before start test");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		System.out.println("this is on success");
		      
		  
		
	}

	public void onTestFailure(ITestResult result) {
		
		
		System.out.println(result.getName()+" testcase is failed");  
		
		if (result.getStatus()==2) {
			try {
				util.takeScreen(result.getName(), singelton.getDriver());
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
