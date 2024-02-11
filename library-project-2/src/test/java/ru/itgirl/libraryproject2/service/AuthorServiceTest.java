package ru.itgirl.libraryproject2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itgirl.libraryproject2.dto.AuthorCreateDto;
import ru.itgirl.libraryproject2.dto.AuthorDto;
import ru.itgirl.libraryproject2.dto.AuthorUpdateDto;
import ru.itgirl.libraryproject2.model.Author;
import ru.itgirl.libraryproject2.model.Book;
import ru.itgirl.libraryproject2.repository.AuthorRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    public void testGetAuthorById() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorService.getAuthorById(id);
        verify(authorRepository).findById(id);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        Long id = 1L;
        when(authorRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> authorService.getAuthorById(id));
        verify(authorRepository).findById(id);
    }

    @Test
    public void testGetAuthorByNameV1() {
        Long id = 1L;
        String name = "John";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);
        when(authorRepository.findAuthorByName(name)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorService.getByNameV1(name);
        verify(authorRepository).findAuthorByName(name);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testGetAuthorByNameV2() {
        Long id = 2L;
        String name = "Jane";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, name, surname, books);
        when(authorRepository.findAuthorByNameBySql(name)).thenReturn(Optional.of(author));
        AuthorDto authorDto = authorService.getByNameV2(name);
        verify(authorRepository).findAuthorByNameBySql(name);
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testCreateAuthor() {
        Long id = 4L;
        String name = "Alice";
        String surname = "Doe";
        Set<Book> books = new HashSet<>();
        AuthorCreateDto authorCreateDto = new AuthorCreateDto();
        Author author = new Author(id, name, surname, books);
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        AuthorDto authorDto = authorService.createAuthor(authorCreateDto);
        verify(authorRepository).save(any(Author.class));
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
    }

    @Test
    public void testUpdateAuthor() {
        Long id = 5L;
        String oldName = "Alice";
        String oldSurname = "Doe";
        String newName = "Mary";
        String newSurname = "Smith";
        Set<Book> books = new HashSet<>();
        Author author = new Author(id, oldName, oldSurname, books);
        AuthorUpdateDto authorUpdateDto = new AuthorUpdateDto(id, newName, newSurname);
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        AuthorDto authorDto = authorService.updateAuthor(authorUpdateDto);
        verify(authorRepository).findById(id);
        verify(authorRepository).save(any(Author.class));
        Assertions.assertEquals(authorDto.getId(), author.getId());
        Assertions.assertEquals(authorDto.getName(), author.getName());
        Assertions.assertEquals(authorDto.getSurname(), author.getSurname());
        Assertions.assertEquals(authorDto.getName(), newName);
        Assertions.assertEquals(authorDto.getSurname(), newSurname);
    }

    @Test
    public void testGetAllAuthors() {
        Long id1 = 7L;
        String name1 = "Bob";
        String surname1 = "Smith";
        Set<Book> books1 = new HashSet<>();
        Author author1 = new Author(id1, name1, surname1, books1);
        Long id2 = 8L;
        String name2 = "Mary";
        String surname2 = "Jones";
        Set<Book> books2 = new HashSet<>();
        Author author2 = new Author(id2, name2, surname2, books2);
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorRepository.findAll()).thenReturn(authors);
        List<AuthorDto> authorDto = authorService.getAllAuthors();
        verify(authorRepository).findAll();
        Assertions.assertEquals(authorDto.size(), authors.size());
        Assertions.assertEquals(authorDto.get(0).getId(), author1.getId());
        Assertions.assertEquals(authorDto.get(0).getName(), author1.getName());
        Assertions.assertEquals(authorDto.get(0).getSurname(), author1.getSurname());
        Assertions.assertEquals(authorDto.get(1).getId(), author2.getId());
        Assertions.assertEquals(authorDto.get(1).getName(), author2.getName());
        Assertions.assertEquals(authorDto.get(1).getSurname(), author2.getSurname());
    }

    @Test
    public void testDeleteAuthor() {
        Long id = 1L;
        doNothing().when(authorRepository).deleteById(id);
        authorService.deleteAuthor(id);
        verify(authorRepository).deleteById(id);
    }
}