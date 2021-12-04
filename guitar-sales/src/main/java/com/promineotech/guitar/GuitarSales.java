package com.promineotech.guitar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class GuitarSales {

  public static void main(String[] args) {
    SpringApplication.run(GuitarSales.class, args);

  }

}
