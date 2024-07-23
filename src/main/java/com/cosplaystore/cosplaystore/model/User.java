package com.cosplaystore.cosplaystore.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String password_hash;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "email")
    private String email;
    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private Date birth_day;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)

    private LocalDateTime updated_at;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)

    private LocalDateTime created_at;
}
