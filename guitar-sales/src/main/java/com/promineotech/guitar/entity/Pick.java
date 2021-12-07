package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pick {
  private Long pickPK;
  private String pickId;
  private String manufacturer;
  private String model;
  private BigDecimal price;

  @JsonIgnore
  public Long getPickPK() {
    return pickPK;
  }
}
