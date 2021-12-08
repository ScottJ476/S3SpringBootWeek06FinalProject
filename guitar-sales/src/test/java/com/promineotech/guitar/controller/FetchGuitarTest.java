package com.promineotech.guitar.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.guitar.Constants;
import com.promineotech.guitar.controller.support.FetchGuitarTestSupport;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.service.GuitarSalesService;

class FetchGuitarTest {

  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {"classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
          "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatDoNotPolluteTheApplicationContext extends FetchGuitarTestSupport {

    @Test
    void testThatGuitarIsReturnedWhenAValidGuitarIdIsSupplied() {

      // Given: A valid guitarId and URI
      String guitarId = "912CE_TAYLOR";
      String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);

      // When: a connection is made to the URI
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

      // Given: An invalid guitarId and valid URI
      String guitarId = "Unknown Value";
      String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);

      // When: a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET,
          null, new ParameterizedTypeReference<>() {});

      // Then: A success (200) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

      // And: an error message is returned
      Map<String, Object> error = response.getBody();

      assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @MethodSource("com.promineotech.guitar.controller.FetchGuitarTest#parametersForInvalidInput")
    void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(String guitarId,
        String reason) {
      // Given: An invalid guitarId and valid URI
      String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);

      // When: a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET,
          null, new ParameterizedTypeReference<>() {});

      // Then: a BAD_REQUEST (400) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

      // And: an error message is returned
      Map<String, Object> error = response.getBody();

      assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
    }
  }

  static Stream<Arguments> parametersForInvalidInput() {
    // @formatter:off
    return Stream.of(
        arguments("@#$%^&&%", "GuitarId contains non-alpha-numeric characters"),
        arguments("C".repeat(Constants.GUITARID_MAX_LENGTH + 1), "GuitarId length too long")
        );
    // @formatter:on
  }

  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
      scripts = {"classpath:flyway/migrations/V1.0__Guitar_Schema.sql",
          "classpath:flyway/migrations/V1.1__Guitar_Data.sql"},
      config = @SqlConfig(encoding = "utf-8"))
  class TestsThatPolluteTheApplicationContext extends FetchGuitarTestSupport {
    @MockBean
    private GuitarSalesService guitarSalesService;

    @Test
    void testThatAnUplannedErrorResultsInA500Status() {

      // Given: An invalid guitarId and valid URI
      String guitarId = "Invalid";
      String uri = String.format("%s?guitarId=%s", getBaseUriForGuitars(), guitarId);

      doThrow(new RuntimeException("Something went wrong!")).when(guitarSalesService)
          .fetchGuitar(guitarId);
      // When: a connection is made to the URI
      ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.GET,
          null, new ParameterizedTypeReference<>() {});

      // Then: A success (200) status code is returned
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

      // And: an error message is returned
      Map<String, Object> error = response.getBody();

      assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

