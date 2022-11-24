package ru.yandex.practikum.config;

public class Config {

    private static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";
    private static final String BASE_PATH = "/api/v1/";
    private static final String API_COURIER_CREATE = "courier";
    private static final String API_COURIER_LOGIN = "courier/login";
    private static final String API_COURIER_DELETE = "courier/{id}";
    private static final String API_ORDER_CREATE = "orders";
    private static final String API_ORDER_ACCEPT = "orders/accept/{id}";
    private static final String API_ORDER_LIST = "orders";
    private static final String API_ORDER_GET = "orders/track";



    public static String getBaseUri() {
        return BASE_URI;
    }

    public static String getBasePath() {
        return BASE_PATH;
    }

    public static String getAPICourierCreate() {
        return API_COURIER_CREATE;
    }

    public static String getAPICourierLogin() {
        return API_COURIER_LOGIN;
    }

    public static String getAPICourierDelete() {
        return API_COURIER_DELETE;
    }

    public static String getApiOrderCreate() {
        return API_ORDER_CREATE;
    }

    public static String getApiOrderAccept() {
        return API_ORDER_ACCEPT;
    }

    public static String getApiOrderList() {
        return API_ORDER_LIST;
    }

    public static String getApiOrderGet() {
        return API_ORDER_GET;
    }

}

