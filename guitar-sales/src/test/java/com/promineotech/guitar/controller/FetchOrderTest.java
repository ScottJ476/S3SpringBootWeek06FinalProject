package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.guitar.controller.support.FetchOrderTestSupport;
import com.promineotech.guitar.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
                "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
                 config = @SqlConfig(encoding = "utf-8"))
class FetchOrderTest extends FetchOrderTestSupport{

  @Test
  void testThatAnOrderIsReturnedWhenAValidOrderIdIsSupplied() {
    
    // Given: A valid guitarId and URI
    String orderId = "BAKER_BRUCE_ORDER_1";
    String uri = String.format("%s?orderId=%s", getBaseUriForOrders(), orderId);

    // When: a connection is made to the URI
    ResponseEntity<Order> response = getRestTemplate().getForEntity(uri, Order.class);

    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    // And: the actual order returned is the same as expected order
    Order order = response.getBody();
    assertThat(order.getOrderId()).isEqualTo("BAKER_BRUCE_ORDER_1");
    assertThat(order.getCustomer().getCustomerId()).isEqualTo("BAKER_BRUCE");
    assertThat(order.getGuitar().getGuitarId()).isEqualTo("C40II_YAMAHA");
    assertThat(order.getStrap().getStrapId()).isEqualTo("VINTAGE_BELT_LEATHER_MARTIN");
    assertThat(order.getCapo().getCapoId()).isEqualTo("QUICK_CHANGE_KYSER");
    assertThat(order.getStand().getStandId()).isEqualTo("GFW_GTRA_40000_GATOR");
    assertThat(order.getPick().getPickId()).isEqualTo("CELLULOID_MEDIUM_FENDER");
  }
}
