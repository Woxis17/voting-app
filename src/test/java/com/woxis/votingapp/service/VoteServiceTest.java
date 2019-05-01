package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VoteDTO;
import com.woxis.votingapp.exception.AlreadyPerformedException;
import com.woxis.votingapp.model.User;
import com.woxis.votingapp.model.Vote;
import com.woxis.votingapp.model.Voting;
import com.woxis.votingapp.repository.UserRepository;
import com.woxis.votingapp.repository.VoteRepository;
import com.woxis.votingapp.repository.VotingRepository;
import com.woxis.votingapp.util.UserId;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.woxis.votingapp.model.VoteOptionEnum.AGAINST;
import static com.woxis.votingapp.model.VoteOptionEnum.FAVOR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VotingRepository votingRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void cleanup() {
        voteRepository.deleteAll();
        userRepository.deleteAll();
        votingRepository.deleteAll();
    }

    @Test
    public void userCanVoteOnceWithinTheVotingPeriod() {
        // given
        User user = userRepository.save(User.builder().votes(new ArrayList<>()).build());
        UserId.set(user.getId());
        Voting voting = votingRepository.save(Voting.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .votes(new ArrayList<>()).build());
        VoteDTO voteDTO = new VoteDTO(FAVOR);

        // when
        Long voteId = voteService.vote(voteDTO, voting.getId());

        // then
        Vote vote = voteRepository.findById(voteId).get();
        assertThat(voteRepository.count(), is(1L));
        assertThat(vote.getVoteOption(), is(FAVOR));
        assertThat(vote.getVotingUser().getId(), is(user.getId()));
        assertThat(vote.getVoting().getId(), is(voting.getId()));
    }

    @Test
    public void whenUserVoteTwiceShouldReturnAlreadyPerformedException() {
        // given
        User user = userRepository.save(User.builder().votes(new ArrayList<>()).build());
        UserId.set(user.getId());
        Voting voting = votingRepository.save(Voting.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .votes(new ArrayList<>()).build());
        VoteDTO voteDTO1 = new VoteDTO(FAVOR);
        VoteDTO voteDTO2 = new VoteDTO(AGAINST);

        // when && then
        voteService.vote(voteDTO1, voting.getId());
        expectedException.expect(AlreadyPerformedException.class);
        voteService.vote(voteDTO2, voting.getId());
    }

    @Test
    public void whenUserVoteBeforeWithinVotingPeriodShouldReturnIllegalStateException() {
        // given
        User user = userRepository.save(User.builder().votes(new ArrayList<>()).build());
        UserId.set(user.getId());
        Voting voting = votingRepository.save(Voting.builder()
                .startDate(LocalDate.now().plusDays(1))
                .endDate(LocalDate.now().plusDays(11))
                .votes(new ArrayList<>()).build());
        VoteDTO voteDTO = new VoteDTO(FAVOR);

        // when && then
        expectedException.expect(IllegalStateException.class);
        voteService.vote(voteDTO, voting.getId());
    }

    @Test
    public void whenUserVoteAfterVotingPeriodShouldReturnIllegalStateException() {
        // given
        User user = userRepository.save(User.builder().votes(new ArrayList<>()).build());
        UserId.set(user.getId());
        Voting voting = votingRepository.save(Voting.builder()
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(LocalDate.now().minusDays(1))
                .votes(new ArrayList<>()).build());
        VoteDTO voteDTO = new VoteDTO(FAVOR);

        // when && then
        expectedException.expect(IllegalStateException.class);
        voteService.vote(voteDTO, voting.getId());
    }

}