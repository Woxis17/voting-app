package com.woxis.votingapp.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VotingDTO {

  private String subject;
  private LocalDate startDate;
  private LocalDate endDate;
}
