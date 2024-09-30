package com.bootcamp.pragma.cartmicroservice.domain.spi;

import com.bootcamp.pragma.cartmicroservice.domain.model.CartArticle;
import com.bootcamp.pragma.cartmicroservice.domain.model.Stock;

import java.time.LocalDateTime;

public interface ICartArticlePersistencePort {
    void saveCartArticle(CartArticle cartArticle);
    boolean findStockOnMicroservice(Stock stock);
    LocalDateTime findRestockDateOnTransactionService(Long articleId);
    Integer findCategoriesNumOnCartById(Long articleId);
}
