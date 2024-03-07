package com.zanchenko.alex.diploma.domain.autentication;

/**
 * Enumeration representing possible roles in the authentication system.
 * Each role defines a specific level of access and privileges within the system.
 *
 * @author Alex Zanchenko
 */
public enum ERole {
  /**
   * Represents a regular user with basic permissions.
   */
  ROLE_USER,
  /**
   * Represents a moderator with elevated access privileges beyond regular users.
   */
  ROLE_MODERATOR,
  /**
   * Represents an administrator with the highest level of access and control over system.
   */
  ROLE_ADMIN
}