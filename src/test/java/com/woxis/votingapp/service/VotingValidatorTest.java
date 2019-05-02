package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.exception.IllegalEntityStateException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class VotingValidatorTest {

    private VotingValidator votingValidator = new VotingValidator();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldAllowToCreateWithProperDates() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(6))
                .build();

        // when && then
        votingValidator.validate(votingDTO);
    }

    @Test
    public void whenProvideStartDateBeforeTodayShouldThrowException() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusDays(9))
                .build();

        // when && then
        expectedException.expect(IllegalEntityStateException.class);
        votingValidator.validate(votingDTO);
    }

    @Test
    public void whenProvideEndDateBeforeStartDateShouldThrowException() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now().plusDays(9))
                .endDate(LocalDate.now())
                .build();

        // when && then
        expectedException.expect(IllegalEntityStateException.class);
        votingValidator.validate(votingDTO);
    }

    @Test
    public void whenVotingTimeSpanIsLessThan7DaysShouldThrowException() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .build();

        // when && then
        expectedException.expect(IllegalEntityStateException.class);
        votingValidator.validate(votingDTO);
    }

    @Test
    public void whenVotingTimeSpanIsMoreThan1MonthShouldThrowException() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1).plusDays(1))
                .build();

        // when && then
        expectedException.expect(IllegalEntityStateException.class);
        votingValidator.validate(votingDTO);
    }

    @Test
    public void whenVotingTimeSpanIsM1MonthShouldPassValidation() {
        // given
        VotingDTO votingDTO = VotingDTO.builder()
                .subject("anySubject")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .build();

        // when && then
        votingValidator.validate(votingDTO);
    }
}