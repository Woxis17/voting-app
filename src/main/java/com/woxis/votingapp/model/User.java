package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private final static Collection USER_ROLE = Collections.singletonList(new SimpleGrantedAuthority("USER"));

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "creator")
    private List<Voting> votingsCreated = new ArrayList<>();
    @OneToMany(mappedBy = "votingUser")
    private List<Vote> votes = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public void addVoting(Voting voting) {
        voting.setCreator(this);
        votingsCreated.add(voting);
    }

    public void addVote(Vote vote) {
        vote.setVotingUser(this);
        votes.add(vote);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return USER_ROLE;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
