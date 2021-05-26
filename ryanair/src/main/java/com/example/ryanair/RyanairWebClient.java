package com.example.ryanair;

import io.reactivex.Single;
import io.vertx.core.json.JsonArray;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;

public class RyanairWebClient {
    public Single<JsonArray> getFlights() {
        return WebClient
                .create(Vertx.currentContext().owner())
                .get(80, "www.ryanair.com", "/api/booking/v4/es-es/availability?ADT=1&CHD=0&Disc=0&INF=0&Origin=MAD&TEEN=0&promoCode=&IncludeConnectingFlights=false&FlexDaysBeforeIn=2&FlexDaysIn=2&RoundTrip=true&FlexDaysBeforeOut=2&FlexDaysOut=2&DateIn=2021-07-17&DateOut=2021-06-17&Destination=SCQ&ToUs=AGREED")
                .rxSend()
                .map(HttpResponse::bodyAsJsonObject)
                .map(json -> json.getJsonArray("trips"));
    }
}
