package com.denis.matwiejuk.kafka.practice.repository;

import com.denis.matwiejuk.kafka.practice.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    @Query("FROM Stock WHERE symbol = :symbol")
    public Iterable<Stock> findBySymbol(@Param("symbol") String symbol);
}
