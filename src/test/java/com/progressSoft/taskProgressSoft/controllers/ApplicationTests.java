package com.progressSoft.taskProgressSoft.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.progressSoft.taskProgressSoft.TaskProgressSoftApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = TaskProgressSoftApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false, webClientEnabled = false)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ApplicationTests {

    private static ObjectMapper mapper;

    @Autowired
    public MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return geMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper geMapper() {
        try {
            if (mapper == null) {
                mapper = new ObjectMapper();
                JavaTimeModule javaTimeModule = new JavaTimeModule();
                javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
                mapper.registerModule(javaTimeModule);
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            }
            return mapper;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

}
