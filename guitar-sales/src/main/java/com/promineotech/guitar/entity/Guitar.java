package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guitar {

  private Long modelPK;
  private String modelId;
  private String model;
  private String manufacturer;
  private StringType stringType;
  private int numStrings;
  private String bodyShape;
  private String topWood;
  private String backSidesWood;
  private BigDecimal price;
  
  
}
