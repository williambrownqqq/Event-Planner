package com.zanchenko.alex.diploma.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The {@code EventState} enum represents different event states that can be associated with event.
 * Each state represents a specific status of event.
 *
 * @author Alex Zanchenko
 */

@Getter
@AllArgsConstructor
public enum EventState {
    /**
     * A broad term for information requiring attention.
     */
    NOTICE("notice"), // A broad term for information requiring attention.

    /**
     * If the information contains specific instructions or commands.
     */
    DIRECTIVE("directive"), // If the information contains specific instructions or commands.

    /**
     * If the information is intended for a specific audience.
     */
    ANNOUNCEMENT("announcement"), //If the information is intended for a specific audience.

    /**
     * Theoretical action or concept.
     */
    THEORY("theory"),

    /**
     * Event in backlog, yet to be started.
     */
    BACKLOG("backlog"),

    /**
     * Event currently in progress.
     */
    IN_PROGRESS("in progress"),

    /**
     * Event currently in review.
     */
    IN_REVIEW("in review"),

    /**
     * Event completed.
     */
    DONE("done");

    private final String displayName;
}
