package com.automation.butler.ldr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LdrService {

    private final LdrRepository ldrRepository;

    @Autowired
    public LdrService(LdrRepository ldrRepository) {
        this.ldrRepository = ldrRepository;
    }

    void store(LdrDTO value) {
        log.info("Storing {}", value);
        ldrRepository.save(value);
    }

}
