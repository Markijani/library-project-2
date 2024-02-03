package ru.itgirl.libraryproject2.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.itgirl.libraryproject2.dto.BookCreateDto;
import ru.itgirl.libraryproject2.dto.BookDto;
import ru.itgirl.libraryproject2.dto.BookUpdateDto;
import ru.itgirl.libraryproject2.model.Book;
import ru.itgirl.libraryproject2.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto getByName(String name) {
        log.info("Try to find book by name {}", name);
        Optional<Book> book = bookRepository.findBookByName(name);
        if (book.isPresent()) {
            BookDto bookDto = convertEntityToDto(book.get());
            log.info("Book: {}", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with name {} not found", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto getByNameV2(String name) {
        log.info("Try to find book by name {}", name);
        Optional<Book> book = bookRepository.findBookByNameBySql(name);
        if (book.isPresent()) {
            BookDto bookDto = convertEntityToDto(book.get());
            log.info("Book: {}", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with name {} not found", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto getByNameV3(String name) {
        log.info("Try to find book by name {}", name);
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Optional<Book> book = bookRepository.findOne(specification);
        if (book.isPresent()) {
            BookDto bookDto = convertEntityToDto(book.get());
            log.info("Book: {}", bookDto.toString());
            return bookDto;
        } else {
            log.error("Book with name {} not found", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto createBook(BookCreateDto bookCreateDto) {
        log.info("Try to create book from dto {}", bookCreateDto);
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        log.info("Book saved: {}", book);
        BookDto bookDto = convertEntityToDto(book);
        log.info("Book converted to dto: {}", bookDto);
        return bookDto;
    }

    @Override
    public BookDto updateBook(BookUpdateDto bookUpdateDto) {
        log.info("Try to update book from dto {}", bookUpdateDto);
        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        log.info("Book found: {}", book);
        book.setName(bookUpdateDto.getName());
        book.setGenre(bookUpdateDto.getGenre());
        Book savedBook = bookRepository.save(book);
        log.info("Book updated: {}", savedBook);
        BookDto bookDto = convertEntityToDto(savedBook);
        log.info("Book converted to dto: {}", bookDto);
        return bookDto;
    }

    @Override
    public void deleteBook(Long id) {
        log.info("Try to delete book by id {}", id);
        bookRepository.deleteById(id);
        log.info("Book deleted: {}", id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        log.info("Try to get all books");
        List<Book> books = bookRepository.findAll();
        log.info("Books found: {}", books.size());
        List<BookDto> bookDto = books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        log.info("Books converted to dto: {}", bookDto.size());
        return bookDto;
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(bookCreateDto.getGenre())
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .name(book.getName())
                .build();
    }
}
