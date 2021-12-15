package com.promineotech.guitar.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.guitar.entity.Capo;
import com.promineotech.guitar.entity.Customer;
import com.promineotech.guitar.entity.Guitar;
import com.promineotech.guitar.entity.Order;
import com.promineotech.guitar.entity.Pick;
import com.promineotech.guitar.entity.Stand;
import com.promineotech.guitar.entity.Strap;
import com.promineotech.guitar.entity.StringType;
import lombok.extern.slf4j.Slf4j;

@Component
public class DefaultGuitarOrderDao implements GuitarOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Order saveOrder(Customer customer, Guitar guitar, Strap strap, Capo capo, Stand stand,
      Pick pick, BigDecimal price) {

    SqlParams params = generateInsertSql(customer, guitar, strap, capo, stand, pick, price);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    Long orderPK = keyHolder.getKey().longValue();

    // @formatter:off
    return Order.builder()
        .orderPK(orderPK)
        .customer(customer)
        .guitar(guitar)
        .strap(strap)
        .capo(capo)
        .stand(stand)
        .pick(pick)
        .price(price)
        .build();
    // @formatter:on
  }

  // @Override
  // public Order updateOrder(Order order, Customer customer, Guitar guitar, Strap strap, Capo capo,
  // Stand stand,
  // Pick pick, BigDecimal price) {
  //
  // SqlParams params = generateUpdateSql(order, customer, guitar, strap, capo, stand, pick, price);
  //
  // KeyHolder keyHolder = new GeneratedKeyHolder();
  // jdbcTemplate.update(params.sql, params.source, keyHolder);
  //
  // Long orderPK = keyHolder.getKey().longValue();
  //
//    // @formatter:off
//    return null;
//    // @formatter:on
  // }

  @Override
  public void updateOrder(Order order, Customer customer, Guitar guitar, Strap strap, Capo capo,
      Stand stand, Pick pick, BigDecimal price) {

    // @formatter:off
    String sql = ""
        + "UPDATE orders SET "      
        + "customer_fk = :customer_fk, "
        + "strap_fk = :strap_fk, "
        + "capo_fk = :capo_fk, "
        + "stand_fk = :stand_fk, "
        + "pick_fk = :pick_fk, "
        + "guitar_fk = :guitar_fk, "
        + "price = :price "
        + "WHERE order_pk = :order_pk";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("order_pk", order.getOrderPK());
    params.put("customer_fk", customer.getCustomerPK());
    params.put("strap_fk", strap.getStrapPK());
    params.put("capo_fk", capo.getCapoPK());
    params.put("stand_fk", stand.getStandPK());
    params.put("pick_fk", pick.getPickPK());
    params.put("guitar_fk", guitar.getGuitarPK());
    params.put("price", price);

    jdbcTemplate.update(sql, params);
  }

  private SqlParams generateInsertSql(Customer customer, Guitar guitar, Strap strap, Capo capo,
      Stand stand, Pick pick, BigDecimal price) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders ("
        + "customer_fk, strap_fk, capo_fk, stand_fk, pick_fk, guitar_fk, price"
        + ") VALUES ("
        + ":customer_fk, :strap_fk, :capo_fk, :stand_fk, :pick_fk, :guitar_fk, :price"
        + ")";
    // @formatter:on

    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("strap_fk", strap.getStrapPK());
    params.source.addValue("capo_fk", capo.getCapoPK());
    params.source.addValue("stand_fk", stand.getStandPK());
    params.source.addValue("pick_fk", pick.getPickPK());
    params.source.addValue("guitar_fk", guitar.getGuitarPK());
    params.source.addValue("price", price);

    return params;
  }

  // private SqlParams generateUpdateSql(Order order, Customer customer, Guitar guitar, Strap strap,
  // Capo capo,
  // Stand stand, Pick pick, BigDecimal price) {
