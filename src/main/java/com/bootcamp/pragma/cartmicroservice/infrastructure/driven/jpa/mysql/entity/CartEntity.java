package com.bootcamp.pragma.cartmicroservice.infrastructure.driven.jpa.mysql.entity;

import com.bootcamp.pragma.cartmicroservice.domain.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime update_at;

    @OneToMany(mappedBy = "cartEntity")
    private List<CartArticleEntity> cartArticles;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        update_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        update_at = LocalDateTime.now();
    }

}

