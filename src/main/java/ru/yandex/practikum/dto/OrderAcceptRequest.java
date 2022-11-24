package ru.yandex.practikum.dto;

public class OrderAcceptRequest {

    private final String id;
    private final String courierId;

    public OrderAcceptRequest(String id, String courierId) {
        this.id = id;
        this.courierId = courierId;
    }

}
