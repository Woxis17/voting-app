package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.CreatedId;
import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.dto.VotingResponseDTO;
import com.woxis.votingapp.service.VotingService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/votings")
@RequiredArgsConstructor
public class VotingController extends BaseExceptionHandlingController {

  private final VotingService votingService;

  @PostMapping
  @ResponseBody
  public CreatedId createVoting(@RequestBody @Valid VotingDTO votingDTO) {
    return CreatedId.of(votingService.createVoting(votingDTO));
  }

  @GetMapping
  public Page<VotingResponseDTO> getVotingList(@PageableDefault(size = 25) Pageable page) {
    return votingService.getVotingList(page);
  }
}
