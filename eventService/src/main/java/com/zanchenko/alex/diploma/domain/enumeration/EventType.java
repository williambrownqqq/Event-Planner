package com.zanchenko.alex.diploma.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The {@code EventType} enum represents different types of events related to energy facilities.
 * Each event type has a display name associated with it.
 *
 * @author Alex Zanchenko
 */

@Getter
@AllArgsConstructor
public enum EventType {

    /**
     * Event type for preventive maintenance.
     */
    PREVENTIVE_MAINTENANCE("preventive maintenance"),
    /**
     * Event type for repairing.
     */
    OVERHAUL("overhaul"),
    /**
     * The display name of the event type.
     */
    REPAIRING("repairing");

    private final String displayName;

}
