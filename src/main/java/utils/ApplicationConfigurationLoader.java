package utils;

import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigFactory;

@NoArgsConstructor
public class ApplicationConfigurationLoader {

    private static ApplicationConfiguration config;

    public static ApplicationConfiguration getConfig() {
        if (config == null) {
            config = ConfigFactory.create(ApplicationConfiguration.class);
        }
        return config;
    }
}
