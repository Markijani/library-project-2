package ru.itgirl.libraryproject2.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itgirl.libraryproject2.dto.AuthorCreateDto;
import ru.itgirl.libraryproject2.dto.AuthorDto;
import ru.itgirl.libraryproject2.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject2.service.AuthorService;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "library-users")
public class AuthorRestController {

    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author/v1")
    AuthorDto getAuthorsByName(@RequestParam("name") String name) {
        return authorService.getByNameV1(name);
    }

    @GetMapping("/author/v2")
    AuthorDto getAuthorsByNameV1(@RequestParam("name") String name) {
        return authorService.getByNameV2(name);
    }

    @GetMapping("/author/v3")
    AuthorDto getAuthorsByNameV2(@RequestParam("name") String name) {
        return authorService.getByNameV3(name);
    }

    @PostMapping("/author/create")
    AuthorDto createAuthor(@RequestBody @Valid AuthorCreateDto authorCreateDto) {
        return authorService.createAuthor(authorCreateDto);
    }

    @PutMapping("/author/update")
    AuthorDto updateAuthor(@RequestBody @Valid AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @DeleteMapping("/author/delete/{id}")
    void deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}


