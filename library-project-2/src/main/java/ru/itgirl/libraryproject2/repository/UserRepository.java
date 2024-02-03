package ru.itgirl.libraryproject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
