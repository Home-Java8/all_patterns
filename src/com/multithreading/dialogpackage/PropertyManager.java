package com.multithreading.dialogpackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * This class manages the System and GART specific properties 
 * 
 * @author Cheol-Woo Jung (cjung@gatech.edu)
 * @version
 */
public class PropertyManager {

	private static final String PROPERTY_FILE = "properties.xml";

	private static Properties properties;
	
	/**
	 * Loads the GART property file along with the system property
	 */
	public static void init() {
		try {
			InputStream inStream = new FileInputStream(PROPERTY_FILE);
			properties = new Properties(System.getProperties());
			properties.loadFromXML(inStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperties(properties);
	}

	/**
	 * Makes sure the properties are set
	 */
	private static void ensurePropertiesSet() {
		if (properties == null) {
			init();
		}
	}

	/**
	 * See {@link java.util.Properties.getProperties}
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		ensurePropertiesSet();
		String property = System.getProperty(key).trim();
		if (property == null) {
			throw new RuntimeException("Property '" + key + "' does not exist");
		}
		return property;
	}

	/**
	 * Adds a new property
	 * 
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, String value) {
		ensurePropertiesSet();
		properties.setProperty(key, value);
	}

	public static void removeProperty(String key) {
		ensurePropertiesSet();
		properties.remove(key);
	}
	/**
	 * Saves the GART properties to XML file
	 */
	public static void store() {
		OutputStream outStream;
		try {
			outStream = new FileOutputStream(new File(PROPERTY_FILE));
			properties.storeToXML(outStream, "GART");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Testing
	 * 
	 * @param args	not used
	 */
	public static void main(String[] args) {
		PropertyManager.init();
		PropertyManager.store();
	
		System.getProperties().list(System.out);
		//System.out.print(System.getProperty("java.class.path"));
	}
}
