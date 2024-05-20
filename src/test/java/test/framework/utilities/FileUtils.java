package test.framework.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
    private final static String CONFIG_FILEPATH = "C:/Users/artsiom.shubtsik/IdeaProjects/TestFramework/src/test/resources/config.properties";
    public static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() {
        try (FileInputStream configFileReader = new FileInputStream(CONFIG_FILEPATH)) {
            properties = new Properties();
            properties.load(configFileReader);
        } catch (IOException e) {
            throw new RuntimeException("Properties file not found");
        }
    }

}
