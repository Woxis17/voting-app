package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private boolean ended;
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
                ", ended=" + ended +
                '}';
    }
}
