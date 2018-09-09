package se.gokopen.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.ConfigEntity;
import se.gokopen.persistence.repository.ConfigRepository;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;
    
    @Override
    @Transactional
    public ConfigEntity getCurrentConfig() {
        Iterable<ConfigEntity> configs = configRepository.findAll();
        Iterator<ConfigEntity> configIterator = configs.iterator();


        if (!configIterator.hasNext()) {
            ConfigEntity config = new ConfigEntity();
            config.setName("Gök Open");

            return config;
        }

        return configIterator.next();
    }
    
    @Override
    @Transactional
    public void saveConfig(ConfigEntity config){
        configRepository.save(config);
    }
}
