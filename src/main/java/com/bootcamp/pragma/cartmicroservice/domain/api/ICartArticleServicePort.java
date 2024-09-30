package com.bootcamp.pragma.cartmicroservice.domain.api;

import com.bootcamp.pragma.cartmicroservice.domain.model.CartArticle;

import java.util.Optional;

public interface ICartArticleServicePort {
    Optional<String> saveArticleToCart(CartArticle cartArticle);
}
