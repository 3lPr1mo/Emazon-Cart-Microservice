package com.bootcamp.pragma.cartmicroservice.domain.api;

import com.bootcamp.pragma.cartmicroservice.domain.model.Cart;

public interface ICartServicePort {
    void saveCart(Cart cart);
}
