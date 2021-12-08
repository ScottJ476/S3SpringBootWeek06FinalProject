package com.promineotech.guitar.service;

import java.util.Optional;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesService {

  /**
   * 
   * @param model
   * @return
   */
  Optional<Guitar> fetchGuitar(String guitarId);

}
