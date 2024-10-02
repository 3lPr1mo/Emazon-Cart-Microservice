package com.bootcamp.pragma.cartmicroservice.domain.api.usecase;

import com.bootcamp.pragma.cartmicroservice.domain.api.ICartArticleServicePort;
import com.bootcamp.pragma.cartmicroservice.domain.api.ICartServicePort;
import com.bootcamp.pragma.cartmicroservice.domain.exception.ExcessiveCategoriesException;
import com.bootcamp.pragma.cartmicroservice.domain.model.CartArticle;
import com.bootcamp.pragma.cartmicroservice.domain.model.Stock;
import com.bootcamp.pragma.cartmicroservice.domain.spi.ICartArticlePersistencePort;
import com.bootcamp.pragma.cartmicroservice.domain.util.DomainConstants;
import com.bootcamp.pragma.cartmicroservice.domain.util.ExceptionConstants;

import java.time.LocalDateTime;
import java.util.Optional;

public class CartArticleUseCase implements ICartArticleServicePort {

    private final ICartServicePort cartServicePort;
    private final ICartArticlePersistencePort cartArticlePersistencePort;

    public CartArticleUseCase(ICartServicePort cartServicePort, ICartArticlePersistencePort cartArticlePersistencePort) {
        this.cartServicePort = cartServicePort;
        this.cartArticlePersistencePort = cartArticlePersistencePort;
    }

    @Override
    public Optional<String> saveArticleToCart(CartArticle cartArticle) {
        if(!isStockAvailable(cartArticle.getArticleId(), cartArticle.getQuantity())) {
            LocalDateTime restockDate = cartArticlePersistencePort.findRestockDateOnTransactionService(cartArticle.getArticleId());
            return Optional.of(DomainConstants.DATE_TO_RESTOCK_ARTICLE + restockDate.toString());
        }
        if(!isCategoriesMaxThanThreeOnCart(cartArticle.getArticleId())){
            throw new ExcessiveCategoriesException(ExceptionConstants.MAX_CATEGORIES_MSG);
        }
        cartServicePort.saveCart(cartArticle.getCart());
        cartArticlePersistencePort.saveCartArticle(cartArticle);
        return Optional.of(DomainConstants.ARTICLE_WAS_SAVED_TO_CART_SUCCESSFULLY);
    }

    private boolean isStockAvailable(Long articleId, Integer quantity) {
        return cartArticlePersistencePort.findStockOnMicroservice(new Stock(articleId, quantity));
    }

    private boolean isCategoriesMaxThanThreeOnCart(Long articleId){
        Integer numCategories = cartArticlePersistencePort.findCategoriesNumOnCartById(articleId);
        return numCategories > DomainConstants.NUM_MAX_OF_CATEGORIES_ON_CART;
    }
}
