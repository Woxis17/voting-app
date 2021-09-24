package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vote extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, updatable = false)
  private User votingUser;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, updatable = false)
  private Voting voting;

  @Enumerated
  private VoteOptionEnum voteOption;
  @Column
  private boolean changed;

  public Vote(VoteOptionEnum voteOption) {
    this.voteOption = voteOption;
  }

}
