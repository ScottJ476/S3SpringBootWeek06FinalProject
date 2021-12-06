package com.promineotech.guitar.controller.support;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.StringType;

public class FetchGuitarTestSupport extends BaseTest {
  protected List<Guitar> buildExpected() {
    List<Guitar> list = new LinkedList<>();
    
    // @formatter:off
    list.add(Guitar.builder()
        .modelId("912CE_TAYLOR")
        .manufacturer("Taylor")
        .model("912ce Builders Edition")
        .stringType(StringType.STEEL)
        .numStrings(6)
        .bodyShape("Grand Concert")
        .topWood("Lutz Spruce")
        .backSidesWood("Indian Rosewood")
        .price(new BigDecimal("5499.00"))
        .build());
    
    list.add(Guitar.builder()
        .modelId("254CE_TAYLOR")
        .manufacturer("Taylor")
        .model("254ce")
        .stringType(StringType.STEEL)
        .numStrings(12)
        .bodyShape("Grand Auditorium")
        .topWood("Sitka Spruce")
        .backSidesWood("Rosewood")
        .price(new BigDecimal("1099.00"))
        .build());
    // @formatter:on
    
    return list;
  }

}
