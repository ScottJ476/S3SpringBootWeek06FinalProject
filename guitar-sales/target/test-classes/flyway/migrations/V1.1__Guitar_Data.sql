-- Customers
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('JONES_FLORENCE', 'Florence', 'Jones', '757.553.5847');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('PORTMAN_JERRY', 'Jerry', 'Portman', '346.377.4855');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('BAKER_BRUCE', 'Bruce', 'Baker', NULL);
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('MARTINEZ_JULIE', 'Julie', 'Martinez', '338.965.3098');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('KISSENGER_GARTH', 'Garth', 'Kissenger', '438.789.4860');
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('SANCHEZ_CHRISTINA', 'Christina', 'Sanchez', NULL);
INSERT INTO customers (customer_id, first_name, last_name, phone) VALUES('SMITH_KIMBERLY', 'Kimberly', 'Smith', '777.989.2876');

-- MODELS =======================================================================================================

-- Cordoba
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('RODRIQUEZ_CORDOBA', 'Cordoba', 'Rodriguez Master Series', 'NYLON', 6, 'Classical', 'Cedar', 'Indian Rosewood', 4399.00);

-- Guild
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('P240_GUILD', 'Guild', 'P-240 Memoir', 'STEEL', 6, 'Parlor', 'Sitka Spruce', 'Mahogany', 529.00);

-- Martin
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('HD28_MARTIN', 'Martin', 'HD-28', 'STEEL', 6, 'Dreadnought', 'Sitka Spruce', 'East Indian Rosewood', 3199.00);
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('0018_MARTIN', 'Martin', '00-18', 'STEEL', 6, 'Grand Concert', 'Sitka Spruce', 'Mahogany', 2499.00);
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('DX2E_MARTIN', 'Martin', 'D-X2E', 'STEEL', 12, 'Dreadnought', 'Sitka Spruce', 'High-pressure Laminate', 599.00);

-- Takamine
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('P3NY_TAKAMINE', 'Takamine', 'P3NY New Yorker', 'STEEL', 6, 'Parlor', 'Cedar', 'Sapele', 1449.99);

-- Taylor
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('912CE_TAYLOR', 'Taylor', '912ce Builders Edition', 'STEEL', 6, 'Grand Concert', 'Lutz Spruce', 'Indian Rosewood', 5499.00);
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('254CE_TAYLOR', 'Taylor', '254ce', 'STEEL', 12, 'Grand Auditorium', 'Sitka Spruce', 'Rosewood', 1099.00);

-- Yamaha
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('FG800_YAMAHA', 'Yamaha', 'FG800', 'STEEL', 6, 'Dreadnought', 'Solid Spruce', 'Nato/Okoume', 219.99);
INSERT INTO models (model_id, manufacturer, model, string_type, num_strings, body_shape, top_wood, back_sides_wood, price) VALUES ('C40II_YAMAHA', 'Yamaha', 'C40II Full-scale Classical', 'NYLON', 6, 'Classical', 'Spruce', 'Meranti', 159.99);


-- ACCESSORIES ===================================================================================================

-- Straps
INSERT INTO straps (strap_id, manufacturer, model, material, color, price) VALUES ('2_INCH_POLYPRO_ERNIE_BALL', 'Ernie Ball', '2 Inch Polypro Strap', 'Woven Polypropylene with Leather Ends', 'Black', 6.99);
INSERT INTO straps (strap_id, manufacturer, model, material, color, price) VALUES ('VINTAGE_BELT_LEATHER_MARTIN', 'Martin', 'Vintage Belt Leather Strap', 'Belt Leather', 'Brown', 54.99);

-- Capos
INSERT INTO capos (capo_id, manufacturer, model, price) VALUES ('QUICK_CHANGE_KYSER', 'Kyser', 'Quick-Change Capo', 24.95);
INSERT INTO capos (capo_id, manufacturer, model, price) VALUES ('83CB_TRIGGER_DUNLOP', 'Dunlop', '83CB Trigger Capo', 16.99);

-- Stands
INSERT INTO stands (stand_id, manufacturer, model, price) VALUES ('XCG4_ONSTAGE', 'On-Stage', 'XCG-4 Classic Guitar Stand', 17.95);
INSERT INTO stands (stand_id, manufacturer, model, price) VALUES ('GFW_GTRA_40000_GATOR', 'Gator', 'GFW-GTRA-40000 "A" Style Guitar Stand', 29.99);

-- Picks
INSERT INTO picks (pick_id, manufacturer, model, price) VALUES ('TORTEX_60MM_DUNLOP', 'Dunlop', '418P060 Tortex Standard Guitar Picks - .60mm Orange (12-pack)', 4.49);
INSERT INTO picks (pick_id, manufacturer, model, price) VALUES ('CELLULOID_MEDIUM_FENDER', 'Fender', '351 Celluloid Guitar Pick Medley - Medium (12-pack)', 5.79);