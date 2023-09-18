package com.example.internalcache.service;

import com.example.internalcache.model.CheckResult;

public interface CacheService {
    CheckResult saveCheckResult(String sessionId, CheckResult checkResult);

    CheckResult getCheckResultIfExists(String sessionId);
}
