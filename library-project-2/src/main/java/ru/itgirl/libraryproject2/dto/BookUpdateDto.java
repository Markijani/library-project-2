package ru.itgirl.libraryproject2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itgirl.libraryproject2.model.Genre;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookUpdateDto {
    private Long id;
    @Size(min = 3, max = 10)
    @NotBlank(message = "name required")
    private String name;
    private Genre genre;
}
