package com.bootcamp.pragma.cartmicroservice.domain.api.usecase;

import com.bootcamp.pragma.cartmicroservice.domain.api.ICartServicePort;
import com.bootcamp.pragma.cartmicroservice.domain.exception.UserHaveAlreadyAnActiveCartException;
import com.bootcamp.pragma.cartmicroservice.domain.model.Cart;
import com.bootcamp.pragma.cartmicroservice.domain.model.Status;
import com.bootcamp.pragma.cartmicroservice.domain.spi.ICartPersistencePort;
import com.bootcamp.pragma.cartmicroservice.domain.util.ExceptionConstants;

import java.util.Optional;

public class CartUseCase implements ICartServicePort {

    private final ICartPersistencePort cartPersistencePort;

    public CartUseCase(ICartPersistencePort cartPersistencePort) {
        this.cartPersistencePort = cartPersistencePort;
    }

    @Override
    public void saveCart(Cart cart) {
        if(!userDoesNotHaveAnActiveCart(cart)){
            throw new UserHaveAlreadyAnActiveCartException(ExceptionConstants.USER_HAVE_ALREADY_AN_ACTIVE_CART);
        }
        cartPersistencePort.save(cart);
    }

    private boolean userDoesNotHaveAnActiveCart(Cart cart) {
        Optional<Cart> existingCart = cartPersistencePort.findCartByUserId(cart.getUserId());
        return existingCart.map(value -> value.getStatus().equals(Status.CANCELED)).orElse(true);
    }

}
