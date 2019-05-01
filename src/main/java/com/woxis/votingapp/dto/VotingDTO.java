package com.woxis.votingapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VotingDTO {

    private String subject;
    private LocalDate startDate;
    private LocalDate endDate;
}
