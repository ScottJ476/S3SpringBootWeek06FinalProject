package com.promineotech.guitar.entity;

public enum ImageMimeType {
  IMAGE_JPEG("image/jpeg");
  private String mimeType;
  
  private ImageMimeType(String mimeType) {
    this.mimeType = mimeType;
  }
  
  /**
   * 
   * @return
   */
  public String getMimeType() {
    return mimeType;
  }
}
