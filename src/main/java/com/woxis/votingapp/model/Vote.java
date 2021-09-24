package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "VOTING_USER_ID")
  private User votingUser;
  @ManyToOne
  @JoinColumn(name = "VOTING_ID")
  private Voting voting;

  @Enumerated
  private VoteOptionEnum voteOption;
  private boolean changed;

  public Vote(VoteOptionEnum voteOption) {
    this.voteOption = voteOption;
  }

}
