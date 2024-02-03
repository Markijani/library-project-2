package ru.itgirl.libraryproject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itgirl.libraryproject2.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    Author findAuthorByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM AUTHOR WHERE name = ?")
    Optional<Author> findAuthorByNameBySql(String name);

}
