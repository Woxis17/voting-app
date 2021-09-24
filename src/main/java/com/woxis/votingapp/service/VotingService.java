package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.dto.VotingResponseDTO;
import com.woxis.votingapp.exception.NotFoundException;
import com.woxis.votingapp.mapper.VoteMapper;
import com.woxis.votingapp.mapper.VotingMapper;
import com.woxis.votingapp.model.User;
import com.woxis.votingapp.model.Voting;
import com.woxis.votingapp.repository.UserRepository;
import com.woxis.votingapp.repository.VotingRepository;
import com.woxis.votingapp.util.UserId;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VotingService {

  private final VotingRepository votingRepository;
  private final UserRepository userRepository;
  private final VotingValidator votingValidator;
  private final VoteService voteService;
  private final VotingMapper votingMapper;
  private final VoteMapper voteMapper;

  public Long createVoting(VotingDTO votingDTO) {
    votingValidator.validate(votingDTO);
    Voting voting = votingMapper.fromDto(votingDTO);
    User user = userRepository.findById(UserId.get()).orElseThrow(NotFoundException::new);
    user.addVoting(voting);
    votingRepository.save(voting);
    return voting.getId();
  }

  public Page<VotingResponseDTO> getVotingList(Pageable pageable) {
    Page<Voting> votingList = votingRepository.findAll(pageable);
    return votingList.map(this::mapWithDetails);
  }

  private VotingResponseDTO mapWithDetails(Voting voting) {
    VotingResponseDTO votingResponseDTO = votingMapper.toDto(voting);
    voteService.getUserVote(UserId.get(), voting.getId())
        .ifPresent(vote -> votingResponseDTO.setUserVote(voteMapper.toDto(vote)));

    votingResponseDTO.setScore(voting.calculateScore());
    return votingResponseDTO;
  }
}
