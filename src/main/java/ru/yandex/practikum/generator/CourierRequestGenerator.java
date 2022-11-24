package ru.yandex.practikum.generator;

import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.practikum.dto.CourierRequest;

public class CourierRequestGenerator {

    public static CourierRequest getRandomCourierRequest() {

        CourierRequest courierRequest = new CourierRequest();
        courierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setPassword(RandomStringUtils.randomAlphabetic(10));
        courierRequest.setFirstName("firstName");

        return courierRequest;

    }
}
