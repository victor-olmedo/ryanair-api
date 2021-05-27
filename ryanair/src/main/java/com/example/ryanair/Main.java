package com.example.ryanair;

import io.reactivex.disposables.Disposable;
import io.vertx.reactivex.core.Vertx;

public class Main {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    MainVerticle mv = new MainVerticle();

    Disposable unused = vertx.rxDeployVerticle(mv)
      .subscribe(id -> System.out.println("Verticle " + id + " deployed"),
        err -> System.out.println("Error: "+err));
  }
}
