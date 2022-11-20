import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import ru.yandex.practikum.client.OrderClient;
import ru.yandex.practikum.dto.OrderAcceptRequest;
import ru.yandex.practikum.dto.OrderRequest;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;


public class OrderBaseTest {

    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @After
    public void tearDown() {

    }

    public String createOrder(String firstName,
                                           String lastName,
                                           String address,
                                           String metroStation,
                                           String phone,
                                           int rentTime,
                                           String deliveryDate,
                                           String comment,
                                           String[] color) {

        OrderRequest orderRequest = new OrderRequest(firstName,
                lastName,
                address,
                metroStation,
                phone,
                rentTime,
                deliveryDate,
                comment,
                color);

        ValidatableResponse response = orderClient.createOrder(orderRequest);

        response.assertThat()
                .log().all()
                .statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());

        String orderID = response
                .extract()
                .body()
                .path("track")
                .toString();

        return orderID;

    }

    public ValidatableResponse acceptOrder(String orderID, String courierId) {

        return orderClient.acceptOrder(orderID, courierId);

    }

    public ValidatableResponse getOrderList() {

        return orderClient.getOrderList();

    }

    public ValidatableResponse getOrder(String orderId) {

        return orderClient.getOrder(orderId);

    }

}
