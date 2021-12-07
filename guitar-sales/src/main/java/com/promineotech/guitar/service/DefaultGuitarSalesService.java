package com.promineotech.guitar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.guitar.dao.GuitarSalesDao;
import com.promineotech.guitar.entity.Guitar;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultGuitarSalesService implements GuitarSalesService {
  
  @Autowired
  private GuitarSalesDao guitarSalesDao;

  @Override
  public Guitar fetchGuitar(String guitarId) {
    log.info("The fetchGuitar method was called with guitarId={}", guitarId);

    return guitarSalesDao.fetchGuitar(guitarId);
  }

}
