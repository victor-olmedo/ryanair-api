package com.example.ryanair;

import io.reactivex.Completable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.buffer.Buffer;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;

public class MainVerticle extends AbstractVerticle {

  @Override
  public Completable rxStart() {
    return WebClient
      .create(vertx)
      .get(80, "www.ryanair.com", "/api/booking/v4/es-es/availability?ADT=1&CHD=0&Disc=0&INF=0&Origin=MAD&TEEN=0&promoCode=&IncludeConnectingFlights=false&FlexDaysBeforeIn=2&FlexDaysIn=2&RoundTrip=true&FlexDaysBeforeOut=2&FlexDaysOut=2&DateIn=2021-07-17&DateOut=2021-06-17&Destination=SCQ&ToUs=AGREED")
      .rxSend()
      .doOnSuccess(response -> {

        printFlightDetails(response);
        System.exit(0);

      })
      .doOnError(err ->
        System.out.println("Something went wrong " + err.getMessage()))
      .ignoreElement();

  }

  private void printFlightDetails(HttpResponse<Buffer> response){
    JsonArray trips = response.bodyAsJsonObject().getJsonArray("trips"), dates, flights;
    int i = 1, j = 1;

    // Obtengo los viajes (ida y vuelta)
    for (Object trip:trips) {
      System.out.println("trip " + i++ + ": " + trip.toString());
      dates = ((JsonObject) trip).getJsonArray("dates");
      j = 1;

      // Obtengo todas las fechas (porque ponemos 2 días antes y 2 días después)
      for (Object date:dates) {
        flights = ((JsonObject) date).getJsonArray("flights");

        // Obtengo los datos de los vuelos y los precios
        for (Object flight:flights) {
          System.out.println("flight " + j + ": " + flight.toString());
          System.out.println("price " + j++ + ": " + ((JsonObject) flight).getJsonObject("regularFare").getJsonArray("fares").getJsonObject(0).getString("amount") + "€");
          System.out.println();
        }
      }
    }
  }

}
