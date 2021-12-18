package com.promineotech.guitar.controller;

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
  public Order fetchOrder(String orderId) {
    log.debug("Order={}", orderId);
    return guitarOrderService.fetchOrder(orderId);
  }
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return guitarOrderService.createOrder(orderRequest);
  }

  @Override
  public Order updateOrder(OrderRequest orderRequest) {
    log.debug("Order={}", orderRequest);
    return guitarOrderService.updateOrder(orderRequest);
  }
  
  @Override
  public void deleteOrder(String orderId) {
    log.debug("orderId={}", orderId);
    guitarOrderService.deleteOrder(orderId);
  }
}
