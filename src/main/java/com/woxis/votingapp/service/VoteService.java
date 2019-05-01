package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.exception.ConflictException;
import com.woxis.votingapp.exception.NotFoundException;
import com.woxis.votingapp.model.User;
import com.woxis.votingapp.model.Vote;
import com.woxis.votingapp.model.Voting;
import com.woxis.votingapp.repository.UserRepository;
import com.woxis.votingapp.repository.VoteRepository;
import com.woxis.votingapp.repository.VotingRepository;
import com.woxis.votingapp.util.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final VotingRepository votingRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       VotingRepository votingRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.votingRepository = votingRepository;
    }

    public Long vote(VoteDTO voteDTO, Long votingId) {
        Vote vote = new Vote(voteDTO.getVoteOption());

        User user = userRepository.findById(UserId.get()).orElseThrow(NotFoundException::new);
        user.addVote(vote);

        Voting voting = votingRepository.findById(votingId).orElseThrow(NotFoundException::new);
        voting.addVote(vote);

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
            throw new ConflictException();
        }
    }

    public Optional<Vote> getUserVote(Long userId, Long votingId) {
        return voteRepository.findByVotingUserIdAndVotingId(userId, votingId);
    }
}
