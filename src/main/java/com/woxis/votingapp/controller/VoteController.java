package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/votings/{voting-id}/votes")
    public ResponseEntity<Long> vote(@RequestBody VoteDTO voteDTO,
                                     @PathVariable(name = "voting-id") Long votingId) {
        log.info("Adding vote {} to voting with id {}", voteDTO, votingId);
        return ok(voteService.vote(voteDTO, votingId));
    }

    @PutMapping("/votings/{voting-id}/votes/{vote-id}")
    public ResponseEntity<Long> changeVote(@RequestBody VoteDTO voteDTO,
                                           @PathVariable(name = "voting-id") Long votingId,
                                           @PathVariable(name = "vote-id") Long voteId) {
        log.info("Changing vote");
        voteService.changeVote(voteDTO, voteId, votingId);
        return ok().build();
    }
}
