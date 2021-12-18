package com.promineotech.guitar.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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
@Slf4j
public class DefaultGuitarOrderDao implements GuitarOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Optional<Order> fetchOrder(String orderId) {
    log.debug("DAO: fetchOrder={}", orderId);
   // @formatter:off
   String sql = "" 
       + "SELECT * " 
       + "FROM orders "
       + "WHERE order_id = :order_id";
   // @formatter:on
 
  Map<String, Object> params = new HashMap<>();
  params.put("order_id", orderId);
 
  return Optional.ofNullable(jdbcTemplate.query(sql, params, new OrderResultSetExtractor()));
  } 
    
  @Override
  public Order saveOrder(String orderId, Customer customer, Guitar guitar, Strap strap, Capo capo,
      Stand stand, Pick pick, BigDecimal price) {

    SqlParams params =
        generateInsertSql(orderId, customer, guitar, strap, capo, stand, pick, price);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    Long orderPK = keyHolder.getKey().longValue();

    // @formatter:off
    return Order.builder()
        .orderPK(orderPK)
        .orderId(orderId)
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

  @Override
  public Order updateOrder(Order order, Customer customer, Guitar guitar, Strap strap, Capo capo,
      Stand stand, Pick pick, BigDecimal price) {
    log.debug("updateOrder={}", order);

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
        + "WHERE "
            + "order_id = :order_id";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();

    params.put("order_id", order.getOrderId());
    params.put("customer_fk", customer.getCustomerPK());
    params.put("strap_fk", strap.getStrapPK());
    params.put("capo_fk", capo.getCapoPK());
    params.put("stand_fk", stand.getStandPK());
    params.put("pick_fk", pick.getPickPK());
    params.put("guitar_fk", guitar.getGuitarPK());
    params.put("price", price);

    int rowsUpdated = jdbcTemplate.update(sql, params);
    log.debug(rowsUpdated + " row(s) were updated.");
      
   // @formatter:off
      return Order.builder()
          .orderPK(order.getOrderPK())
          .orderId(order.getOrderId())
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

  @Override
  public void deleteOrder(String orderId) {
    log.debug("DAO: orderId={}", orderId);

   // @formatter:off
   String sql = ""
       + "DELETE FROM orders "
       + "WHERE order_id = :order_id";
   // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", orderId);

    int rows = jdbcTemplate.update(sql, params);
    if (rows == 0) {
      System.out.println("orderId: " + orderId + " was not found!");
    } else {
      System.out.println(rows + " row(s) deleted successfully.");
    }
  }

  private SqlParams generateInsertSql(String orderId, Customer customer, Guitar guitar, Strap strap,
      Capo capo, Stand stand, Pick pick, BigDecimal price) {
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders ("
        + "order_id, customer_fk, strap_fk, capo_fk, stand_fk, pick_fk, guitar_fk, price"
        + ") VALUES ("
        + ":order_id, :customer_fk, :strap_fk, :capo_fk, :stand_fk, :pick_fk, :guitar_fk, :price"
        + ")";
    // @formatter:on

    SqlParams params = new SqlParams();

    params.sql = sql;
    params.source.addValue("order_id", orderId);
    params.source.addValue("customer_fk", customer.getCustomerPK());
    params.source.addValue("strap_fk", strap.getStrapPK());
    params.source.addValue("capo_fk", capo.getCapoPK());
    params.source.addValue("stand_fk", stand.getStandPK());
    params.source.addValue("pick_fk", pick.getPickPK());
    params.source.addValue("guitar_fk", guitar.getGuitarPK());
    params.source.addValue("price", price);

    return params;
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

  private Customer fetchCustomerByPK(Long customerPK) {
    // @formatter:off
    String sql = "" 
        + "SELECT * " 
        + "FROM customers "
        + "WHERE customer_pk = :customer_pk";
    // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("customer_pk", customerPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()))
        .orElseThrow(
            () -> new NoSuchElementException("customerPK=" + customerPK + " was not found"));
  }

  private Guitar fetchGuitarByPK(Long guitarPK) {
    // @formatter:off
   String sql = "" 
       + "SELECT * " 
       + "FROM guitars "
       + "WHERE guitar_pk = :guitar_pk";
   // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("guitar_pk", guitarPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new GuitarResultSetExtractor()))
        .orElseThrow(() -> new NoSuchElementException("guitarPK=" + guitarPK + " was not found"));
  }

  private Strap fetchStrapByPK(Long strapPK) {
  // @formatter:off
   String sql = "" 
     + "SELECT * " 
     + "FROM straps "
     + "WHERE strap_pk = :strap_pk";
 // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("strap_pk", strapPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new StrapResultSetExtractor()))
        .orElseThrow(() -> new NoSuchElementException("strapPK=" + strapPK + " was not found"));
  }
  
  private Capo fetchCapoByPK(Long capoPK) {
    // @formatter:off
   String sql = "" 
       + "SELECT * " 
       + "FROM capos "
       + "WHERE capo_pk = :capo_pk";
   // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("capo_pk", capoPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new CapoResultSetExtractor()))
        .orElseThrow(() -> new NoSuchElementException("capoPK=" + capoPK + " was not found"));
  }

  private Stand fetchStandByPK(Long standPK) {
    // @formatter:off
   String sql = "" 
       + "SELECT * " 
       + "FROM stands "
       + "WHERE stand_pk = :stand_pk";
   // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("stand_pk", standPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new StandResultSetExtractor()))
        .orElseThrow(() -> new NoSuchElementException("standPK=" + standPK + " was not found"));
  }
  
  private Pick fetchPickByPK(Long pickPK) {
    // @formatter:off
   String sql = "" 
       + "SELECT * " 
       + "FROM picks "
       + "WHERE pick_pk = :pick_pk";
   // @formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("pick_pk", pickPK);

    return Optional.ofNullable(jdbcTemplate.query(sql, params, new PickResultSetExtractor()))
        .orElseThrow(() -> new NoSuchElementException("pickPK=" + pickPK + " was not found"));
  }
  
  class OrderResultSetExtractor implements ResultSetExtractor<Order> {
   @Override
   public Order extractData(ResultSet rs) throws SQLException {
   rs.next();
  
      // @formatter:off
      return Order.builder()
          .orderPK(rs.getLong("order_pk"))
          .orderId(rs.getString("order_id"))
          .customer(fetchCustomerByPK(rs.getLong("customer_fk")))
          .guitar(fetchGuitarByPK(rs.getLong("guitar_fk")))
          .strap(fetchStrapByPK(rs.getLong("strap_fk")))
          .capo(fetchCapoByPK(rs.getLong("capo_fk")))
          .stand(fetchStandByPK(rs.getLong("stand_fk")))
          .pick(fetchPickByPK(rs.getLong("pick_fk")))
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
