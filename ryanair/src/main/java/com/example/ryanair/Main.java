package com.example.ryanair;

import io.vertx.reactivex.core.Vertx;

public class Main {

 @SuppressWarnings("CheckReturnValue")
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    MainVerticle mv = new MainVerticle();

    vertx.rxDeployVerticle(mv)
      .subscribe(id -> System.out.println("Verticle " + id + " deployed"),
        err -> System.out.println("Error: "+err));
  }
}
