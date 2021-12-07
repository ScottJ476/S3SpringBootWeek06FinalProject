package com.promineotech.guitar.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesDao {

  /**
   * 
   * @param model
   * @return
   */
  Guitar fetchGuitar(String guitarId);

}
