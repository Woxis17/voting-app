package com.woxis.votingapp.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class VotingDTO {

  @NotEmpty(message = "eee")
  @NotNull(message = "eee")
  private String subject;
  @NotNull(message = "eee")
  private LocalDate startDate;
  @NotNull(message = "eee")
  private LocalDate endDate;
}
