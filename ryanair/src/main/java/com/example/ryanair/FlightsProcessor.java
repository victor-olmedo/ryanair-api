package com.example.ryanair;

import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class FlightsProcessor {

    private final RyanairWebClient ryanairWebClient;

    public FlightsProcessor() {
        this.ryanairWebClient = new RyanairWebClient();
    }

    public Single<JsonArray> process() {
        return ryanairWebClient
                .getFlights()
                .map(this::doProcess)
                .map(JsonArray::new);
    }

    private List<JsonObject> doProcess(final JsonArray trips) {
        return trips.stream()
                .map(j -> (JsonObject) j)
                .map(j -> j.getJsonArray("dates"))
                .flatMap(JsonArray::stream)
                .map(j -> (JsonObject) j)
                .map(date -> date.getJsonArray("flights"))
                .filter(not(JsonArray::isEmpty))
                .flatMap(JsonArray::stream)
                .map(j -> (JsonObject) j)
                .map(flightItem -> flightItem.getJsonObject("regularFare"))
                .map(regularFare -> regularFare.getJsonArray("fares"))
                .map(fare -> fare.getJsonObject(0))
                .map(fare -> fare.getString("amount"))
                .map(Float::parseFloat)
                .map(price -> new JsonObject().put("price", price))
                .collect(Collectors.toList());
    }
}
