package com.denis.matwiejuk.kafka.practice.kafka;

import com.denis.matwiejuk.kafka.practice.models.Stock;
import com.denis.matwiejuk.kafka.practice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    @Autowired
    StockRepository stockRepository;

    @KafkaListener(topics = "stock",
            containerFactory = "stockListenerContainerFactory")
    public void listenGroupStock(Stock stock) {
        stockRepository.save(stock);
    }
}
