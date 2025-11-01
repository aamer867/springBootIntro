package com.thehecklers.sbur_rest_demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CoffeeApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllCoffees() throws Exception {
        mockMvc.perform(get("/coffees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateCoffee() throws Exception {
        Coffee coffee = new Coffee("Test Coffee");
        mockMvc.perform(post("/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coffee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Coffee"));
    }

    @Test
    void testGetCoffeeById() throws Exception {
        // First create a coffee
        Coffee coffee = new Coffee("CoffeeById");
        String content = objectMapper.writeValueAsString(coffee);
        String response = mockMvc.perform(post("/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andReturn().getResponse().getContentAsString();

        Coffee created = objectMapper.readValue(response, Coffee.class);

        // Now GET it
        mockMvc.perform(get("/coffees/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("CoffeeById"));
    }

    @Test
    void testUpdateCoffee() throws Exception {
        Coffee coffee = new Coffee("CoffeeToUpdate");
        String response = mockMvc.perform(post("/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coffee)))
                .andReturn().getResponse().getContentAsString();

        Coffee created = objectMapper.readValue(response, Coffee.class);
        created.setName("UpdatedCoffee");

        mockMvc.perform(put("/coffees/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedCoffee"));
    }

    @Test
    void testDeleteCoffee() throws Exception {
        Coffee coffee = new Coffee("CoffeeToDelete");
        String response = mockMvc.perform(post("/coffees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coffee)))
                .andReturn().getResponse().getContentAsString();

        Coffee created = objectMapper.readValue(response, Coffee.class);

        mockMvc.perform(delete("/coffees/" + created.getId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/coffees/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
