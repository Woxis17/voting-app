package com.woxis.votingapp.repository;

import com.woxis.votingapp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findByVotingUserIdAndVotingId(Long userId, Long votingId);

    Optional<Vote> findByIdAndVotingId(Long id, Long votingId);

    boolean existsByVotingUserIdAndVotingId(Long userId, Long votingId);
}
