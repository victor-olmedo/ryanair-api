java_binary(
    name = "Main",
    srcs = ["src/main/java/com/example/ryanair/Main.java"],
    main_class = "com.example.ryanair.Main",
    resources = glob(["src/main/resources/**"]),
    deps = [":java_test_deps", ":dependencies"],
)

java_library(
    name = "dependencies",
    srcs = glob(["src/main/java/**/*.java"]),
    deps = [":java_test_deps",],
    #visibility = ["//src/main/java/com/example/cmdline:__pkg__"]
)

java_library(
    name = "java_test_deps",
    exports = [
        "@maven//:io_vertx_vertx_stack_depchain",
        "@maven//:io_vertx_vertx_web",
        "@maven//:io_vertx_vertx_web_client",
        "@maven//:io_vertx_vertx_rx_java2",
        "@maven//:io_vertx_vertx_junit5",
        "@maven//:org_junit_jupiter_junit_jupiter",
    ],
)
