package com.promineotech.guitar.service;

import java.util.List;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesService {

  /**
   * 
   * @param model
   * @return
   */
  List<Guitar> fetchGuitars(String model);

}
