package com.denis.matwiejuk.kafka.practice.resources;

import com.denis.matwiejuk.kafka.practice.kafka.Sender;
import com.denis.matwiejuk.kafka.practice.models.Stock;
import com.denis.matwiejuk.kafka.practice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@Controller
@RequestMapping(path = "/api/v1/stock")
public class StockResources {

    public enum StockDataSize {
        SMALL, LARGE
    }

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    public Sender sender;

    @PostMapping(path = "/start/{size}")
    public @ResponseBody String startCommand(
            @PathVariable("size")
            StockDataSize size
    ) throws FileNotFoundException {
        sender.sendStockData(size);
        return "Done";
    }

    @GetMapping(path = "/stock")
    public @ResponseBody Iterable<Stock> getStockData() {
        return stockRepository.findAll();
    }

    @GetMapping(path = "/stock/{symbol}")
    public @ResponseBody Iterable<Stock> getStockBySymbol(
            @PathVariable
            String symbol
    ) {
        return stockRepository.findBySymbol(symbol);
    }

}
