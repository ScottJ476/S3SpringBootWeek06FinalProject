package com.promineotech.guitar.service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.guitar.dao.GuitarOrderDao;
import com.promineotech.guitar.entity.Capo;
import com.promineotech.guitar.entity.Customer;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.OrderRequest;
import com.promineotech.guitar.entity.Pick;
import com.promineotech.guitar.entity.Stand;
import com.promineotech.guitar.entity.Strap;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultGuitarOrderService implements GuitarOrderService {

  @Autowired
  private GuitarOrderDao guitarOrderDao;

  @Transactional(readOnly = true)
  @Override
  public Order fetchOrder(String orderId) {
    log.debug("fetchOrder={}", orderId);

    return guitarOrderDao.fetchOrder(orderId)
        .orElseThrow(() -> new NoSuchElementException(
        "Order with ID=" + orderId + " was not found"));
        
  }
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("createOrder={}", orderRequest);

    String orderId = orderRequest.getOrderId();
    Customer customer = getCustomer(orderRequest);
    Guitar guitar = getGuitar(orderRequest);
    Strap strap = getStrap(orderRequest);
    Capo capo = getCapo(orderRequest);
    Stand stand = getStand(orderRequest);
    Pick pick = getPick(orderRequest);

    BigDecimal price = guitar.getPrice().add(strap.getPrice()).add(capo.getPrice())
        .add(stand.getPrice()).add(pick.getPrice());

    return guitarOrderDao.saveOrder(orderId, customer, guitar, strap, capo, stand, pick, price);
  }

  @Transactional
  @Override
  public Order updateOrder(OrderRequest orderRequest) {
    log.debug("updateOrder={}", orderRequest);

    Order order = getOrder(orderRequest);
    Customer customer = getCustomer(orderRequest);
    Guitar guitar = getGuitar(orderRequest);
    Strap strap = getStrap(orderRequest);
    Capo capo = getCapo(orderRequest);
    Stand stand = getStand(orderRequest);
    Pick pick = getPick(orderRequest);

    BigDecimal price = guitar.getPrice().add(strap.getPrice()).add(capo.getPrice())
        .add(stand.getPrice()).add(pick.getPrice());

    return guitarOrderDao.updateOrder(order, customer, guitar, strap, capo, stand, pick, price);
  }

  @Transactional
  @Override
  public void deleteOrder(String orderId) {
    log.info("The deleteOrder method was called with orderId={}", orderId);

    guitarOrderDao.deleteOrder(orderId);
  }

  private Pick getPick(OrderRequest orderRequest) {
    return guitarOrderDao.fetchPick(orderRequest.getPick())
        .orElseThrow(() -> new NoSuchElementException(
            "Pick with ID=" + orderRequest.getPick() + " was not found"));
  }

  private Stand getStand(OrderRequest orderRequest) {
    return guitarOrderDao.fetchStand(orderRequest.getStand())
        .orElseThrow(() -> new NoSuchElementException(
            "Stand with ID=" + orderRequest.getStand() + " was not found"));
  }

  private Capo getCapo(OrderRequest orderRequest) {
    return guitarOrderDao.fetchCapo(orderRequest.getCapo())
        .orElseThrow(() -> new NoSuchElementException(
            "Capo with ID=" + orderRequest.getCapo() + " was not found"));
  }

  private Strap getStrap(OrderRequest orderRequest) {
    return guitarOrderDao.fetchStrap(orderRequest.getStrap())
        .orElseThrow(() -> new NoSuchElementException(
            "Strap with ID=" + orderRequest.getStrap() + " was not found"));
  }

  private Guitar getGuitar(OrderRequest orderRequest) {
    return guitarOrderDao.fetchGuitar(orderRequest.getGuitar())
        .orElseThrow(() -> new NoSuchElementException(
            "Guitar with ID=" + orderRequest.getGuitar() + " was not found"));
  }

  private Customer getCustomer(OrderRequest orderRequest) {
    return guitarOrderDao.fetchCustomer(orderRequest.getCustomer())
        .orElseThrow(() -> new NoSuchElementException(
            "Customer with ID=" + orderRequest.getCustomer() + " was not found"));
  }

  private Order getOrder(OrderRequest orderRequest) {
    return guitarOrderDao.fetchOrder(orderRequest.getOrderId())
        .orElseThrow(() -> new NoSuchElementException(
            "Order with ID=" + orderRequest.getOrderId() + " was not found"));
  }
}
