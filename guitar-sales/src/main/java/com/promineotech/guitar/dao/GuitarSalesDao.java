package com.promineotech.guitar.dao;

import java.util.List;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesDao {

  /**
   * 
   * @param model
   * @return
   */
  List<Guitar> fetchGuitars(String model);

}
