package se.gokopen.service;

import se.gokopen.persistence.entity.Config;

public interface ConfigService {

    Config getCurrentConfig();

    void saveConfig(Config config);

}