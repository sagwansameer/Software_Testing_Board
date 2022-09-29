package com.build.stb.base;

import java.util.Random;

public class BaseStrings {
	/*
	 * @author Sameer Sagwan: This static block is used to initialize the static
	 * final variable to generate the random string to be used as password this us.
	 */
	static {
		Random rand = new Random();
		int random = rand.nextInt(1000000);
		password = "Pa@" + Integer.toString(random);
	}
	public static final String password;
	public static final String proceedButton = "Proceed";
	public static final String errorMessage = "There is already an account with this email address";
	public static final String successMessage = "Thank";

}