//    // @formatter:off
//    String sql = ""
//        + "UPDATE orders SET "      
//        + "customer_fk = :customer_fk, "
//        + "strap_fk = :strap_fk, "
//        + "capo_fk = :capo_fk, "
//        + "stand_fk = :stand_fk, "
//        + "pick_fk = :pick_fk, "
//        + "guitar_fk = :guitar_fk, "
//        + "price = :price "
//        + "WHERE order_pk = :order_pk";
//    // @formatter:on
  //
  // SqlParams params = new SqlParams();
  //
  // params.sql = sql;
  // params.source.addValue("order_pk", order.getOrderPK());
  // params.source.addValue("customer_fk", customer.getCustomerPK());
  // params.source.addValue("strap_fk", strap.getStrapPK());
  // params.source.addValue("capo_fk", capo.getCapoPK());
  // params.source.addValue("stand_fk", stand.getStandPK());
  // params.source.addValue("pick_fk", pick.getPickPK());
  // params.source.addValue("guitar_fk", guitar.getGuitarPK());
  // params.source.addValue("price", price);
  //
  //
  // return params;
  // }

  @Override
  public Optional<Order> fetchOrder(Long orderPK) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE order_pk = :order_pk";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("order_pk", orderPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new OrderResultSetExtractor()));
  }

  @Override
  public Optional<Customer> fetchCustomer(String customerId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE customer_id = :customer_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_id", customerId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }

  @Override
  public Optional<Guitar> fetchGuitar(String guitarId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM guitars "
        + "WHERE guitar_id = :guitar_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("guitar_id", guitarId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new GuitarResultSetExtractor()));
  }

  @Override
  public Optional<Strap> fetchStrap(String strapId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM straps "
        + "WHERE strap_id = :strap_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("strap_id", strapId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new StrapResultSetExtractor()));
  }

  @Override
  public Optional<Capo> fetchCapo(String capoId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM capos "
        + "WHERE capo_id = :capo_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("capo_id", capoId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CapoResultSetExtractor()));
  }

  @Override
  public Optional<Stand> fetchStand(String standId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM stands "
        + "WHERE stand_id = :stand_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("stand_id", standId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new StandResultSetExtractor()));
  }

  @Override
  public Optional<Pick> fetchPick(String pickId) {
 // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM picks "
        + "WHERE pick_id = :pick_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("pick_id", pickId);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new PickResultSetExtractor()));
  }

  class OrderResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Order.builder()
          .orderPK(rs.getLong("order_pk"))
          .customer(Customer.class(rs.getLong("customer_fk")))
          .guitar(rs.getString("guitar"))
          .strap(rs.getString("strap"))
          .capo(rs.getString("capo"))
          .stand(rs.getString("stand"))
          .pick(rs.getString("pick"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on

    }
  }

  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
    @Override
    public Customer extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Customer.builder()
          .customerId(rs.getString("customer_id"))
          .customerPK(rs.getLong("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .phone(rs.getString("phone"))
          .build();
      // @formatter:on

    }
  }

  class GuitarResultSetExtractor implements ResultSetExtractor<Guitar> {
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

    }
  }

  class StrapResultSetExtractor implements ResultSetExtractor<Strap> {
    @Override
    public Strap extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Strap.builder()
          .strapPK(rs.getLong("strap_pk"))
          .strapId(rs.getString("strap_id"))
          .manufacturer(rs.getString("manufacturer"))
          .model(rs.getString("model"))
          .material(rs.getString("material"))
          .color(rs.getString("color"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on

    }
  }

  class CapoResultSetExtractor implements ResultSetExtractor<Capo> {
    @Override
    public Capo extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Capo.builder()
          .capoPK(rs.getLong("capo_pk"))
          .capoId(rs.getString("capo_id"))
          .manufacturer(rs.getString("manufacturer"))
          .model(rs.getString("model"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on

    }
  }

  class StandResultSetExtractor implements ResultSetExtractor<Stand> {
    @Override
    public Stand extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Stand.builder()
          .standPK(rs.getLong("stand_pk"))
          .standId(rs.getString("stand_id"))
          .manufacturer(rs.getString("manufacturer"))
          .model(rs.getString("model"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on

    }
  }

  class PickResultSetExtractor implements ResultSetExtractor<Pick> {
    @Override
    public Pick extractData(ResultSet rs) throws SQLException {
      rs.next();

      // @formatter:off
      return Pick.builder()
          .pickPK(rs.getLong("pick_pk"))
          .pickId(rs.getString("pick_id"))
          .manufacturer(rs.getString("manufacturer"))
          .model(rs.getString("model"))
          .price(rs.getBigDecimal("price"))
          .build();
      // @formatter:on

    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
