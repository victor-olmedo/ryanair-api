package com.example.ryanair;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.ext.web.RoutingContext;

public class FlightHandler {

    private final FlightsProcessor flightsProcessor;

    public FlightHandler() {
        this.flightsProcessor = new FlightsProcessor();
    }

    public void handle(final RoutingContext ctx) {
        Disposable unused = flightsProcessor.process()
                .subscribe((Consumer<JsonArray>) ctx::json);
    }
}
