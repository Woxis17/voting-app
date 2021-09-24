package com.woxis.votingapp.mapper;

import com.woxis.votingapp.config.MapStructConfig;
import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.dto.VoteResponseDTO;
import com.woxis.votingapp.model.Vote;

import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface VoteMapper {

  VoteResponseDTO toDto(Vote vote);

  Vote fromDto(VoteDTO voteDTO);
}
