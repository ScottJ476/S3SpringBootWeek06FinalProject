package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stand {
  private Long standPK;
  private String standId;
  private String manufacturer;
  private String model;
  private BigDecimal price;

  @JsonIgnore
  public Long getStandPK() {
    return standPK;
  }
}
