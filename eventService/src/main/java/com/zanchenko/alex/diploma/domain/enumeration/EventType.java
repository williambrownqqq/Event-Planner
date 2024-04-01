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
    PREVENTIVE_MAINTENANCE("preventive_maintenance"),
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
    REACTOR_MAINTENANCE("reactor_maintenance"),

    /**
     * Event type for cooling system overhaul.
     */
    COOLING_SYSTEM_OVERHAUL("cooling_system_overhaul"),

    /**
     * Event type for emergency drill.
     */
    EMERGENCY_DRILL("emergency_drill"),

    /**
     * Event type for containment inspection.
     */
    CONTAINMENT_INSPECTION("containment_inspection"),

    /**
     * Event type for fuel rod replacement.
     */
    FUEL_ROD_REPLACEMENT("fuel_rod_replacement"),

    /**
     * Event type for radiation monitoring.
     */
    RADIATION_MONITORING("radiation_monitoring"),

    /**
     * Event type for security training.
     */
    SECURITY_TRAINING("security_training"),

    /**
     * Event type for emergency generator test.
     */
    EMERGENCY_GENERATOR_TEST("emergency_generator_test"),

    /**
     * Event type for control room upgrades.
     */
    CONTROL_ROOM_UPGRADES("control_room_upgrades"),

    /**
     * Event type for fuel storage inspection.
     */
    FUEL_STORAGE_INSPECTION("fuel_storage_inspection"),

    /**
     * Event type for emergency cooling test.
     */
    EMERGENCY_COOLING_TEST("emergency_cooling_test"),

    /**
     * Event type for radiation protection maintenance.
     */
    RADIATION_PROTECTION_MAINTENANCE("radiation_protection_maintenance"),

    /**
     * Event type for fire safety drill.
     */
    FIRE_SAFETY_DRILL("fire_safety_drill"),

    /**
     * Event type for evacuation exercise.
     */
    EVACUATION_EXERCISE("evacuation_exercise"),

    /**
     * Event type for emergency response training.
     */
    EMERGENCY_RESPONSE_TRAINING("emergency_response_training");

    private final String displayName;

}
