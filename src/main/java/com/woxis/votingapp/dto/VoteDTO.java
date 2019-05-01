package com.woxis.votingapp.dto;

import com.woxis.votingapp.model.VoteOptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {

    @NotNull
    private VoteOptionEnum voteOption;
}
