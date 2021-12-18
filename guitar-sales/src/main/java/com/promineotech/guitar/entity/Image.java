package com.promineotech.guitar.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
  private Long imagePK;
  private Long guitarFK;
  private String imageId;
  private int width;
  private int height;
  private ImageMimeType mimeType;
  private String name;
  private byte[] data;
}
