package com.example.internalcache.service;

import com.example.internalcache.client.SwapiClient;
import com.example.internalcache.model.CheckResult;
import com.example.internalcache.model.Films;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FilmServiceImpl implements FilmService {

    private final Logger log = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final CacheService cacheService;

    private final SwapiClient swapiClient;


    public FilmServiceImpl(CacheService cacheService,
                           SwapiClient swapiClient) {
        this.cacheService = cacheService;
        this.swapiClient = swapiClient;
    }

    private String getSessionId() {
        return UUID.randomUUID().toString();
    }

    public CheckResult check() {
        /**
         * Some business logic goes here
         */
        CheckResult checkResult = new CheckResult();
        checkResult.setStatus(true);
        checkResult.setFailedCheck("none");
        checkResult.setMessage("All checks were successful !");

        /**
         * ---=== WRITE CACHE ===---
         * Now that we have result we can save it into cache
         */
        String sessionId = getSessionId();
        cacheService.saveCheckResult(sessionId, checkResult);
        log.info("Written into cache: {}={}", sessionId, checkResult);

        /**
         * ---=== READ CACHE ===---
         * Ensure the result exists
         */
        CheckResult checkResultExisting;
        checkResultExisting = cacheService.getCheckResultIfExists(sessionId);

        if (checkResultExisting == null) {
            log.error("CACHE EMPTY FOR: {}", sessionId);
        } else {
            log.info("Read from cache: {}={}", sessionId, checkResultExisting);
        }

        return checkResultExisting;
    }

    @Cacheable(value = "filmData", key = "'films'")
    public Films getFilms() {
        log.info("Going to call external Swapi now!!!");
        Films result = swapiClient.getFilms();
        return result;
    }

}
