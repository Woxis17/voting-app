package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="VOTING_USER_ID")
    private User votingUser;
    @ManyToOne
    @JoinColumn(name="VOTING_ID")
    private Voting voting;

    @Enumerated
    private VoteOptionEnum voteOption;
    private boolean changed;

    public Vote(VoteOptionEnum voteOption) {
        this.voteOption = voteOption;
    }
}
