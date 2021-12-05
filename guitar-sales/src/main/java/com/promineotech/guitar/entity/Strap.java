package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Strap {
  private Long strapPK;
  private String strapId;
  private String manufacturer;
  private String model;
  private String material;
  private String color;
  private BigDecimal price;
  
}
