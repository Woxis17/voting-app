package com.woxis.votingapp.model;

import com.woxis.votingapp.dto.ScoreDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.woxis.votingapp.model.VoteOptionEnum.AGAINST;
import static com.woxis.votingapp.model.VoteOptionEnum.FAVOR;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voting extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private User creator;
  private String subject;
  private LocalDate startDate;
  private LocalDate endDate;
  @OneToMany(mappedBy = "voting")
  @ToString.Exclude
  private List<Vote> votes = new ArrayList<>();

  public void addVote(Vote vote) {
    vote.setVoting(this);
    votes.add(vote);
  }

  public boolean isActive() {
    LocalDate now = LocalDate.now();
    return (now.isAfter(startDate) || now.isEqual(startDate)) && (now.isBefore(endDate) || now.isEqual(endDate));
  }

  public boolean isEnded() {
    return LocalDate.now().isAfter(endDate);
  }

  public ScoreDTO calculateScore() {
    Map<VoteOptionEnum, Long> scores = votes.stream().collect(Collectors.groupingBy(Vote::getVoteOption, Collectors.counting()));
    return new ScoreDTO(scores.getOrDefault(FAVOR, 0L), scores.getOrDefault(AGAINST, 0L));
  }

}
