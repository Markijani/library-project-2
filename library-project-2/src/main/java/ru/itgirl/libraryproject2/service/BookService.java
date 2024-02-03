package ru.itgirl.libraryproject2.service;

import ru.itgirl.libraryproject2.dto.BookCreateDto;
import ru.itgirl.libraryproject2.dto.BookDto;
import ru.itgirl.libraryproject2.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto getByName(String name);
    BookDto getByNameV2(String name);
    BookDto getByNameV3(String name);
    BookDto createBook(BookCreateDto bookCreateDto);
    BookDto updateBook(BookUpdateDto bookUpdateDto);
    void deleteBook(Long id);
    List<BookDto> getAllBooks();
}
