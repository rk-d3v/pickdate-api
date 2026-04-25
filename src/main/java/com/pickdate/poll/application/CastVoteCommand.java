package com.pickdate.poll.application;

import com.pickdate.poll.domain.Availability;
import com.pickdate.shared.domain.Identifier;


public record CastVoteCommand(
        Identifier pollId,
        Identifier participantId,
        Identifier OptionId,
        Availability vote
) {
}
