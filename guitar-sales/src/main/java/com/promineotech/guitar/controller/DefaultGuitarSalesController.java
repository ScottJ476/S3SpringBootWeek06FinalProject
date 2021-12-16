package com.promineotech.guitar.controller;

import java.util.Optional;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.service.GuitarSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultGuitarSalesController implements GuitarSalesController {
  
  @Autowired
  private GuitarSalesService guitarSalesService;

  @Override
  public Optional<Guitar> fetchGuitar(String guitarId) {
    log.debug("guitarId={}", guitarId);
    return guitarSalesService.fetchGuitar(guitarId);
  }

//  /**
//   * 
//   */
//  @Override
//  public Optional<Guitar> updateGuitar(String guitarId) {
//    log.debug("guitarId={}", guitarId);
//    return guitarSalesService.updateGuitar(guitarId);
//  }
  
  /**
   * 
   */
  @Override
  public String uploadImage(MultipartFile image, Long guitarPK) {
log.debug("image={}, guitarPK={}", image, guitarPK);
    String imageId = guitarSalesService.uploadImage(image, guitarPK);
    String json = "{\"imageId\":\"" + imageId + "\"}";
    
    return json;
  }

 

}
