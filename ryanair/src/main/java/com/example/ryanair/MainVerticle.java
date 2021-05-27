package com.example.ryanair;

import io.reactivex.Completable;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    @Override
    public Completable rxStart() {

        final FlightHandler flightHandler = new FlightHandler();

        final Router router = Router.router(Vertx.currentContext().owner());

        router.get("/flights").handler(flightHandler::handle);

        return vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(8080)
                .ignoreElement();
    }

}
