import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Random;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderGetTest extends OrderBaseTest {

    private static final String[] ORDER_COLOR_GREY_BLACK = {"GREY", "BLACK"};
    private static final String[] ORDER_COLOR_GREY = {"GREY"};
    private static final String[] ORDER_COLOR_BLACK = {"BLACK"};
    private static final String[] ORDER_COLOR_BLANK = {};

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
                {"Name" + new Random().nextInt(200),
                        "lastName" + new Random().nextInt(200),
                        "Moskva",
                        "Luzhniki",
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
    @DisplayName("Successful request returns an object with an order")
    //    успешный запрос возвращает объект с заказом
    public void getOrderTest() {

        String orderID = createOrder(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

        getOrder(orderID)
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("order", notNullValue());

    }

    @Test
    @DisplayName("Request without an order number returns an error")
    //    запрос с несуществующим заказом возвращает ошибку
    public void getOrderNoOrderIdTest() {

        getOrder("")
                .log().all()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для поиска"));

    }

    @Test
    @DisplayName("Request with a non-existent order returns an error")
    //    запрос без номера заказа возвращает ошибку
    public void getOrderNonExistentOrderIdTest() {

        getOrder("123456")
                .log().all()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Заказ не найден"));

    }

}
