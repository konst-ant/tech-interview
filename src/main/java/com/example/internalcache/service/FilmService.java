package com.example.internalcache.service;

import com.example.internalcache.model.CheckResult;
import com.example.internalcache.model.Films;

public interface FilmService {

    CheckResult check();

    Films getFilms();
}
