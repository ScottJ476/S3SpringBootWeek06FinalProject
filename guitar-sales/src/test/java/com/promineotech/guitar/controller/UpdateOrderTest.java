package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineotech.guitar.controller.support.UpdateOrderTestSupport;
import com.promineotech.guitar.entity.Order;

  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(scripts = {
          "classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
          "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
      config = @SqlConfig(encoding = "utf-8"))
  class UpdateOrderTest extends UpdateOrderTestSupport {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Test
    void testUpdateOrderReturnsSuccess200() {
      // Given: an order as JSON
      String body = createOrderBody();
      String uri = getBaseUriForOrders();
      
      int numRowsOrders = JdbcTestUtils.countRowsInTable(jdbcTemplate,  "orders");
      
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      
      HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
      
      // When: the order is sent
      ResponseEntity<Order> response = 
          getRestTemplate().exchange(uri,  HttpMethod.PUT, bodyEntity, Order.class);
   // Then: a 200 status is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      
      // And: the returned order is correct
      assertThat(response.getBody()).isNotNull();
      
      Order order = response.getBody();
      assertThat(order.getOrderPK()).isEqualTo("1");
      assertThat(order.getCustomer().getCustomerId()).isEqualTo("MARTINEZ_JULIE");
      assertThat(order.getGuitar().getGuitarId()).isEqualTo("912CE_TAYLOR");
      assertThat(order.getStrap().getStrapId()).isEqualTo("VINTAGE_BELT_LEATHER_MARTIN");
      assertThat(order.getCapo().getCapoId()).isEqualTo("QUICK_CHANGE_KYSER");
      assertThat(order.getStand().getStandId()).isEqualTo("GFW_GTRA_40000_GATOR");
      assertThat(order.getPick().getPickId()).isEqualTo("CELLULOID_MEDIUM_FENDER");
      
      assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "orders")).isEqualTo(numRowsOrders + 1);

      
    }
  }