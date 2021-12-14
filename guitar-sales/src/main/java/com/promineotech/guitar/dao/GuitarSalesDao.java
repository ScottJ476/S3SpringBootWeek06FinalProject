package com.promineotech.guitar.dao;

import java.util.List;
import java.util.Optional;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;

public interface GuitarSalesDao {

  /**
   * 
   * @param model
   * @return
   */
  Optional<Guitar> fetchGuitar(String guitarId);

  /**
   * 
   * @param image
   */
  void saveImage(Image image);

}
