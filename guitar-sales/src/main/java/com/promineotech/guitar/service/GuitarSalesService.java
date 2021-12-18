package com.promineotech.guitar.service;

import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;

public interface GuitarSalesService {

  /**
   * 
   * @param guitarId
   * @return
   */
  Guitar fetchGuitar(String guitarId);
  
  /**
   * 
   * @param image
   * @param guitarPK
   * @return
   */
  String uploadImage(MultipartFile image, Long guitarPK);

  /**
   * 
   * @param imageId
   * @return
   */
  Image retrieveImage(String imageId);
  
}
