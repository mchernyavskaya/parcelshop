package com.gls.parcelshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gls.parcelshop.controller.ParcelController;
import com.gls.parcelshop.model.DeliveryState;
import com.gls.parcelshop.model.Parcel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParcelshopApplicationTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @LocalServerPort
    private int port;

    @Autowired
    private ParcelController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }


    @Test
    public void getAllParcels() throws JsonProcessingException {
        String url = constructUrl("parcels");
        String response = this.restTemplate.getForObject(url, String.class);
        List<Parcel> result = MAPPER.readValue(response, new TypeReference<>() {
        });
        logResponse(url, response);

        assertThat(response).isNotBlank();
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    public void getParcelsByDate() throws JsonProcessingException {
        String url = constructUrl("parcels?deliveryDate=20200421");
        String response = this.restTemplate.getForObject(url, String.class);
        List<Parcel> result = MAPPER.readValue(response, new TypeReference<>() {
        });
        logResponse(url, response);

        assertThat(response).isNotBlank();
        assertThat(result.size()).isEqualTo(3);
        result.forEach(parcel -> {
            assertEquals(DeliveryState.OUT_FOR_DELIVERY, parcel.getDeliveryState());
        });
    }

    private String constructUrl(String endpoint) {
        return "http://localhost:" + port + "/api/v1/" + endpoint;
    }

    private void logResponse(String url, String response) {
        System.out.println("GET " + url);
        System.out.println("-------");
        System.out.println(response);
        System.out.println("-------");
    }
}