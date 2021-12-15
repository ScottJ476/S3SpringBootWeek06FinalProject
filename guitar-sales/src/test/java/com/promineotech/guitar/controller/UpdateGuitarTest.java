package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.guitar.controller.support.UpdateGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
        "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class UpdateGuitarTest extends UpdateGuitarTestSupport{

  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Test
  void testUpdateGuitarReturnsSuccess200WhenAValidGuitarIdIsSupplied() {
    // Given: A valid guitarID and URI 
    String guitarId = "912CE_TAYLOR";
    String body = updateGuitarBody();
    String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);
    
    // When: a connection is made to the URI
    ResponseEntity<Guitar> response = getRestTemplate().getForEntity(uri, Guitar.class);
    
    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    
  }

}
