package com.bootcamp.pragma.cartmicroservice.domain.spi;

import com.bootcamp.pragma.cartmicroservice.domain.model.Cart;
import com.bootcamp.pragma.cartmicroservice.domain.model.Status;

import java.util.Optional;

public interface ICartPersistencePort {
    void save(Cart cart);
    Optional<Cart> findCartByUserId(Long userId);
    Status findCartStatusByUserId(Long userId);
}
