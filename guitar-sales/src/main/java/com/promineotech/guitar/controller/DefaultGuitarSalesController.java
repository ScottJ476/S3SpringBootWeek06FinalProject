package com.promineotech.guitar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;
import com.promineotech.guitar.service.GuitarSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultGuitarSalesController implements GuitarSalesController {
  
  @Autowired
  private GuitarSalesService guitarSalesService;

  @Override
  public Guitar fetchGuitar(String guitarId) {
    log.debug("guitarId={}", guitarId);
    return guitarSalesService.fetchGuitar(guitarId);
  }
  
  @Override
  public ResponseEntity<byte[]> retrieveImage(String imageId) {
      log.debug("Retrieving image with ID={}", imageId);
      Image image = guitarSalesService.retrieveImage(imageId);
      
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", image.getMimeType().getMimeType());
      headers.add("Content-Length", Integer.toString(image.getData().length));
      
    return ResponseEntity.ok().headers(headers).body(image.getData());
  }
  
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
