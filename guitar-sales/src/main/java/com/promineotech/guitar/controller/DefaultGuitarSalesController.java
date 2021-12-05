package com.promineotech.guitar.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.guitar.entity.Guitar;

@RestController
public class DefaultGuitarSalesController implements GuitarSalesController{

  @Override
  public List<Guitar> fetchGuitars(String model) {
    return null;
  }

}
