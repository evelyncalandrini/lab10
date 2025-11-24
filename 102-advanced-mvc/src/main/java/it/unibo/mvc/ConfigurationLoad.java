package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.InputStream;

public class ConfigurationLoad {
    private static final String CONFIG_FILE = "config.yml";
    public Configuration loadConfiguration(){
        final Configuration.Builder builder = new Configuration.Builder();
        InputStream input = ClassLoader.getSystemResourceAsStream(CONFIG_FILE);
        if (input == null) 
            throw new IllegalStateException("Configuration file not found: " + CONFIG_FILE);

       try(BufferedReader br = new BufferedReader(new java.io.InputStreamReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String[] parts = line.split(":");
                if (parts.length == 2) {
                    final String key = parts[0].trim();
                    final String value = parts[1].trim();
                    int intValue = Integer.parseInt(value);
                    switch (key) {
                        case "minimum":
                            builder.setMin(intValue);
                            break;
                        case "maximum":
                            builder.setMax(intValue);
                            break;
                        case "attempts":
                            builder.setAttempts(intValue);
                            break;
                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error reading configuration file", e);
        }
        return builder.build();
    }
    

    
}