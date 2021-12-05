package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class capos {
  private Long capoPK;
  private String manufactuer;
  private String model;
  private BigDecimal price;
  

}
