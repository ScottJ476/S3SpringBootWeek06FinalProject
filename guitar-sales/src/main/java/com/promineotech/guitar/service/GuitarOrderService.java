package com.promineotech.guitar.service;

import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.OrderRequest;

public interface GuitarOrderService {
  
  Order fetchOrder(String orderId);
  
  Order createOrder(OrderRequest orderRequest);

  Order updateOrder(OrderRequest orderRequest);

  void deleteOrder(String orderId);
}
