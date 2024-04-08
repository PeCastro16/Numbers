package com.challenge.numbers.repository;

import com.challenge.numbers.domain.entity.NumberEntity;
import com.challenge.numbers.domain.enums.NumberCategory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class NumberRepository {
    private final Map<Long, NumberCategory> repo = new HashMap<>();

    public void save(NumberEntity numberEntity) {
        repo.put(numberEntity.number(), numberEntity.numberCategory());
    }

    public NumberEntity findById(long number) {
        var divType = Optional.ofNullable(repo.get(number)).orElseThrow();
        return new NumberEntity(number, divType);
    }

    public List<NumberEntity> findAll() {
        return repo.entrySet().stream().map(e -> new NumberEntity(e.getKey(), e.getValue())).toList();
    }

    public void deleteById(long number) {
        repo.remove(number);
    }

    public void deleteAll() {
        repo.clear();
    }
}
