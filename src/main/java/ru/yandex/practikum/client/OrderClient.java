package ru.yandex.practikum.client;

import io.restassured.response.ValidatableResponse;
import ru.yandex.practikum.dto.OrderAcceptRequest;
import ru.yandex.practikum.dto.OrderRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.config.Config.*;

public class OrderClient extends RestClient {

    // create order
    public ValidatableResponse createOrder(OrderRequest orderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(orderRequest)
                .post(getApiOrderCreate())
                .then();
    }

     public ValidatableResponse acceptOrder(String orderID, String courierId) {
        return given()
                .spec(getDefaultRequestSpec())
                .queryParam("courierId", courierId)
                .put(getApiOrderAccept(), orderID)
                .then();
    }

    public ValidatableResponse getOrderList() {
        return given()
                .spec(getDefaultRequestSpec())
                .get(getApiOrderList())
                .then();
    }

    public ValidatableResponse getOrder(String orderId) {
        return given()
                .spec(getDefaultRequestSpec())
                .queryParam("t", orderId)
                .get(getApiOrderGet())
                .then();
    }

}
