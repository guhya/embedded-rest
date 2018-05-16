package gframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	
	private static Properties config = new Properties();
	
	static{
		InputStream is = null;
		try{
			final String env = System.getProperty("env");
			is = ConfigUtil.class.getResourceAsStream("/gframework/properties/application-"+env+".properties");
			config.load(is);
		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				if(is != null) is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getString(String key){
		return config.getProperty(key);
	}
	
	public static int getInt(String key){
		return Integer.parseInt(config.getProperty(key));
	}
	
	public static long getLong(String key){
		return Long.parseLong(config.getProperty(key));
	}
	
	public static double getDouble(String key){
		return Double.parseDouble(config.getProperty(key));
	}
	
}
