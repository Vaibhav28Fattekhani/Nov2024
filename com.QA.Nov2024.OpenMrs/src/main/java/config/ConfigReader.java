package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	private static String path = System.getProperty("user.dir") + "//src//main//java//resources//config.properties";
	private static String value;

	public static String getProperty(String key) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			prop = new Properties();
			prop.load(fis);
			value = prop.getProperty(key);
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}

}
