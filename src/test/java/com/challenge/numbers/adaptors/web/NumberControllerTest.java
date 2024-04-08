package com.challenge.numbers.adaptors.web;

import com.challenge.numbers.adaptor.web.dto.ErrorResponse;
import com.challenge.numbers.adaptor.web.dto.NumberRequest;
import com.challenge.numbers.adaptor.web.dto.NumberResponse;
import com.challenge.numbers.domain.entity.NumberEntity;
import com.challenge.numbers.domain.enums.NumberCategory;
import com.challenge.numbers.repository.NumberRepository;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NumberControllerTest {
    @LocalServerPort int port;
    @Autowired TestRestTemplate testRestTemplate;
    @Autowired NumberRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveAndCategorizeNumber() {
        // when
        var response = testRestTemplate.postForEntity("/numbers", new NumberRequest(3), NumberResponse.class);

        // then
        Assertions.assertThat(response.getStatusCode())
                .as("Should return 201 - Created")
                .isEqualTo(HttpStatus.CREATED);
        Awaitility.await("Find number 3 with the right category").until(() -> repository.findById(3).numberCategory() == NumberCategory.TYPE_1);
    }

    @Test
    void shouldReturn404_whenNumberDontExistsOnDB() {
        // when
        var response = testRestTemplate.getForEntity("/numbers/4", ErrorResponse.class);

        // then
        Assertions.assertThat(response.getStatusCode())
                .as("Should return 404 - Not Found")
                .isEqualTo(HttpStatus.NOT_FOUND);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).message())
                .as("Should have an specific error message")
                .isEqualTo("Number not found!");
    }

    @Test
    void shouldReturn200WithTheNumberOnBody_whenNumberExistsOnDB() {
        // given
        repository.save(new NumberEntity(5, NumberCategory.TYPE_2));

        // when
        var response = testRestTemplate.getForEntity("/numbers/5", NumberResponse.class);

        // then
        Assertions.assertThat(response.getStatusCode())
                .as("Should return 200 - Ok")
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).number())
                .as("Should return the right number")
                .isEqualTo(5);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).category())
                .as("Should return the right category")
                .isEqualTo("Type 2");
    }

    @Test
    void shouldReturn200WithAllTheNumbers_whenExistsOnDB() {
        // given
        repository.save(new NumberEntity(3, NumberCategory.TYPE_1));

        // when
        var response = testRestTemplate.getForEntity("/numbers", NumberResponse[].class);

        // then
        Assertions.assertThat(response.getStatusCode())
                .as("Should return 200 - Ok")
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).length)
                .as("Should have only 1 item")
                .isEqualTo(1);

        Assertions.assertThat(Objects.requireNonNull(response.getBody())[0].number())
                .as("Item 1 should have number 3")
                .isEqualTo(3);

        Assertions.assertThat(Objects.requireNonNull(response.getBody())[0].category())
                .as("Item 1 should have category Type 1")
                .isEqualTo(NumberCategory.TYPE_1.getDescription());
    }

    @Test
    void shouldReturn200WithAnEmptyList_whenDBIsEmpty() {
        // when
        var response = testRestTemplate.getForEntity("/numbers", NumberResponse[].class);

        // then
        Assertions.assertThat(response.getStatusCode())
                .as("Should return 200 - Ok")
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(Objects.requireNonNull(response.getBody()).length)
                .as("Should have only 1 item")
                .isEqualTo(0);
    }

}
