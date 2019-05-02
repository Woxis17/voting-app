package com.woxis.votingapp.service;

import com.woxis.votingapp.dto.VotingDTO;
import com.woxis.votingapp.exception.IllegalStateException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VotingValidator {

    public void validate(VotingDTO votingDTO) {
        LocalDate startDate = votingDTO.getStartDate();
        LocalDate endDate = votingDTO.getEndDate();

        LocalDate today = LocalDate.now();

        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            throw new IllegalStateException("Dates must starts from today");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalStateException("End date must be after start date");
        }

        if (endDate.isBefore(startDate.plusDays(6)) || endDate.isAfter(startDate.plusMonths(1))) {
            throw new IllegalStateException("Minimum time span is 1 week and maximum is 1 month");
        }
    }
}
