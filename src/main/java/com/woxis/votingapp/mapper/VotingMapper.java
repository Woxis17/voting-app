package com.woxis.votingapp.mapper;

import com.woxis.votingapp.config.MapStructConfig;
import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.dto.VotingResponseDTO;
import com.woxis.votingapp.model.Voting;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface VotingMapper {

  @Mapping(source = "creator.username", target = "creator")
  VotingResponseDTO toDto(Voting voting);

  Voting fromDto(VotingDTO votingDTO);

}
