package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long orderPK;
  private String orderId;
  private Customer customer;
  private Guitar guitar;
  private Strap strap;
  private Capo capo;
  private Stand stand;
  private Pick pick;
  private BigDecimal price;

  @JsonIgnore
  public Long getOrderPK() {
    return orderPK;
  }
}
