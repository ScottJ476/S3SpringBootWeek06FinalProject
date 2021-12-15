package com.promineotech.guitar.dao;

import java.math.BigDecimal;
import java.util.Optional;
import com.promineotech.guitar.entity.Capo;
import com.promineotech.guitar.entity.Customer;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.Pick;
import com.promineotech.guitar.entity.Stand;
import com.promineotech.guitar.entity.Strap;

public interface GuitarOrderDao {
  Optional<Customer> fetchCustomer(String custumerId);
  Optional<Guitar> fetchGuitar(String guitarId);
  Optional<Strap> fetchStrap(String strapId);
  Optional<Capo> fetchCapo(String capoId);
  Optional<Stand> fetchStand(String standId);
  Optional<Pick> fetchPick(String pickId);

  Order saveOrder(Customer customer, Guitar guitar, Strap strap, Capo capo, Stand stand, Pick pick,
      BigDecimal price);
  
  void updateOrder(Order order, Customer customer, Guitar guitar, Strap strap, Capo capo, Stand stand,
      Pick pick, BigDecimal price);
}
