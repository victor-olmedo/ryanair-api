package com.example.ryanair;

import java.util.List;

public class OutputPrinter {

    public void print(final List<Float> prices) {
        prices.stream()
                .map(price -> String.format("Price %s €", price))
                .forEach(System.out::println);
    }

}
