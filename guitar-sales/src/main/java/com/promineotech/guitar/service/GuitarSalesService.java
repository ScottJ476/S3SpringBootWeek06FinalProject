package com.promineotech.guitar.service;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesService {

  /**
   * 
   * @param guitarId
   * @return
   */
  Optional<Guitar> fetchGuitar(String guitarId);
  
//  /**
//   * 
//   * @param guitarId
//   * @return
//   */
//  Optional<Guitar> updateGuitar(String guitarId);
  
  /**
   * 
   * @param image
   * @param guitarPK
   * @return
   */
  String uploadImage(MultipartFile image, Long guitarPK);
  
}
