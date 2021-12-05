package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stand {
  private Long pickPK;
  private String pickId;
  private String manufacturer;
  private String model;
  private BigDecimal price;
}
