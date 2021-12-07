package com.promineotech.guitar.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.OrderRequest;
import com.promineotech.guitar.service.GuitarOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultGuitarOrderController implements GuitarOrderController {

  @Autowired
  private GuitarOrderService guitarOrderService;
  
  @Override
  public Order createOrder( OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return guitarOrderService.createOrder(orderRequest);
  }

}
