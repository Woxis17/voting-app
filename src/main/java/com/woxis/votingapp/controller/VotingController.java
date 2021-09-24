package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.dto.VotingResponseDTO;
import com.woxis.votingapp.service.VotingService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
public class VotingController extends BaseExceptionHandlingController {

  private final VotingService votingService;

  @PostMapping("/votings")
  public ResponseEntity<Long> createVoting(@RequestBody VotingDTO votingDTO) {
    return ok(votingService.createVoting(votingDTO));
  }

  @GetMapping("/votings")
  public ResponseEntity<List<VotingResponseDTO>> getVotingList() {
    return ok(votingService.getVotingList());
  }
}
