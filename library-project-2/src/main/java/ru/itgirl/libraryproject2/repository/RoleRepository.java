package ru.itgirl.libraryproject2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itgirl.libraryproject2.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}

