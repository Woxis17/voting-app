package com.woxis.votingapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

  private final static Collection USER_ROLE = Collections.singletonList(new SimpleGrantedAuthority("USER"));

  private String username;
  private String password;
  private String firstName;
  private String lastName;

  @OneToMany(mappedBy = "creator")
  @ToString.Exclude
  private List<Voting> votingsCreated = new ArrayList<>();
  @OneToMany(mappedBy = "votingUser")
  @ToString.Exclude
  private List<Vote> votes = new ArrayList<>();

  public void addVoting(Voting voting) {
    voting.setCreator(this);
    votingsCreated.add(voting);
  }

  public void addVote(Vote vote) {
    vote.setVotingUser(this);
    votes.add(vote);
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
