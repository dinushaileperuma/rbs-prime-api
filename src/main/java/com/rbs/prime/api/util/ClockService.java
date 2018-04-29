package com.rbs.prime.api.util;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class ClockService {

    private final Clock clock;

    public ClockService(final Clock clock) {
        this.clock = clock;
    }

    public LocalDateTime currentTime() {
        return LocalDateTime.now(clock);
    }

}
