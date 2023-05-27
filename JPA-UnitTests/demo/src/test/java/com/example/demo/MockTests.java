package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.PetController;
import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
                classes = DemoApplication.class)
public class MockTests {
    @MockBean
    PetRepository repository;

    @InjectMocks
    @Autowired
    PetController controller;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test 
    public void mockAdd() throws UnsupportedEncodingException, Exception{
        log.info("Agregando mascota con mock repository");

        Pet pet = new Pet();
		pet.setName("memo");
		pet.setAge(3);
		pet.setSpecies("perro");
		pet.setTag("123-ABC");

        given(repository.save(any())).willReturn(pet);

		String requestBody = objectMapper.writeValueAsString(pet);
		
		String result = mvc.perform(post("/pets").contentType("application/json").content(requestBody))
		                .andExpect(status().isOk())
						.andExpect(content().contentTypeCompatibleWith("application/json"))
						.andDo(print())
						.andExpect(jsonPath("$.name", is("memo")))
						.andReturn().getResponse().getContentAsString();
		
		log.info("Exito....");
    }
}
