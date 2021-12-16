package com.promineotech.guitar.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.promineotech.guitar.dao.GuitarSalesDao;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;
import com.promineotech.guitar.entity.ImageMimeType;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultGuitarSalesService implements GuitarSalesService {

  @Autowired
  private GuitarSalesDao guitarSalesDao;

  @Transactional(readOnly = true)
  @Override
  public Image retrieveImage(String imageId) {
    
    return guitarSalesDao.retrieveImage(imageId)
        .orElseThrow(() -> new NoSuchElementException(
            "Could not find image with ID=" + imageId));
  }


  @Transactional
  @Override
  public String uploadImage(MultipartFile file, Long modelPK) {
    String imageId = UUID.randomUUID().toString();

    try (InputStream inputStream = file.getInputStream()) {
      BufferedImage bufferedImage = ImageIO.read(inputStream);

      // @formatter:off
      Image image = Image.builder()
          .guitarFK(modelPK)
          .imageId(imageId)
          .width(bufferedImage.getWidth())
          .height(bufferedImage.getHeight())
          .mimeType(ImageMimeType.IMAGE_JPEG)
          .name(file.getOriginalFilename())
          .data(toByteArray(bufferedImage, "jpeg" ))
          .build();
      // @formatter:on

      guitarSalesDao.saveImage(image);

      return imageId;
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  /**
   * 
   * @param bufferedImage
   * @param string
   * @return
   */
  private byte[] toByteArray(BufferedImage bufferedImage, String renderType) {


    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      if (!ImageIO.write(bufferedImage, renderType, baos)) {
        throw new RuntimeException("Could not write to image buffer");
      }

      return baos.toByteArray();
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public Optional<Guitar> fetchGuitar(String guitarId) {
    log.info("The fetchGuitar method was called with guitarId={}", guitarId);

    Optional<Guitar> guitar = guitarSalesDao.fetchGuitar(guitarId);

    if (guitar.isEmpty()) {
      String msg = String.format("No guitar found with guitarId=%s", guitarId);
      throw new NoSuchElementException(msg);
    }

    return guitar;
  }


  // @Transactional
  // @Override
  // public Optional<Guitar> updateGuitar(String guitarId) {
  // log.info("The updateGuitar method was called with guitarId={}", guitarId);
  //
  // Optional<Guitar> guitar = guitarSalesDao.updateGuitar(guitarId);
  //
  // if(guitar.isEmpty()) {
  // String msg = String.format("No guitar found with guitarId=%s", guitarId);
  // throw new NoSuchElementException(msg);
  // }
  //
  // return guitar;
  // }



}
