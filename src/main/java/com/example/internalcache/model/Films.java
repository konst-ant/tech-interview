package com.example.internalcache.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Films {

    @JsonAlias("results")
    @JsonProperty("films")
    List<Film> films;
}
