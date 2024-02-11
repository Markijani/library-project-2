package ru.itgirl.libraryproject2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itgirl.libraryproject2.dto.GenreDto;
import ru.itgirl.libraryproject2.model.Book;
import ru.itgirl.libraryproject2.model.Genre;
import ru.itgirl.libraryproject2.repository.GenreRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GenreServiceTest {
    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;

    @Test
    public void testGetGenreById() {
        Long id = 2L;
        String name = "novel";
        Set<Book> books = new HashSet<>();
        Genre genre = new Genre(id, name, books);
        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));
        GenreDto genreDto = genreService.getGenreById(id);
        verify(genreRepository).findById(id);
        Assertions.assertEquals(genreDto.getId(), genre.getId());
        Assertions.assertEquals(genreDto.getName(), genre.getName());
    }
}
