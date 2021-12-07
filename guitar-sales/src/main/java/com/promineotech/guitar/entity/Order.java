package com.promineotech.guitar.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long orderPK;
  private Customer customer;
  private Strap strap;
  private Capo capo;
  private Stand stand;
  private Pick pick;
  private Guitar guitar;
  private BigDecimal price;

}
