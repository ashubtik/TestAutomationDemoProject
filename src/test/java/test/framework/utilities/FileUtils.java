package test.framework.utilities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class FileUtils {
    private final static String CONFIG_FILEPATH = "src/test/resources/config.properties";
    public static Properties properties;

    public static Properties getProperties() {
        if (properties == null) {
            loadProperties();
        }
        return properties;
    }

    private static void loadProperties() {
        try (var configFileReader = new ByteArrayInputStream(Files.readAllBytes(Paths.get(CONFIG_FILEPATH)))) {
            properties = new Properties();
            properties.load(configFileReader);
        } catch (IOException e) {
            throw new RuntimeException("Properties file not found");
        }
    }

}
