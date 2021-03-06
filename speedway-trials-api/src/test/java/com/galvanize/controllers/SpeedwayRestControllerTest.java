package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Car;
import com.galvanize.entities.Driver;
import com.galvanize.entities.Race;
import com.galvanize.services.SpeedwayService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SpeedwayRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SpeedwayService speedwayService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createDriver() throws Exception {
        Driver driver = new Driver();
        String json = objectMapper.writeValueAsString(driver);
        driver.setId(1L);
        when(speedwayService.createDriver(ArgumentMatchers.any(Driver.class))).thenReturn(driver);
        mockMvc.perform(post("/api/speedway/driver").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driver.getId()));
    }

    @Test
    void findDriver() throws Exception {
        Driver driver = new Driver();
        driver.setId(1L);
        when(speedwayService.findDriverById(1L)).thenReturn(driver);
        mockMvc.perform(get("/api/speedway/driver/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(driver.getId()));
    }

    @Test
    void findAllDrivers() throws Exception {
        List<Driver> drivers = new ArrayList<>();
        Driver driver = new Driver();
        drivers.add(driver);
        when(speedwayService.findAllDrivers()).thenReturn(drivers);
        mockMvc.perform(get("/api/speedway/driver"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void updateDriverById() throws Exception{
        Driver expected = new Driver();
        String json = objectMapper.writeValueAsString(expected);
        expected.setId(1L);

        when(speedwayService.updateDriverById(ArgumentMatchers.any(Driver.class),ArgumentMatchers.anyLong())).thenReturn(expected);

        mockMvc.perform(put("/api/speedway/driver/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()));
    }

    @Test
    void deleteDriverById() throws Exception{
        mockMvc.perform(delete("/api/speedway/driver/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createCar() throws Exception{
        Car car = new Car();
        String json = objectMapper.writeValueAsString(car);
        car.setId(1L);

        when(speedwayService.createCar(ArgumentMatchers.any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/api/speedway/car/").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car.getId()));
    }

    @Test
    void findCar() throws Exception {
        Car car = new Car();
        car.setId(1L);
        when(speedwayService.findCarById(1L)).thenReturn(car);
        mockMvc.perform(get("/api/speedway/car/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(car.getId()));
    }

    @Test
    void findAllCars() throws Exception {
        Car expected = new Car();
        expected.setId(1L);
        List<Car> expectedCars = new ArrayList<>();
        expectedCars.add(expected);
        when(speedwayService.findAllCars()).thenReturn(expectedCars);
        mockMvc.perform(get("/api/speedway/car/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getId()));
    }

    @Test
    void updateCarById() throws Exception{
        Car expected = new Car();
        String json = objectMapper.writeValueAsString(expected);
        expected.setId(1L);
        when(speedwayService.updateCarById(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Car.class))).thenReturn(expected);
        mockMvc.perform(put("/api/speedway/car/1").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()));
    }

    @Test
    void deleteCarById() throws Exception{
        mockMvc.perform(delete("/api/speedway/car/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createRace() throws Exception{
        Race race = new Race();
        String json = objectMapper.writeValueAsString(race);
        race.setId(1L);
        when(speedwayService.createRace(ArgumentMatchers.any(Race.class))).thenReturn(race);
        mockMvc.perform(post("/api/speedway/race").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(race.getId()));
    }

    @Test
    void findRaceById() throws Exception{
        Race expected = new Race();
        expected.setId(1L);
        when(speedwayService.findRaceById(1L)).thenReturn(expected);
        mockMvc.perform(get("/api/speedway/race/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()));
    }

    @Test
    void findAllRaces() throws Exception{
        Race expected = new Race();
        expected.setId(1L);
        List<Race> expectedRaces = new ArrayList<>();
        expectedRaces.add(expected);
        when(speedwayService.findAllRaces()).thenReturn(expectedRaces);
        mockMvc.perform(get("/api/speedway/race"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(expected.getId()));
    }

    @Test
    void updateRaceById() throws Exception{
        Race expected = new Race();
        String json = objectMapper.writeValueAsString(expected);
        expected.setId(1L);
        when(speedwayService.updateRaceById(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Race.class))).thenReturn(expected);
        mockMvc.perform(put("/api/speedway/race/1").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expected.getId()));
    }

    @Test
    void deleteRaceById() throws Exception{
        mockMvc.perform(delete("/api/speedway/race/1"))
                .andExpect(status().isOk());
    }
}