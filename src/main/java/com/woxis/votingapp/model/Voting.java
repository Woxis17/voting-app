package com.woxis.votingapp.model;

import com.woxis.votingapp.dto.ScoreDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.woxis.votingapp.model.VoteOptionEnum.AGAINST;
import static com.woxis.votingapp.model.VoteOptionEnum.FAVOR;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "CREATOR_ID")
    private User creator;
    private String subject;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "voting")
    private List<Vote> votes = new ArrayList<>();

    public void addVote(Vote vote) {
        vote.setVoting(this);
        votes.add(vote);
    }

    @Override
    public String toString() {
        return "Voting{" +
                "id=" + id +
                ", creator=" + creator +
                ", subject='" + subject + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
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
        return new ScoreDTO(scores.get(FAVOR), scores.get(AGAINST));
    }
}
