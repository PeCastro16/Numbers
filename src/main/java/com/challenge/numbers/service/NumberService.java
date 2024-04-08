package com.challenge.numbers.service;

import com.challenge.numbers.domain.dto.NumberDTO;

import java.util.List;

public interface NumberService {
    void save(long num);
    NumberDTO find(long num);
    List<NumberDTO> find();
}
