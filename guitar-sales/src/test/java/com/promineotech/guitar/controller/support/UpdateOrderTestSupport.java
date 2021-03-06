package com.promineotech.guitar.controller.support;

public class UpdateOrderTestSupport extends BaseTest{
  
  protected String updateOrderBody() {
    // @formatter:off
    return "{\n"
        + "   \"orderId\":\"MARTINEZ_JULIE_ORDER_2\",\n"
         + "   \"customer\":\"MARTINEZ_JULIE\",\n"
         + "   \"guitar\":\"912CE_TAYLOR\",\n"
         + "   \"strap\":\"VINTAGE_BELT_LEATHER_MARTIN\",\n"
         + "   \"capo\":\"QUICK_CHANGE_KYSER\",\n"
         + "   \"stand\":\"GFW_GTRA_40000_GATOR\",\n"
         + "   \"pick\":\"CELLULOID_MEDIUM_FENDER\"\n"
         + "}";
    // @formatter:on
  }
}
