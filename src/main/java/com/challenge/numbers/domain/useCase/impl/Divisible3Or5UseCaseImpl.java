package com.challenge.numbers.domain.useCase.impl;

import com.challenge.numbers.domain.enums.NumberCategory;
import com.challenge.numbers.domain.useCase.DivisibleUseCase;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Divisible3Or5UseCaseImpl implements DivisibleUseCase {

    private static final Map<DivResult, NumberCategory> DIV_RESULT_NUMBER_TYPE_MAP =
            Map.of(
                    new DivResult(false, false), NumberCategory.NOT_APPLICABLE,
                    new DivResult(true, false), NumberCategory.TYPE_1,
                    new DivResult(false, true), NumberCategory.TYPE_2,
                    new DivResult(true, true), NumberCategory.TYPE_3
            );

    @Override
    public NumberCategory findNumberCategory(long num) {
        return DIV_RESULT_NUMBER_TYPE_MAP.get(new DivResult(num % 3 == 0, num % 5 == 0));
    }

    record DivResult(boolean div3, boolean div5){}
}
