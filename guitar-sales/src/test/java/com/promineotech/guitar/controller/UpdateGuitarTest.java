package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.guitar.controller.support.UpdateGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.StringType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
        "classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
        "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class UpdateGuitarTest extends UpdateGuitarTestSupport{

//  @Autowired
//  private JdbcTemplate jdbcTemplate;
  
  @Test
  void testUpdateGuitarReturnsSuccess200WhenAValidGuitarIdIsSupplied() {
    //Changes guitar price from 5499.00 to 6200.00
    // Given: A valid guitarID and URI 
    //String guitarId = "912CE_TAYLOR";
    String body = updateOrderBody();
    String uri = getBaseUriForGuitars();
    //String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    
    // When: a connection is made to the URI
    ResponseEntity<Guitar> response =
        getRestTemplate().exchange(uri, HttpMethod.PUT, bodyEntity, Guitar.class);

    
    // Then: A success (200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    // And: the actual guitar returned is the same as expected guitar after being updated
//    Guitar expected = buildExpected();
//    Guitar actual = response.getBody();
//
//    assertThat(actual).isEqualTo(expected);
    
    assertThat(response.getBody()).isNotNull();
    
    Guitar guitar = response.getBody();
    assertThat(guitar.getGuitarPK()).isEqualTo("1");
    assertThat(guitar.getGuitarId()).isEqualTo("912CE_TAYLOR");
    assertThat(guitar.getManufacturer()).isEqualTo("Taylor");
    assertThat(guitar.getModel()).isEqualTo("912ce Builders Edition");
    assertThat(guitar.getStringType()).isEqualTo(StringType.STEEL);
    assertThat(guitar.getNumStrings()).isEqualTo(6);
    assertThat(guitar.getBodyShape()).isEqualTo("Grand Concert");
    assertThat(guitar.getTopWood()).isEqualTo("Lutz Spruce");
    assertThat(guitar.getBackSidesWood()).isEqualTo("Indian Rosewood");
    assertThat(guitar.getPrice()).isEqualTo("6200.00");
    
  }
}
