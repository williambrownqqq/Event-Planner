package com.zanchenko.alex.diploma.domain.autentication;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a role that a user can have within the application.
 * Extends {@link BaseEntity} for persistence.
 *
 * @author Alex Zanchenko
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  /**
   * The name of the user role, represented as an {@link ERole} enum value.
   */
  @Enumerated(EnumType.STRING) // JPA annotation for how enum should be persisted in database
  @Column(length = 20)
  ERole name;
}

