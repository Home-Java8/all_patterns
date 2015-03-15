package com.multithreading.dialogpackage;

/**
 * This class will contain various methods for testing
 * 
 * @author		Cheol-Woo Jung (cjung@gatech.edu)
 * @version	
 */
public class Debugger {

	/**
	 * Prints out the message along with the class name from which this is called
	 * if debug mode is on
	 * 
	 * @param debug		<code>true</code> if debug mode is on;
	 * 								<code>false</code> otherwise	
	 * @param instance	the class in which this method is called
	 * @param message	the message to be printed
	 */
	public static void printMessage(boolean debug, Object instance, String message) {
		if (debug) {
			String className = instance.getClass().getName();
			System.out.println("[" + className + "]:\t" + message);
		}
	}
}
