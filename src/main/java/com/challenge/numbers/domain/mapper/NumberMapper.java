package com.challenge.numbers.domain.mapper;

import com.challenge.numbers.adaptor.web.dto.NumberResponse;
import com.challenge.numbers.domain.dto.NumberDTO;
import com.challenge.numbers.domain.entity.NumberEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NumberMapper {
    public NumberDTO toDTO(NumberEntity entity) {
        return new NumberDTO(entity.number(), entity.numberCategory());
    }

    public NumberResponse toResponse(NumberDTO numberDTO) {
        return new NumberResponse(numberDTO.num(), numberDTO.category().getDescription());
    }

    public List<NumberResponse> toResponse(List<NumberDTO> numbersDTO) {
        return numbersDTO.stream().map(this::toResponse).toList();
    }
}
