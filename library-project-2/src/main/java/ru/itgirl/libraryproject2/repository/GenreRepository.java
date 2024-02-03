package ru.itgirl.libraryproject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject2.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
