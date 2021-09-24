package com.woxis.votingapp.service

import com.woxis.votingapp.repository.UserRepository
import com.woxis.votingapp.repository.VoteRepository
import com.woxis.votingapp.repository.VotingRepository
import spock.lang.Specification
import spock.lang.Subject

class VoteServiceSpec extends Specification {

    @Subject
    VoteService voteService
    VoteRepository voteRepository = Mock()
    UserRepository userRepository = Mock()
    VotingRepository votingRepository = Mock()

    def setup() {
        voteService = new VoteService(voteRepository, userRepository, votingRepository)
    }

}
