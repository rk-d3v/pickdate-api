package com.pickdate.poll.application;

import com.pickdate.shared.domain.Identifier;

import java.util.List;


public interface VoteUseCase {

    void castVote(CastVoteCommand castVoteCommand);

    List<VoteData> getVotesBy(Identifier pollId);
}
