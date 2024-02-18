package com.zanchenko.alex.diploma.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

/***
 * The {@code Faciity} class represents a energy system facility in the database.
 *  Extends {@link BaseEntity} class, providing a common structure for all entity classes.
 *  The class defines properties specific to a facility.
 *
 *  @author Alex Zanchenko
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "facility")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Facility extends BaseEntity{

    @Column(name = "facility_title")
    String facilityTitle;

    @Column(name = "photo_url", length = 500)
    String photoURL;

    @Column(name = "description")
    String description;

    @OneToMany( mappedBy = "facility", cascade = CascadeType.ALL)
    Set<Event> events = new HashSet<>();
}
