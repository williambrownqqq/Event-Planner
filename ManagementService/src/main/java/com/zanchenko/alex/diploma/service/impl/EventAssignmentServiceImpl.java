package com.zanchenko.alex.diploma.service.impl;

import com.zanchenko.alex.diploma.domain.Event;
import com.zanchenko.alex.diploma.domain.Task;
import com.zanchenko.alex.diploma.domain.autentication.User;
import com.zanchenko.alex.diploma.repository.EventRepository;
import com.zanchenko.alex.diploma.repository.UserRepository;
import com.zanchenko.alex.diploma.service.EventAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventAssignmentServiceImpl implements EventAssignmentService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public void assignExecutors(List<Long> userIDs, Long eventID) {
        Optional<Event> optionalEvent = eventRepository.findById(eventID);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            List<User> executors = userRepository.findAllById(userIDs);
            // Clear existing executors and add new ones
            event.getExecutors().clear();
            event.getExecutors().addAll(executors);

            eventRepository.save(event);
        }
    }

    @Override
    public void selfAssignExecute(Long userID, Long eventID) {
        Optional<Event> optionalEvent = eventRepository.findById(eventID);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();

            Optional<User> executor = userRepository.findById(userID);
            // Check if the user is already assigned to the event
            if (!event.getExecutors().contains(executor.get())) {
                // Add the user to the event's list of executors
                event.getExecutors().add(executor.get());
                eventRepository.save(event);
            }
        }
    }

    @Override
    public void selfUnAssignExecute(Long userID, Long eventID) {
        Optional<Event> optionalEvent = eventRepository.findById(eventID);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Optional<User> executor = userRepository.findById(userID);
            if (executor.isPresent() && event.getExecutors().contains(executor.get())) {
                // Filter out the user with the given userID and collect the result into a new list
                List<User> updatedExecutors = event.getExecutors().stream()
                        .filter(user -> !user.getId().equals(userID))
                        .collect(Collectors.toList());
                // Update the executors list with the filtered list
                event.setExecutors(updatedExecutors);
                // Save the updated event within the same transaction
                eventRepository.save(event);
            }
        }
    }

}
