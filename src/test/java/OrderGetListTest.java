import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderGetListTest extends OrderBaseTest{

    @Test
    @DisplayName("Check order list can be received")
    //    в тело ответа возвращается список заказов
    public void getOrderListTest() {

        getOrderList()
                .log().all()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());

    }

}
