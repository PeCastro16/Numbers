package com.challenge.numbers.domain.useCase;

import com.challenge.numbers.domain.enums.NumberCategory;

public interface DivisibleUseCase {
    NumberCategory findNumberCategory(long num);
}
