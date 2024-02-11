package ru.itgirl.libraryproject2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itgirl.libraryproject2.dto.BookCreateDto;
import ru.itgirl.libraryproject2.dto.BookDto;
import ru.itgirl.libraryproject2.dto.BookUpdateDto;
import ru.itgirl.libraryproject2.model.Author;
import ru.itgirl.libraryproject2.model.Book;
import ru.itgirl.libraryproject2.model.Genre;
import ru.itgirl.libraryproject2.repository.BookRepository;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void testGetBookByName() {
        Long id = 2L;
        String name = "Day";
        Genre genre = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findBookByName(name)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getByName(name);
        verify(bookRepository).findBookByName(name);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testGetBookByNameV2() {
        Long id = 2L;
        String name = "Night";
        Genre genre = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.findBookByNameBySql(name)).thenReturn(Optional.of(book));
        BookDto bookDto = bookService.getByNameV2(name);
        verify(bookRepository).findBookByNameBySql(name);
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testCreateBook() {
        Long id = 4L;
        String name = "Alice";
        Genre genre = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors = new HashSet<>();
        BookCreateDto bookCreateDto = new BookCreateDto();
        Book book = new Book(id, name, genre, authors);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        BookDto bookDto = bookService.createBook(bookCreateDto);
        verify(bookRepository).save(any(Book.class));
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getGenre(), book.getGenre().getName());
    }

    @Test
    public void testUpdateAuthor() {
        Long id = 5L;
        String oldName = "Alice";
        String newName = "Mary";
        Genre genre = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors = new HashSet<>();
        Book book = new Book(id, oldName, genre, authors);
        BookUpdateDto bookUpdateDto = new BookUpdateDto(id, newName, genre);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        BookDto bookDto = bookService.updateBook(bookUpdateDto);
        verify(bookRepository).findById(id);
        verify(bookRepository).save(any(Book.class));
        Assertions.assertEquals(bookDto.getId(), book.getId());
        Assertions.assertEquals(bookDto.getName(), book.getName());
        Assertions.assertEquals(bookDto.getName(), newName);
    }

    @Test
    public void testGetAllBooks() {
        Long id1 = 7L;
        String name1 = "Bob";
        Genre genre1 = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors1 = new HashSet<>();
        Book book1 = new Book(id1, name1, genre1, authors1);
        Long id2 = 8L;
        String name2 = "Mary";
        Genre genre2 = new Genre(2L, "novel", new HashSet<>());
        Set<Author> authors2 = new HashSet<>();
        Book book2 = new Book(id2, name2, genre2, authors2);
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);
        List<BookDto> bookDto = bookService.getAllBooks();
        verify(bookRepository).findAll();
        Assertions.assertEquals(bookDto.size(), books.size());
        Assertions.assertEquals(bookDto.get(0).getId(), book1.getId());
        Assertions.assertEquals(bookDto.get(0).getName(), book1.getName());
        Assertions.assertEquals(bookDto.get(0).getGenre(), book1.getGenre().getName());
        Assertions.assertEquals(bookDto.get(1).getId(), book2.getId());
        Assertions.assertEquals(bookDto.get(1).getName(), book2.getName());
        Assertions.assertEquals(bookDto.get(1).getGenre(), book2.getGenre().getName());
    }

    @Test
    public void testDeleteBook() {
        Long id = 1L;
        doNothing().when(bookRepository).deleteById(id);
        bookService.deleteBook(id);
        verify(bookRepository).deleteById(id);
    }
}
