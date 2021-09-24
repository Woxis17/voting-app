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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

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

  public List<VotingResponseDTO> getVotingList() {
    List<Voting> votingList = votingRepository.findAll();
    return votingList.stream().map(this::mapWithDetails).collect(toList());
  }

  private VotingResponseDTO mapWithDetails(Voting voting) {
    VotingResponseDTO votingResponseDTO = votingMapper.toDto(voting);
    voteService.getUserVote(UserId.get(), voting.getId())
        .ifPresent(vote -> votingResponseDTO.setUserVote(voteMapper.toDto(vote)));

    votingResponseDTO.setScore(voting.calculateScore());
    return votingResponseDTO;
  }
}
