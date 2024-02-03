package ru.itgirl.libraryproject2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject2.dto.AuthorDto;
import ru.itgirl.libraryproject2.dto.BookDto;
import ru.itgirl.libraryproject2.dto.GenreDto;
import ru.itgirl.libraryproject2.model.Genre;
import ru.itgirl.libraryproject2.repository.GenreRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        log.info("Try to find genre by id {}", id);
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            GenreDto genreDto = convertEntityToDto(genre.get());
            log.info("Genre: {}", genreDto.toString());
            return genreDto;
        } else {
            log.error("Genre with id {} not found", id);
            throw new NoSuchElementException("No value present");
        }
    }

    private GenreDto convertEntityToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks().stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .id(book.getId())
                        .name(book.getName())
                        .authors(book.getAuthors().stream()
                                .map(author -> AuthorDto.builder()
                                        .id(author.getId())
                                        .name(author.getName())
                                        .surname(author.getSurname())
                                        .build())
                                .toList())
                        .build())
                .toList();
        return GenreDto.builder()
                .books(bookDtoList)
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
