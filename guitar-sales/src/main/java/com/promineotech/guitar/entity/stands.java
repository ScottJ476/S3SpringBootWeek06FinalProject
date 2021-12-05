package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class stands {
  private Long standPK;
  private String manufacturer;
  private String model;
  private BigDecimal price;

}
