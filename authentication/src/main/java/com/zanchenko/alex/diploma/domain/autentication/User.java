package com.zanchenko.alex.diploma.domain.autentication;

import com.zanchenko.alex.diploma.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing users in the authentication system.
 * Each user has a unique username, password, and email, and can be associated with multiple roles.
 * Extends {@link BaseEntity} for persistence.
 *
 * @author Alex Zanchenko
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "password")

        })
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    /**
     * The user's unique username.
     * Must be between 3 and 50 characters long.
     */
    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 50, message = "Username must be between {min} and {max} characters")
    String username;
    /**
     * The user's password for authentication.
     * Must be between 6 and 100 characters long.
     */
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be between {min} and {max} characters")
    String password;
    /**
     * The user's email address.
     * Must be between 6 and 255 characters long and in a valid email format.
     */
    @NotBlank(message = "Email must not be blank")
    @Email
    @Size(min=6, max = 255, message = "Email must be between {min} and {max} characters")
    String email;
    /**
     * The roles assigned to this user.
     */
    @ManyToMany(fetch = FetchType.LAZY) // Uses lazy loading for performance optimization.
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
