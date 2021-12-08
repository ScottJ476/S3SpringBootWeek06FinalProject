package com.promineotech.guitar.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.guitar.dao.GuitarSalesDao;
import com.promineotech.guitar.entity.Guitar;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultGuitarSalesService implements GuitarSalesService {
  
  @Autowired
  private GuitarSalesDao guitarSalesDao;

  @Transactional(readOnly = true)
  @Override
  public Guitar fetchGuitar(String guitarId) {
    log.info("The fetchGuitar method was called with guitarId={}", guitarId);

    Guitar guitar = guitarSalesDao.fetchGuitar(guitarId);
  
    if(guitar.equals(null)) {
      String msg = String.format("No guitar found with guitarId=%s", guitarId);
      throw new NoSuchElementException(msg);
    }
    
    System.out.println(guitar);
    return guitar;
  }

}
