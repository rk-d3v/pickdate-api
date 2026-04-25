package com.pickdate.poll.application;

import com.pickdate.poll.domain.*;
import com.pickdate.shared.domain.Identifier;


public interface PollUseCase {

    PollData createPoll(Title title, Description description);

    PollData addOption(Identifier pollId, TimeRange timeRange, boolean wholeDay);

    PollData getPoll(Identifier id);

    void deletePoll(Identifier pollId);

    PollData registerParticipant(Identifier pollId, Participant participant);

    PollData addLocation(Identifier pollId, LocationDetails location);
}
