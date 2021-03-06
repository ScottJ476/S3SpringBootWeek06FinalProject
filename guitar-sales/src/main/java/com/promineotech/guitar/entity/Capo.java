package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Capo {
  private Long capoPK;
  private String capoId;
  private String manufacturer;
  private String model;
  private BigDecimal price;
  
  @JsonIgnore
  public Long getCapoPK() {
    return capoPK;
  }
}
