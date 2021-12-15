package com.promineotech.guitar.service;

import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.OrderRequest;

public interface GuitarOrderService {

  Order createOrder(OrderRequest orderRequest);

  Order updateOrder(OrderRequest orderRequest);
}
