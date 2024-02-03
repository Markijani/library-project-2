package ru.itgirl.libraryproject2.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itgirl.libraryproject2.dto.GenreDto;
import ru.itgirl.libraryproject2.service.GenreService;

@RestController
@RequiredArgsConstructor
public class GenreRestController {
    private final GenreService genreService;

    @GetMapping("/genre/{id}")
    GenreDto getBookByGenreId(@PathVariable("id") Long id){
        return genreService.getGenreById(id);
    }
}
