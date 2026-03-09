package com.pickdate.poll.application;

import com.pickdate.poll.domain.Option;

import java.time.Instant;

import static com.pickdate.bootstrap.domain.Value.valueOrNull;


public record OptionData(
        String optionId,
        Instant startAt,
        Instant endAt,
        boolean wholeDay
) {

    static OptionData from(Option option) {
        return new OptionData(
                valueOrNull(option.getId()),
                option.getStartAt(),
                option.getEndAt(),
                option.isWholeDay()
        );
    }
}
