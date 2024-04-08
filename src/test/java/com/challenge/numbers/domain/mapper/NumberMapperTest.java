package com.challenge.numbers.domain.mapper;

import com.challenge.numbers.adaptor.web.dto.NumberResponse;
import com.challenge.numbers.domain.dto.NumberDTO;
import com.challenge.numbers.domain.entity.NumberEntity;
import com.challenge.numbers.domain.enums.NumberCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class NumberMapperTest {

    @InjectMocks
    private NumberMapper numberMapper;

    @Test
    void shouldParseFromNumberEntityToNumberDTO() {
        // given
        var expected = new NumberDTO(3, NumberCategory.TYPE_1);
        var entity = new NumberEntity(3, NumberCategory.TYPE_1);

        // when
        var actual = numberMapper.toDTO(entity);

        // then
        Assertions.assertThat(actual)
                .as("Should return dto with number 3 and category TYPE_1")
                .isEqualTo(expected);
    }

    @Test
    void shouldParseFromNumberDTOToNumberResponse() {
        // given
        var expected = new NumberResponse(3, NumberCategory.TYPE_1.getDescription());
        var dto = new NumberDTO(3, NumberCategory.TYPE_1);

        // when
        var actual = numberMapper.toResponse(dto);

        // then
        Assertions.assertThat(actual)
                .as("Should return response with number 3 and category as \"Type 1\"")
                .isEqualTo(expected);
    }

    @Test
    void shouldParseFromListOfNumberDTOToListOfNumberResponse() {
        // given
        var expected = List.of(
                new NumberResponse(3, NumberCategory.TYPE_1.getDescription()),
                new NumberResponse(5, NumberCategory.TYPE_2.getDescription())
        );
        var dtos = List.of(
                new NumberDTO(3, NumberCategory.TYPE_1),
                new NumberDTO(5, NumberCategory.TYPE_2)
        );

        // when
        var actual = numberMapper.toResponse(dtos);

        // then
        Assertions.assertThat(actual)
                .as("Should return a list with 2 elements number 3 category Type 1 and number 5 category Type 2")
                .isEqualTo(expected);
    }
}
