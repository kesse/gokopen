package se.gokopen.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import se.gokopen.SpringBootTestBase;
import se.gokopen.persistence.entity.ConfigEntity;

import static org.junit.Assert.assertEquals;

public class ConfigServiceImplTest extends SpringBootTestBase {

    @Autowired
    private ConfigService configService;

    @Test
    public void testGetAndSaveConfig() {
        ConfigEntity config = configService.getCurrentConfig();

        assertEquals("GÖK Open", config.getName());

        String name = "test name";
        ConfigEntity saveOld = new ConfigEntity();
        saveOld.setName(name);

        configService.saveConfig(saveOld);


        ConfigEntity newConfig = configService.getCurrentConfig();

        assertEquals(name, newConfig.getName());
    }
}
