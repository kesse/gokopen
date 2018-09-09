package se.gokopen.service;

import se.gokopen.persistence.entity.ConfigEntity;

public interface ConfigService {

    ConfigEntity getCurrentConfig();

    void saveConfig(ConfigEntity config);

}