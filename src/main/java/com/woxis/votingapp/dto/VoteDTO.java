package com.woxis.votingapp.dto;

import com.woxis.votingapp.model.VoteOptionEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

  private VoteOptionEnum voteOption;
}
