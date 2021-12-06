package com.promineotech.guitar.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
  public List<Guitar> fetchGuitars(String model) {
    log.debug("DAO: model={}", model);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM models "
        + "WHERE model_id = :model_id";
    // @formatter:on
        
    Map<String, Object> params = new HashMap<>();
    params.put("model_id", model);
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<Guitar>() {

          @Override
          public Guitar mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
            return Guitar.builder()
                .price(new BigDecimal(rs.getString("price")))
                .modelId(rs.getString("model_id"))
                .modelPK(rs.getLong("model_pk"))
                .manufacturer(rs.getString("manufacturer"))
                .model(rs.getString("model"))
                .stringType(StringType.valueOf(rs.getString("string_type")))
                .numStrings(rs.getInt("num_strings"))
                .bodyShape(rs.getString("body_shape"))
                .topWood(rs.getString("top_wood"))
                .backSidesWood(rs.getString("back_sides_wood"))
                .build();
            // @formatter:on
            }});
  }

}
