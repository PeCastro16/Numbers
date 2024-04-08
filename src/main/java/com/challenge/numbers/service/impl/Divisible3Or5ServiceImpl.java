package com.challenge.numbers.service.impl;

import com.challenge.numbers.domain.dto.NumberDTO;
import com.challenge.numbers.domain.entity.NumberEntity;
import com.challenge.numbers.domain.mapper.NumberMapper;
import com.challenge.numbers.domain.useCase.DivisibleUseCase;
import com.challenge.numbers.repository.NumberRepository;
import com.challenge.numbers.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Divisible3Or5ServiceImpl implements NumberService {

    private final DivisibleUseCase divisible3Or5UseCaseImpl;
    private final NumberRepository numberRepository;
    private final NumberMapper numberMapper;

    @Override
    public void save(long num) {
        var divType = divisible3Or5UseCaseImpl.findNumberCategory(num);
        numberRepository.save(new NumberEntity(num, divType));
    }

    @Override
    public NumberDTO find(long num) {
        return numberMapper.toDTO(numberRepository.findById(num));
    }

    @Override
    public List<NumberDTO> find() {
        return numberRepository.findAll().stream().map(numberMapper::toDTO).toList();
    }
}
