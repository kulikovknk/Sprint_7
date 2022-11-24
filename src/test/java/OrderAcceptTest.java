import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Random;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderAcceptTest extends OrderBaseTest {

    private static final String[] ORDER_COLOR_GREY_BLACK = {"GREY", "BLACK"};
    private static final String[] ORDER_COLOR_GREY = {"GREY"};
    private static final String[] ORDER_COLOR_BLACK = {"BLACK"};
    private static final String[] ORDER_COLOR_BLANK = {};

    private final String courierId = "127763";


    //    можно указать один из цветов — BLACK или GREY;
    //    можно указать оба цвета;
    //    можно совсем не указывать цвет;
    @Parameterized.Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
//                {"Name" + new Random().nextInt(200),
//                        "lastName" + new Random().nextInt(200),
//                        "Moskva",
//                        "Sokolniki",
//                        "+79111234567",
//                        2,
//                        "2022-10-15",
//                        "no",
//                        ORDER_COLOR_BLACK},
                {"Имя" + new Random().nextInt(200),
                        "Фамилия" + new Random().nextInt(200),
                        "Москва",
                        "Черкизовская",
                        "+79111234577",
                        3,
                        "2022-10-12",
                        "no",
                        ORDER_COLOR_GREY},
//                {"Name" + new Random().nextInt(200),
//                        "lastName" + new Random().nextInt(200),
//                        "Moskva",
//                        "Ostankinskaya",
//                        "+79145634577",
//                        1,
//                        "2022-10-14",
//                        "no",
//                        ORDER_COLOR_GREY_BLACK},
//                {"Name" + new Random().nextInt(200),
//                        "lastName" + new Random().nextInt(200),
//                        "Moskva",
//                        "Altufievo",
//                        "+79145634577",
//                        6,
//                        "2022-10-12",
//                        "no",
//                        ORDER_COLOR_BLANK}
        });
    }

    @Parameterized.Parameter(0)
    public String firstName;

    @Parameterized.Parameter(1)
    public String lastName;

    @Parameterized.Parameter(2)
    public String address;

    @Parameterized.Parameter(3)
    public String metroStation;

    @Parameterized.Parameter(4)
    public String phone;

    @Parameterized.Parameter(5)
    public int rentTime;

    @Parameterized.Parameter(6)
    public String deliveryDate;

    @Parameterized.Parameter(7)
    public String comment;

    @Parameterized.Parameter(8)
    public String[] color;

    @Test
    @DisplayName("Successful request returns ok: true")
    //  принять заказ
    //  успешный запрос возвращает ok: true;
    public void acceptOrderPositiveTest() {

        String orderID = createOrder(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

        ValidatableResponse orderAcceptanceResponse = acceptOrder("391588", courierId);

        orderAcceptanceResponse.assertThat()
                .log().all()
                .statusCode(SC_OK)
                .and()
                .body("ok", equalTo(true));

    }

    @Test
    @DisplayName("If the courier id is not passed, the request returns an error")
    //    принять заказ
    //    если не передать id курьера, запрос вернёт ошибку;
    public void acceptOrderNoCourierIdTest() {

        String orderID = createOrder(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

        ValidatableResponse orderAcceptanceResponse = acceptOrder(orderID, "");

        orderAcceptanceResponse.assertThat()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для поиска"));

    }

    @Test
    @DisplayName("If you pass an incorrect courier id, the request returns an error")
    //  принять заказ
    //  если передать неверный id курьера, запрос вернёт ошибку;
    public void acceptOrderIncorrectCourierIdTest() {

        String orderID = createOrder(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

         ValidatableResponse orderAcceptanceResponse = acceptOrder(orderID, "123456");

        orderAcceptanceResponse.assertThat()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Курьера с таким id не существует"));

    }

    @Test
    @DisplayName("If you do not pass the order number, the request will return an error")
    //    принять заказ
    //    если не передать номер заказа, запрос вернёт ошибку;
    public void acceptOrderNoOrderIdTest() {

        ValidatableResponse orderAcceptanceResponse = acceptOrder("", courierId);

        orderAcceptanceResponse.assertThat()
                .log().all()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для поиска"));

    }

    @Test
    @DisplayName("If you send an incorrect order number, the request will return an error")
    //    принять заказ
    //    если передать неверный номер заказа, запрос вернёт ошибку.
    public void acceptOrderIncorrectOrderIdTest() {

        ValidatableResponse orderAcceptanceResponse = acceptOrder("123456", courierId);

        orderAcceptanceResponse.assertThat()
                .log().all()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Заказа с таким id не существует"));

    }



}
