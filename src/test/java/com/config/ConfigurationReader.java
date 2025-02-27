package com.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationReader {
    public static Configuration config() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
