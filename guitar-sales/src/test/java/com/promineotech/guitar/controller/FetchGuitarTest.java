package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;
import com.promineotech.guitar.controller.support.FetchGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
        "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class FetchGuitarTest extends FetchGuitarTestSupport {
 
  @Test
  void testThatGuitarIsReturnedWhenAValidGuitarIdIsSupplied() {
    
    // Given: A valid guitarId and URI
    String guitarId = "912CE_TAYLOR";
    String uri = String.format("%s?guitarId=%s",  getBaseUriForGuitars(), guitarId);
    
    // When:  a connection is made to the URI
    ResponseEntity<Guitar> response = getRestTemplate().getForEntity(uri, Guitar.class);
    
    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);  
    
    // And: the actual guitar returned is the same as expected guitar
    Guitar expected = buildExpected();
    Guitar actual = response.getBody();
    
    assertThat(actual).isEqualTo(expected);
    
  }
 
  @Test
  void testThatAnErrorMessageIsReturnedWhenAnUnknownGuitarIdIsSupplied() {
    
    // Given: A valid guitarId and URI
    String guitarId = "Unknown_Value";
    String uri = String.format("%s?guitarId=%s",  getBaseUriForGuitars(), guitarId);
    
    // When:  a connection is made to the URI
    ResponseEntity<?> response = getRestTemplate().getForEntity(uri, Guitar.class);
    
    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);  
    
    // And: the actual guitar returned is the same as expected guitar
    
    
  }


}

