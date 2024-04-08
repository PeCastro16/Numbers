package com.challenge.numbers.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NumberCategory {
    NOT_APPLICABLE("Not applicable"),
    TYPE_1("Type 1"),
    TYPE_2("Type 2"),
    TYPE_3("Type 3");

    private final String description;

}
