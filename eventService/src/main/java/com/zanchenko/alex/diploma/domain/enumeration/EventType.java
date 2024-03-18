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
    REPAIRING("repairing"),

    /**
     * Event type for reactor maintenance.
     */
    REACTOR_MAINTENANCE("reactor maintenance"),

    /**
     * Event type for cooling system overhaul.
     */
    COOLING_SYSTEM_OVERHAUL("cooling system overhaul"),

    /**
     * Event type for emergency drill.
     */
    EMERGENCY_DRILL("emergency drill"),

    /**
     * Event type for containment inspection.
     */
    CONTAINMENT_INSPECTION("containment inspection"),

    /**
     * Event type for fuel rod replacement.
     */
    FUEL_ROD_REPLACEMENT("fuel rod replacement"),

    /**
     * Event type for radiation monitoring.
     */
    RADIATION_MONITORING("radiation monitoring"),

    /**
     * Event type for security training.
     */
    SECURITY_TRAINING("security training"),

    /**
     * Event type for emergency generator test.
     */
    EMERGENCY_GENERATOR_TEST("emergency generator test"),

    /**
     * Event type for control room upgrades.
     */
    CONTROL_ROOM_UPGRADES("control room upgrades"),

    /**
     * Event type for fuel storage inspection.
     */
    FUEL_STORAGE_INSPECTION("fuel storage inspection"),

    /**
     * Event type for emergency cooling test.
     */
    EMERGENCY_COOLING_TEST("emergency cooling test"),

    /**
     * Event type for radiation protection maintenance.
     */
    RADIATION_PROTECTION_MAINTENANCE("radiation protection maintenance"),

    /**
     * Event type for fire safety drill.
     */
    FIRE_SAFETY_DRILL("fire safety drill"),

    /**
     * Event type for evacuation exercise.
     */
    EVACUATION_EXERCISE("evacuation exercise"),

    /**
     * Event type for emergency response training.
     */
    EMERGENCY_RESPONSE_TRAINING("emergency response training");

    private final String displayName;

}
