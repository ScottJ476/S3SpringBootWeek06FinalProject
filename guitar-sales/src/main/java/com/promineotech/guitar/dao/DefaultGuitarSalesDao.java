package com.promineotech.guitar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Image;
import com.promineotech.guitar.entity.ImageMimeType;
import com.promineotech.guitar.entity.StringType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultGuitarSalesDao implements GuitarSalesDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /**
   * 
   */
  @Override
  public Optional<Image> retrieveImage(String imageId) {
    // @formatter:off
    String sql = ""
        + "SELECT * FROM images "
        + "WHERE image_id = :image_id";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("image_id", imageId);
    
    return jdbcTemplate.query(sql, params, new ResultSetExtractor<>() {

      @Override
      public Optional<Image> extractData(ResultSet rs) 
          throws SQLException {
        if(rs.next()) {
          // @formatter:off
          return Optional.of(Image.builder()
              .imagePK(rs.getLong("image_pk"))
              .guitarFK(rs.getLong("guitar_fk"))
              .imageId(rs.getString("image_id"))
              .width(rs.getInt("width"))
              .height(rs.getInt("height"))
              .mimeType(ImageMimeType.fromString(rs.getString("mime_type")))
              .name(rs.getString("name"))
              .data(rs.getBytes("data"))
              .build());
          // @formatter:on
        }
        
        return Optional.empty();
      }});
  }
  
  @Override
  public void saveImage(Image image) {
    log.debug("imageId = " + image.getImageId());
  String sql = ""
      + "INSERT INTO images ("
      + "guitar_fk, image_id, width, height, mime_type, name, data"
      + ") VALUES ("
      + ":guitar_fk, :image_id, :width, :height, :mime_type, :name, :data"
      + ")";
  
  Map<String, Object> params = new HashMap<>();
  params.put("guitar_fk", image.getGuitarFK());
  params.put("image_id", image.getImageId());
  params.put("width", image.getWidth());
  params.put("height", image.getHeight());
  params.put("mime_type", image.getMimeType().getMimeType());
  params.put("name", image.getName());
  params.put("data", image.getData());
  
  jdbcTemplate.update(sql, params);
  }

  @Override
  public Optional<Guitar> fetchGuitar(String guitarId) {
    log.debug("DAO: guitarId={}", guitarId);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM guitars "
        + "WHERE guitar_id = :guitar_id";
    // @formatter:on
        
    Map<String, Object> params = new HashMap<>();
    params.put("guitar_id", guitarId);
    
    return jdbcTemplate.query(sql, params, new ResultSetExtractor<>() {
      
      @Override
      public Optional<Guitar> extractData(ResultSet rs) throws SQLException {
        if (rs.next()) {
       // @formatter:off
          return Optional.of(Guitar.builder()
              .guitarPK(rs.getLong("guitar_pk"))
              .guitarId(rs.getString("guitar_id"))
              .manufacturer(rs.getString("manufacturer"))
              .model(rs.getString("model"))
              .stringType(StringType.valueOf(rs.getString("string_type")))
              .numStrings(rs.getInt("num_strings"))
              .bodyShape(rs.getString("body_shape"))
              .topWood(rs.getString("top_wood"))
              .backSidesWood(rs.getString("back_sides_wood"))
              .price(rs.getBigDecimal("price"))
              .build());
          // @formatter:on
         }
        
        return Optional.empty();
        }});
  }
}
    
