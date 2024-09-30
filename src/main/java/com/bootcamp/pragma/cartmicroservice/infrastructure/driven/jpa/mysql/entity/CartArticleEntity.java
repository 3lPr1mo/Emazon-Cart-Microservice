package com.bootcamp.pragma.cartmicroservice.infrastructure.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_article")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartEntity cartEntity;

    @Column(name = "article_id", nullable = false)
    private Long articleId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false, columnDefinition = "Decimal(10,2)")
    private Double totalPrice;
}
