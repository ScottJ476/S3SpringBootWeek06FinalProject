package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Guitar {
  private Long guitarPK;
  private String guitarId;
  private String manufacturer;
  private String model;
  private StringType stringType;
  private int numStrings;
  private String bodyShape;
  private String topWood;
  private String backSidesWood;
  private BigDecimal price;
  
  @JsonIgnore
  public Long getGuitarPK() {
    return guitarPK;
  }
}
