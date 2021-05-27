package com.example.ryanair;

import io.reactivex.functions.Consumer;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.RoutingContext;

@SuppressWarnings("CheckReturnValue")
public class FlightHandler {

    private final FlightsProcessor flightsProcessor;

    public FlightHandler() {
        this.flightsProcessor = new FlightsProcessor();
    }

    public void handle(final RoutingContext ctx) {
        flightsProcessor.process()
                .subscribe((Consumer<JsonArray>) ctx::json);
    }
}
