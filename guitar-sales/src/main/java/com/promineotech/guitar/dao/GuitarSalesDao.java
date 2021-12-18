package com.promineotech.guitar.dao;

import java.util.Optional;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;

public interface GuitarSalesDao {

  /**
   * 
   * @param guitarId
   * @return
   */
  Optional<Guitar> fetchGuitar(String guitarId);
  
  /**
   * 
   * @param image
   */
  void saveImage(Image image);

  Optional<Image> retrieveImage(String imageId);
}
