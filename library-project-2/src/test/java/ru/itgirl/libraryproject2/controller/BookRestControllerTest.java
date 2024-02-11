package ru.itgirl.libraryproject2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.itgirl.libraryproject2.dto.*;
import ru.itgirl.libraryproject2.model.Genre;
import java.util.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBookByName() throws Exception {
        String name = "Нос";
        BookDto bookDto = new BookDto();
        bookDto.setId(3L);
        bookDto.setName(name);
        bookDto.setGenre("Рассказ");
        mockMvc.perform(MockMvcRequestBuilders.get("/book").param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
    }

    @Test
    public void testGetBookByNameV2() throws Exception {
        String name = "Нос";
        BookDto bookDto = new BookDto();
        bookDto.setId(3L);
        bookDto.setName(name);
        bookDto.setGenre("Рассказ");
        mockMvc.perform(MockMvcRequestBuilders.get("/book/v2").param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
    }

    @Test
    public void testGetBookByNameV3() throws Exception {
        String name = "Нос";
        BookDto bookDto = new BookDto();
        bookDto.setId(3L);
        bookDto.setName(name);
        bookDto.setGenre("Рассказ");
        mockMvc.perform(MockMvcRequestBuilders.get("/book/v3").param("name", name))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value(bookDto.getGenre()));
    }

    @Test
    public void testCreateBook() throws Exception {
        BookCreateDto bookCreateDto = new BookCreateDto();
        bookCreateDto.setName("Тень");
        bookCreateDto.setGenre(new Genre(1L, "роман", new HashSet<>()));
        String jsonContent = new ObjectMapper().writeValueAsString(bookCreateDto);
        System.out.println("Serialized JSON content: " + jsonContent);
        mockMvc.perform(MockMvcRequestBuilders.post("/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookCreateDto.getName()));
    }

    @Test
    public void testUpdateBook() throws Exception {
        BookUpdateDto bookUpdateDto = new BookUpdateDto();
        bookUpdateDto.setId(8L);
        bookUpdateDto.setName("Дочь");
        bookUpdateDto.setGenre(new Genre(1L, "роман", new HashSet<>()));
        String jsonContent = new ObjectMapper().writeValueAsString(bookUpdateDto);
        System.out.println("Serialized JSON content: " + jsonContent);
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(bookUpdateDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(bookUpdateDto.getName()));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/book/delete/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}