package com.zanchenko.alex.diploma.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The {@code Urgency} enum represents different levels of urgency for events.
 * Each urgency level has a display name associated with it.
 *
 * @author Alex Zanchenko
 */

@Getter
@AllArgsConstructor
public enum Urgency {
    /**
     * Represents critical urgency level.
     */
    CRITICAL("critical"),

    /**
     * Represents high urgency level.
     */
    HIGH("high"),

    /**
     * Represents medium urgency level.
     */
    MEDIUM("medium"),

    /**
     * Represents low urgency level.
     */
    LOW("low");

    private final String displayName;
}
