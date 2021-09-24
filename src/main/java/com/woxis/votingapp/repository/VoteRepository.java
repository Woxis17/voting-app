package com.woxis.votingapp.repository;

import com.woxis.votingapp.model.Vote;
import com.woxis.votingapp.model.Voting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findByVotingUserIdAndVoting(Long userId, Voting voting);

  Optional<Vote> findByIdAndVotingId(Long id, Long votingId);

  boolean existsByVotingUserIdAndVotingId(Long userId, Long votingId);
}
