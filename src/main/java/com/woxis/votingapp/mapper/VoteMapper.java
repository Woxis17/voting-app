package com.woxis.votingapp.mapper;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.dto.VoteResponseDTO;
import com.woxis.votingapp.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(source = "id", target = "voteId")
    VoteResponseDTO toDto(Vote vote);
    Vote fromDto(VoteDTO voteDTO);
}
