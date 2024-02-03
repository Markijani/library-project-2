package ru.itgirl.libraryproject2.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String username;

    @Column(nullable = false)
    @Setter
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @Setter
    private Role role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @Setter
    private Set<Role> roles = new HashSet<>();
}
