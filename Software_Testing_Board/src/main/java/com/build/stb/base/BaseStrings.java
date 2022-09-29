package com.build.stb.base;

import java.util.Random;

public class BaseStrings {
	static {
		Random rand = new Random();
		// Generate random integers in range 0 to 999
		int random = rand.nextInt(1000000);
		password = "Pa@"+Integer.toString(random);
		
	}
	//public static final String browser = "Chrome";
	public static final String password;
	public static final String proceedButton = "Proceed";
	public static final String errorMessage="There is already an account with this email address";
	public static final String successMessage="Thank";

}
