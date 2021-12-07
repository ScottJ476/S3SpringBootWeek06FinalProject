package com.promineotech.guitar.dao;

import java.math.BigDecimal;
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
import com.promineotech.guitar.entity.StringType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultGuitarSalesDao implements GuitarSalesDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Guitar fetchGuitar(String guitarId) {
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
      public Guitar extractData(ResultSet rs) throws SQLException {
        rs.next();
        
          // @formatter:off
          return Guitar.builder()
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
              .build();
          // @formatter:on
      }});
    }
}
