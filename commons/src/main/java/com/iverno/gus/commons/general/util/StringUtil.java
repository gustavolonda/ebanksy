package com.iverno.gus.commons.general.util;

import java.util.Random;

public class StringUtil {
	public static String  accountNumGenerate() {
		return new Random().ints(20, 1, 9).toString();
		
	}

}
