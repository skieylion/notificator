package com.example.notification.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class NotificationRouterIT {

    @BeforeAll
    static void setup() {
        String baseUrl = System.getProperty("base.url", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void testHealth() {
        given()
            .when()
                .get("/health")
            .then()
                .statusCode(200)
                .body(equalTo("OK"));
    }

    @Test
    void testNotifications() {
        given()
                .contentType(ContentType.JSON)
                .body("{}")
            .when()
                .post("/notifications")
            .then()
                .statusCode(200)
                .body("status", equalTo("accepted"));
    }
}
