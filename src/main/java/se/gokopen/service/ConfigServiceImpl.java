package se.gokopen.service;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.gokopen.persistence.entity.Config;
import se.gokopen.persistence.repository.ConfigRepository;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;
    
    @Override
    @Transactional
    public Config getCurrentConfig() {
        Iterable<Config> configs = configRepository.findAll();
        Iterator<Config> configIterator = configs.iterator();


        if (!configIterator.hasNext()) {
            Config config = new Config();
            config.setName("Gök Open");

            return config;
        }

        return configIterator.next();
    }
    
    @Override
    @Transactional
    public void saveConfig(Config config){
        configRepository.save(config);
    }
}
