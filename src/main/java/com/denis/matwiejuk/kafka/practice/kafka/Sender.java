package com.denis.matwiejuk.kafka.practice.kafka;

import com.denis.matwiejuk.kafka.practice.models.Stock;
import com.denis.matwiejuk.kafka.practice.resources.StockResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class Sender {

    private KafkaTemplate<String, Serializable> kafkaTemplate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Autowired
    public Sender(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendStockData(StockResources.StockDataSize size) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(String.format("src/main/resources/com/denis/matwiejuk/kafka.practice/kafka/stockData%s.csv", size.toString())));
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String data = sc.next();
            String[] stockData = data.split(",");
            Stock stock = new Stock(
                    stockData[0],
                    LocalDate.parse(stockData[1].trim(), formatter),
                    new BigDecimal(stockData[2].trim()),
                    new BigDecimal(stockData[3].trim()),
                    new BigDecimal(stockData[4].trim()),
                    new BigDecimal(stockData[5].trim()),
                    new BigDecimal(stockData[6].trim()),
                    new BigDecimal(stockData[7].trim()),
                    new BigDecimal(stockData[8].trim())
            );
            kafkaTemplate.send("stock", stock);
        }
    }
}
