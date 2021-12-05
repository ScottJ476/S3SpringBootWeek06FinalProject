package com.promineotech.guitar.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.promineotech.guitar.entity.Guitar;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultGuitarSalesService implements GuitarSalesService {

  @Override
  public List<Guitar> fetchGuitars(String model) {
log.info("The fetchGuitars method was called with model={}",
    model);

    return null;
  }

}
