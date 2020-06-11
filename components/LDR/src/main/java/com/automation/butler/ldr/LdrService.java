package com.automation.butler.ldr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LdrService {

    private final LdrRepository ldrRepository;

    private final LdrConfigRepository ldrConfigRepository;

    @Autowired
    public LdrService(LdrRepository ldrRepository, LdrConfigRepository ldrConfigRepository) {
        this.ldrRepository = ldrRepository;
        this.ldrConfigRepository = ldrConfigRepository;
    }

    void store(LdrDTO value) {
        log.info("Storing {}", value);
        ldrRepository.save(value);
    }

    LdrConfig getConfigByAlias(String alias) {
        Optional<LdrConfig> ldrConfig = ldrConfigRepository.findById(alias);
        return ldrConfig.orElse(null);
    }

    void createDefaultConfig(String alias) {
        LdrConfig ldrConfig = new LdrConfig();
        ldrConfig.setAlias(alias);
        ldrConfigRepository.save(ldrConfig);
    }

}
