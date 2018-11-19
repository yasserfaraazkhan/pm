package utility;

import java.io.InputStream;
import java.util.Properties;

/*******************************************************
 * Class name: Attributes: Methods: Functionality:This class is responsible for
 * parsing .properties file and load test configurations
 *******************************************************/
public class ConfigParser {
	private static Properties propertyValues;

	public static void setConfigParser(String fileName) {
		propertyValues = new Properties();
		InputStream is = ConfigParser.class.getResourceAsStream(fileName);
		try {
			propertyValues.load(is);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Retrieve the property value based on the property name
	 * 
	 * @param locatorName
	 * @return property value
	 */
	public static String getProperty(String locatorName) {
		return propertyValues.getProperty(locatorName);
	}

	public static void setProperty(String property, String value) {
		propertyValues.setProperty(property, value);
	}
}