package com.bootcamp.pragma.cartmicroservice.domain.model;

public class CartArticle {
    private Long id;
    private Cart cart;
    private Long articleId;
    private Integer quantity;
    private Double totalPrice;

    public CartArticle(Long id, Cart cart, Long articleId, Integer quantity, Double totalPrice) {
        this.id = id;
        this.cart = cart;
        this.articleId = articleId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
