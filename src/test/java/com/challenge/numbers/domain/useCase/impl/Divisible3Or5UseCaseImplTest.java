package com.challenge.numbers.domain.useCase.impl;

import com.challenge.numbers.domain.enums.NumberCategory;
import com.fasterxml.jackson.core.JsonParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class Divisible3Or5UseCaseImplTest {

    @InjectMocks
    private Divisible3Or5UseCaseImpl divisible3Or5UseCase;

    @Test
    void shouldCategorizeNumberAsType1_whenItsDivisibleBy3() {
        // when
        var category = divisible3Or5UseCase.findNumberCategory(3);

        // then
        Assertions.assertThat(category)
                .as("Should be equal a TYPE_1")
                .isEqualTo(NumberCategory.TYPE_1);
    }

    @Test
    void shouldCategorizeNumberAsType2_whenItsDivisibleBy5() {
        // when
        var category = divisible3Or5UseCase.findNumberCategory(5);

        // then
        Assertions.assertThat(category)
                .as("Should be equal a TYPE_2")
                .isEqualTo(NumberCategory.TYPE_2);
    }

    @Test
    void shouldCategorizeNumberAsType3_whenItsDivisibleBy3And5() {
        // when
        var category = divisible3Or5UseCase.findNumberCategory(15);

        // then
        Assertions.assertThat(category)
                .as("Should be equal a TYPE_3")
                .isEqualTo(NumberCategory.TYPE_3);
    }

    @Test
    void shouldCategorizeNumberAsNotApplicable_whenItsNotDivisibleBy3Or5() {
        // when
        var category = divisible3Or5UseCase.findNumberCategory(13);

        // then
        Assertions.assertThat(category)
                .as("Should be equal a NOT_APPLICABLE")
                .isEqualTo(NumberCategory.NOT_APPLICABLE);
    }
}
