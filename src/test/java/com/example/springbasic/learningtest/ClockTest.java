package com.example.springbasic.learningtest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @DisplayName("Clock을 사용해서 LocalDateTime.now() 가져오기")
    @Test
    void defaultClock() throws InterruptedException {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        TimeUnit.SECONDS.sleep(1);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        assertThat(dt1).isBefore(dt2);
    }

    @DisplayName("Fixed clock사용해서 LocalDateTime.now()를 고정시키기")
    @Test
    void fixedClock() throws InterruptedException {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dt1 = LocalDateTime.now(clock);
        TimeUnit.SECONDS.sleep(3);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        LocalDateTime thirtyMinutesAfter = LocalDateTime.now(clock).plusMinutes(30);

        assertThat(dt1).isEqualTo(dt2);
        assertThat(thirtyMinutesAfter).isAfter(dt1);
    }

}
