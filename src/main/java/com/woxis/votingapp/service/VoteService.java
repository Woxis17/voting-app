package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.exception.AlreadyPerformedException;
import com.woxis.votingapp.exception.NotFoundException;
import com.woxis.votingapp.model.User;
import com.woxis.votingapp.model.Vote;
import com.woxis.votingapp.model.Voting;
import com.woxis.votingapp.repository.UserRepository;
import com.woxis.votingapp.repository.VoteRepository;
import com.woxis.votingapp.repository.VotingRepository;
import com.woxis.votingapp.util.UserId;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteService {

  private final VoteRepository voteRepository;
  private final UserRepository userRepository;
  private final VotingRepository votingRepository;

  public Long vote(VoteDTO voteDTO, Long votingId) {
    Vote vote = new Vote(voteDTO.getVoteOption());

    if (voteRepository.existsByVotingUserIdAndVotingId(UserId.get(), votingId)) {
      throw new AlreadyPerformedException("already voted");
    }

    Voting voting = votingRepository.findById(votingId).orElseThrow(NotFoundException::new);
    if (!voting.isActive()) {
      throw new IllegalStateException("Voting is not active anymore");
    }
    voting.addVote(vote);

    User user = userRepository.findById(UserId.get()).orElseThrow(NotFoundException::new);
    vote.setVotingUser(user);

    voteRepository.save(vote);
    return vote.getId();
  }

  public void changeVote(VoteDTO voteDTO, Long voteId, Long votingId) {
    Vote vote = voteRepository.findByIdAndVotingId(voteId, votingId).orElseThrow(NotFoundException::new);
    if (!vote.isChanged() && !vote.getVoteOption().equals(voteDTO.getVoteOption())) {
      vote.setVoteOption(voteDTO.getVoteOption());
      vote.setChanged(true);
      voteRepository.save(vote);
    } else {
      if (vote.isChanged()) {
        throw new AlreadyPerformedException();
      } else {
        throw new IllegalStateException();
      }
    }
  }

  public Optional<Vote> getUserVote(Long userId, Voting voting) {
    return voteRepository.findByVotingUserIdAndVoting(userId, voting);
  }
}
