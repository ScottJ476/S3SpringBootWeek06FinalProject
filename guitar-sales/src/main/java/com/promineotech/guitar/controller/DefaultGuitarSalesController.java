package com.promineotech.guitar.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
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

}
