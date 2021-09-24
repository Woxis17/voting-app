package com.woxis.votingapp.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatedId {

  private Long id;

  public static CreatedId of(Long id) {
    return new CreatedId(id);
  }

}
