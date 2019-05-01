package com.woxis.votingapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class VotingResponseDTO {

    private Long votingId;
    private String creator;
    private String subject;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean ended;
    private VoteResponseDTO userVote;
    // todo add scores
}
