package com.zanchenko.alex.diploma.service;

import com.zanchenko.alex.diploma.domain.autentication.User;

import java.util.List;

public interface EventAssignmentService {

    void assignExecutors(List<Long> userIDs, Long eventID);

    void selfAssignExecute(Long userID, Long eventID);
}
