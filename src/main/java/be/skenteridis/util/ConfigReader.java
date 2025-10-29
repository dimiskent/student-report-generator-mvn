package be.skenteridis.util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();
    static {
        try(InputStream stream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(stream);
        } catch (Exception e) {
            throw new RuntimeException("Can't get configuration! " + e.getMessage());
        }
    }
    public static String get(String key) {
        return props.getProperty(key);
    }
}
