package com.promineotech.guitar.service;

import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.entity.Guitar;

public interface GuitarSalesService {

  /**
   * 
   * @param model
   * @return
   */
  Optional<Guitar> fetchGuitar(String guitarId);
  /**
   * 
   * @param image
   * @param guitarPK
   * @return
   */

  String uploadImage(MultipartFile image, Long guitarPK);

}
