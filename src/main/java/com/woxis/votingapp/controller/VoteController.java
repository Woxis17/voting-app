package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.service.VoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/votings")
@RequiredArgsConstructor
public class VoteController extends BaseExceptionHandlingController {

  private final VoteService voteService;

  @PostMapping("/{voting-id}/votes")
  public ResponseEntity<Long> vote(@RequestBody VoteDTO voteDTO,
                                   @PathVariable(name = "voting-id") Long votingId) {
    log.info("Adding vote {} to voting with id {}", voteDTO, votingId);
    return ok(voteService.vote(voteDTO, votingId));
  }

  @PutMapping("/{voting-id}/votes/{vote-id}")
  public ResponseEntity<Long> changeVote(@RequestBody VoteDTO voteDTO,
                                         @PathVariable(name = "voting-id") Long votingId,
                                         @PathVariable(name = "vote-id") Long voteId) {
    log.info("Changing vote");
    voteService.changeVote(voteDTO, voteId, votingId);
    return ok().build();
  }
}
