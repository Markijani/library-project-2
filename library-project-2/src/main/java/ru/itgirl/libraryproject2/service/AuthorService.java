package ru.itgirl.libraryproject2.service;

import ru.itgirl.libraryproject2.dto.AuthorCreateDto;
import ru.itgirl.libraryproject2.dto.AuthorDto;
import ru.itgirl.libraryproject2.dto.AuthorUpdateDto;

import java.util.List;

public interface AuthorService {
    AuthorDto getAuthorById(Long id);

    AuthorDto getByNameV1(String name);

    AuthorDto getByNameV2(String name);

    AuthorDto getByNameV3(String name);

    AuthorDto createAuthor(AuthorCreateDto authorCreateDto);

    AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto);

    void deleteAuthor(Long id);
    List<AuthorDto> getAllAuthors();
}
