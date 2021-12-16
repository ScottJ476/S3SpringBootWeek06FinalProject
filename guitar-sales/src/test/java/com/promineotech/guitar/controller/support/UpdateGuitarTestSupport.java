package com.promineotech.guitar.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.StringType;

public class UpdateGuitarTestSupport extends BaseTest{

  protected String updateOrderBody() {
    // @formatter:off
    return "{\n"
         + "   \"guitarId\":\"912CE_TAYLOR\",\n"
         + "   \"manufacturer\":\"Taylor\",\n"
         + "   \"model\":\"912ce Builders Edition\",\n"
         + "   \"stringType\":\"STEEL\",\n"
         + "   \"numStrings\":6,\n"
         + "   \"bodyShape\":\"Grand Concert\"\n"
         + "   \"topWood\":\"Lutz Spruce\"\n"
         + "   \"backSidesWood\":\"Indian Rosewood\"\n"
         + "   \"price\":\"6200.00\"\n"
         + "}";
  
    // @formatter:on
  }

//  protected Guitar buildExpected() {
//    // @formatter:off
//      return Guitar.builder()
//           .guitarId("912CE_TAYLOR")
//           .manufacturer("Taylor")
//           .model("912ce Builders Edition")
//           .stringType(StringType.STEEL)
//           .numStrings(6)
//           .bodyShape("Grand Concert")
//           .topWood("Lutz Spruce")
//           .backSidesWood("Indian Rosewood")
//           .price(new BigDecimal("6200.00"))
//           .build();
//       // @formatter:on
//     }

//     protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
//       // formatter:off
//       assertThat(error)
//           .containsKey("message")
//           .containsEntry("status code", status.value())
//           .containsEntry("uri", "/guitars")
//           .containsKey("timestamp")
//           .containsEntry("reason", status.getReasonPhrase());  
//       // formatter:on
//     }
}
