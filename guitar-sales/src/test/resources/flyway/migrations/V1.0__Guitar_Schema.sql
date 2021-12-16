DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS picks;
DROP TABLE IF EXISTS stands;
DROP TABLE IF EXISTS capos;
DROP TABLE IF EXISTS straps;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS guitars;
DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS customers;


CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  phone varchar(20),
  PRIMARY KEY (customer_pk)
);

CREATE TABLE guitars (
  guitar_pk int unsigned NOT NULL AUTO_INCREMENT,
  guitar_id varchar(60) NOT NULL,
  manufacturer varchar(60) NOT NULL,
  model varchar(60) NOT NULL,
  string_type enum ('STEEL', 'NYLON') NOT NULL,
  num_strings int NOT NULL,
  body_shape varchar(60) NOT NULL,
  top_wood varchar(60) NOT NULL,
  back_sides_wood varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (guitar_pk),
  UNIQUE KEY (guitar_id)
);

CREATE TABLE images (
  image_pk int unsigned NOT NULL AUTO_INCREMENT,
  guitar_fk int unsigned NOT NULL,
  image_id varchar(40) NOT NULL,
  width int NOT NULL,
  height int NOT NULL,
  mime_type enum('image/jpeg', 'image/png'),
  name varchar(256),
  data mediumblob NOT NULL,
  PRIMARY KEY (image_pk),
  FOREIGN KEY (guitar_fk) REFERENCES guitars (guitar_pk)
);
  
CREATE TABLE straps (
  strap_pk int unsigned NOT NULL AUTO_INCREMENT,
  strap_id varchar(30) NOT NULL,
  manufacturer varchar(60) NOT NULL,
  model varchar(60) NOT NULL,
  material varchar(40) NOT NULL, 
  color varchar(20) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (strap_pk),
  UNIQUE KEY (strap_id)
);

CREATE TABLE capos (
  capo_pk int unsigned NOT NULL AUTO_INCREMENT,
  capo_id varchar(30) NOT NULL,
  manufacturer varchar(60) NOT NULL,
  model varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (capo_pk),
  UNIQUE KEY (capo_id)
);

CREATE TABLE stands (
  stand_pk int unsigned NOT NULL AUTO_INCREMENT,
  stand_id varchar(30) NOT NULL,
  manufacturer varchar(60) NOT NULL,
  model varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (stand_pk),
  UNIQUE KEY (stand_id)
);

CREATE TABLE picks (
  pick_pk int unsigned NOT NULL AUTO_INCREMENT,
  pick_id varchar(30) NOT NULL,
  manufacturer varchar(60) NOT NULL,
  model varchar(100) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (pick_pk),
  UNIQUE KEY (pick_id)
);

CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  order_id varchar(30) NOT NULL,
  customer_fk int unsigned NOT NULL,
  strap_fk int unsigned,
  capo_fk int unsigned,
  stand_fk int unsigned,
  pick_fk int unsigned,
  guitar_fk int unsigned NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  UNIQUE KEY (order_id),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (strap_fk) REFERENCES straps (strap_pk) ON DELETE CASCADE,
  FOREIGN KEY (capo_fk) REFERENCES capos (capo_pk) ON DELETE CASCADE,
  FOREIGN KEY (stand_fk) REFERENCES stands (stand_pk) ON DELETE CASCADE,
  FOREIGN KEY (pick_fk) REFERENCES picks (pick_pk) ON DELETE CASCADE,
  FOREIGN KEY (guitar_fk) REFERENCES guitars (guitar_pk) ON DELETE CASCADE
);