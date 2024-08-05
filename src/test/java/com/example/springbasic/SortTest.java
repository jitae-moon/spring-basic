package com.example.springbasic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortTest {

    Sort sut;

    @BeforeEach
    void beforeEach() {
        sut = new Sort();
    }

    @Test
    void sort() {
        // Given
        List<String> list = Arrays.asList("a", "bbb", "cc", "ddddd");

        // When & Then
        List<String> actual = sut.sortByLength(list);
        assertThat(actual).isEqualTo(List.of("a", "cc", "bbb", "ddddd"));
    }

}
