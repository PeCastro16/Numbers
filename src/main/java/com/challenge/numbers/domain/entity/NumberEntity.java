package com.challenge.numbers.domain.entity;


import com.challenge.numbers.domain.enums.NumberCategory;

public record NumberEntity(long number, NumberCategory numberCategory) {}
