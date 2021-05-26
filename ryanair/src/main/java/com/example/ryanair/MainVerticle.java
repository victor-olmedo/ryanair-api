package com.example.ryanair;

import io.reactivex.Completable;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.client.HttpResponse;
import io.vertx.reactivex.ext.web.client.WebClient;

import static java.util.function.Predicate.not;

public class MainVerticle extends AbstractVerticle {

    @Override
    public Completable rxStart() {

        final var flightHandler = new FlightHandler();

        final Router router = Router.router(Vertx.currentContext().owner());

        router.get("/flights").handler(flightHandler::handle);

        return vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(8080)
                .ignoreElement();
    }

}
