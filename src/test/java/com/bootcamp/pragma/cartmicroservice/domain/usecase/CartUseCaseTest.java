package com.bootcamp.pragma.cartmicroservice.domain.usecase;

import com.bootcamp.pragma.cartmicroservice.domain.api.usecase.CartUseCase;
import com.bootcamp.pragma.cartmicroservice.domain.exception.UserHaveAlreadyAnActiveCartException;
import com.bootcamp.pragma.cartmicroservice.domain.model.Cart;
import com.bootcamp.pragma.cartmicroservice.domain.model.Status;
import com.bootcamp.pragma.cartmicroservice.domain.spi.ICartPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CartUseCaseTest {
    @Mock
    private ICartPersistencePort cartPersistencePort;

    private CartUseCase cartUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartUseCase = new CartUseCase(cartPersistencePort);
    }

    @Test
    void shouldSaveCartSuccessfullyWhenUserDoNotHaveACart(){
        Cart cart = new Cart(null, 1L, null, null, null);
        when(cartPersistencePort.findCartByUserId(1L)).thenReturn(Optional.empty());
        cartUseCase.saveCart(cart);
        verify(cartPersistencePort, times(1)).findCartByUserId(1L);
    }

    @Test
    void shouldSaveCartWhenStatusIsCanceled() {
        Cart cart = new Cart(null, 1L, Status.CANCELED, null, null);
        when(cartPersistencePort.findCartByUserId(1L)).thenReturn(Optional.of(cart));
        cartUseCase.saveCart(cart);
        verify(cartPersistencePort, times(1)).findCartByUserId(1L);
    }

    @Test
    void shouldThrowErrorWhenCartIsActive() {
        Cart cart = new Cart(null, 1L, Status.ACTIVE, null, null);
        when(cartPersistencePort.findCartByUserId(1L)).thenReturn(Optional.of(cart));
        assertThrows(UserHaveAlreadyAnActiveCartException.class, () -> cartUseCase.saveCart(cart));
        verify(cartPersistencePort, times(1)).findCartByUserId(1L);
    }
}
