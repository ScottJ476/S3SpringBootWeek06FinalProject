package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.guitar.controller.support.FetchGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
    "classpath:flyway/migrations/V1.1__Guitar_Data.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchGuitarTest extends FetchGuitarTestSupport{

  @Test
  void testThatGuitarsAreReturnedWhenAValidModelIsSupplied() {
    //System.out.println(getBaseUri());
    // Given: A valid model and URI
    String model = "912CE_TAYLOR";
    String uri = String.format("%s?model=%s",  getBaseUri(), model);
    
    // When:  a connection is made to the URI
    ResponseEntity<List<Guitar>> response = getRestTemplate().exchange(uri,
        HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
    // Then: a list of guitars is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
