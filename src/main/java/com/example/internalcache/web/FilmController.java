package com.example.internalcache.web;

import com.example.internalcache.model.CheckResult;
import com.example.internalcache.model.Films;
import com.example.internalcache.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/check")
    public ResponseEntity<CheckResult> check() {
        return ResponseEntity.ok(filmService.check());
    }

    @GetMapping("/getFilms")
    public ResponseEntity<Films> getFilms() {
        return ResponseEntity.ok(filmService.getFilms());
    }


}
