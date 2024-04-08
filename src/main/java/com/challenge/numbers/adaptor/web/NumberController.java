package com.challenge.numbers.adaptor.web;

import com.challenge.numbers.adaptor.web.dto.NumberRequest;
import com.challenge.numbers.adaptor.web.dto.NumberResponse;
import com.challenge.numbers.domain.mapper.NumberMapper;
import com.challenge.numbers.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/numbers")
public class NumberController {

    private final NumberService divisible3Or5ServiceImpl;
    private final NumberMapper numberMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody NumberRequest numberRequest) {
        divisible3Or5ServiceImpl.save(numberRequest.number());
    }

    @GetMapping("/{number}")
    public NumberResponse find(@PathVariable("number") long number) {
        return numberMapper.toResponse(divisible3Or5ServiceImpl.find(number));
    }

    @GetMapping
    public List<NumberResponse> find() {
        return numberMapper.toResponse(divisible3Or5ServiceImpl.find());
    }
}
