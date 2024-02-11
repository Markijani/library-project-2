package ru.itgirl.libraryproject2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itgirl.libraryproject2.dto.GenreDto;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class GenreRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getGenreByIdTest() throws Exception {
        Long genreId = 1L;
        GenreDto genreDto = new GenreDto();
        genreDto.setName("роман");
        mockMvc.perform(MockMvcRequestBuilders.get("/genre/{id}", genreId))
                .andExpect(status().isOk());
    }
}
