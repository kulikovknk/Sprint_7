package ru.yandex.practikum.client;

import io.restassured.response.ValidatableResponse;
import ru.yandex.practikum.dto.CourierRequest;
import ru.yandex.practikum.dto.DeleteRequest;
import ru.yandex.practikum.dto.LoginRequest;

import static io.restassured.RestAssured.given;
import static ru.yandex.practikum.config.Config.*;

public class CourierClient extends RestClient {

    // create courier
    public ValidatableResponse createCourier(CourierRequest courierRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .body(courierRequest)
                .post(getAPICourierCreate())
                .then();
    }

    // login courier
    public ValidatableResponse loginCourier(LoginRequest loginRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .body(loginRequest)
                .post(getAPICourierLogin())
                .then();
    }

    // delete courier
    public ValidatableResponse deleteCourier(DeleteRequest deleteRequest) {

        return given()
                .spec(getDefaultRequestSpec())
                .delete(getAPICourierDelete(), deleteRequest.getId())
                .then();
    }

}
