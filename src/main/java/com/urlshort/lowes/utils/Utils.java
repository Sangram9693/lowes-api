package com.urlshort.lowes.utils;

import java.util.Random;

public class Utils {
	
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	public static String generateShortText() {
		Random random = new Random();
		StringBuilder builder = new StringBuilder(10);

	    for (int i = 0; i < 10; i++) {
	        builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
	    }

	    return builder.toString();
	}

}
