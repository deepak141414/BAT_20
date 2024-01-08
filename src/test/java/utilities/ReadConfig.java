package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.bouncycastle.crypto.RuntimeCryptoException;

public class ReadConfig {
	
	Properties properties;
	String path="config.properties";
	
	public ReadConfig() throws Exception {
		
		properties=new Properties();
		FileInputStream fis=new FileInputStream(path);
		properties.load(fis);
		
	}
	
	public String getbrowser() {
		String value=properties.getProperty("browser");
		if(value!=null) {
			
		return value;
		}
		else {
			throw new RuntimeException("browser is not found in config file");
		}
	}

}
