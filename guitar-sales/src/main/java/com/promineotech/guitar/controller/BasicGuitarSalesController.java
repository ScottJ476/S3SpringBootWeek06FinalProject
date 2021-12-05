package com.promineotech.guitar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.service.GuitarSalesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicGuitarSalesController implements GuitarSalesController {
  
  @Autowired
  private GuitarSalesService guitarSalesService;

  @Override
  public List<Guitar> fetchGuitars(String model) {
    log.debug("model={}", model);
    return guitarSalesService.fetchGuitars(model);
  }

}
