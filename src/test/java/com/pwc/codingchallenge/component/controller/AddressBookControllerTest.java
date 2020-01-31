package com.pwc.codingchallenge.component.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwc.codingchallenge.component.ComponentTest;
import com.pwc.codingchallenge.repository.AddressBookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentTest
@AutoConfigureMockMvc
public class AddressBookControllerTest {

    @Autowired
    private MockMvc mvc;

    private static final String BASE_PATH = "src/test/resources/request/";

    @Autowired
    private AddressBookRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void saveAndGetAddressBook_withValidValues_returnsSuccess() throws Exception {

        saveDataInDb();
        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(get("/address").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name", is("mike")))
                .andExpect(jsonPath("$[0].phoneNumber", is("0411223344")))
                .andExpect(jsonPath("$[1].name", is("rachel")))
                .andExpect(jsonPath("$[1].phoneNumber", is("0411223346")))
                .andExpect(jsonPath("$[2].name", is("smith")))
                .andExpect(jsonPath("$[2].phoneNumber", is("0411223345")));
    }

    @Test
    public void findUniqueFriends_withValidValues_returnsSuccess() throws Exception {

        saveDataInDb();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mapper.readValue(new File(BASE_PATH + "validFindUniqueFriendsRequest.json"), Object.class));

        mvc.perform(post("/address/unique")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0]", is("bruce")))
                .andExpect(jsonPath("$[1]", is("mike")))
                .andExpect(jsonPath("$[2]", is("rachel")))
                .andExpect(jsonPath("$[3]", is("smith")))
                .andExpect(jsonPath("$[4]", is("steve")));

    }

    @Test
    public void deleteAll_withValidValues_returnsSuccess() throws Exception {

        saveDataInDb();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mapper.readValue(new File(BASE_PATH + "validFindUniqueFriendsRequest.json"), Object.class));

        mvc.perform(delete("/address")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/address").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].name").doesNotExist());

    }

    private void saveDataInDb() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(mapper.readValue(new File(BASE_PATH + "validInputSaveRequest.json"), Object.class));

        mvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().is2xxSuccessful());

        jsonString = mapper.writeValueAsString(mapper.readValue(new File(BASE_PATH + "validInputSaveRequest2.json"), Object.class));

        mvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().is2xxSuccessful());

        jsonString = mapper.writeValueAsString(mapper.readValue(new File(BASE_PATH + "validInputSaveRequest3.json"), Object.class));

        mvc.perform(post("/address")
                .contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().is2xxSuccessful());
    }
}
