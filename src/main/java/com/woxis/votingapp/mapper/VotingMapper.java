package com.woxis.votingapp.mapper;

import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.dto.VotingResponseDTO;
import com.woxis.votingapp.model.Voting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VotingMapper {

    VotingMapper INSTANCE = Mappers.getMapper(VotingMapper.class);

    @Mapping(source = "creator.username", target = "creator")
    @Mapping(source = "id", target = "votingId")
    VotingResponseDTO toDto(Voting voting);

    Voting fromDto(VotingDTO votingDTO);

}
