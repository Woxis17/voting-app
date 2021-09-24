package com.woxis.votingapp.mapper;

import com.woxis.votingapp.config.MapStructConfig;
import com.woxis.votingapp.dto.UserDTO;
import com.woxis.votingapp.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {

  @Mapping(target = "password", ignore = true)
  User fromDto(UserDTO userDTO);

}
