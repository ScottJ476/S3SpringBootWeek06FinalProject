package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineotech.guitar.controller.support.DeleteOrderTestSupport;
import com.promineotech.guitar.entity.Order;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
                "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
                config = @SqlConfig(encoding = "utf-8"))
class DeleteOrderTest extends DeleteOrderTestSupport{
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Test
  void testDeleteOrderReturnsSuccess200() {
    // Given: an valid orderId and URI
    String orderId = "MARTINEZ_JULIE_ORDER_2";
    String uri = String.format("%s?orderId=%s", getBaseUriForOrders(), orderId);;
    
    int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate,  "orders");
    
    // When: a connection is made to the URI
    ResponseEntity<Order> response =
        getRestTemplate().exchange(uri, HttpMethod.DELETE, null, Order.class);
    
    // Then: a 200 status is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
   // And: the number of rows is 1 less than at the start of the test
    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowsOrders - 1);
  }
}
