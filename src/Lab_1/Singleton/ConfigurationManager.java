package Lab_1.Singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;
    private static Properties properties;

    private ConfigurationManager() {
        try {
            properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/Lab_1/Singleton/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            Logger.getLogger(ConfigurationManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static synchronized ConfigurationManager getInstance() {
        if (configurationManager == null) configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public int getPropertyFromConfig() {
        return Integer.parseInt(properties.getProperty("SINGLETON_PROPERTY", "0"));
    }

}