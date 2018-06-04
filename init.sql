DROP TABLE IF EXISTS machines;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS customers;


CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL CHECK (email <> ''),
    balance INT NOT NULL CHECK (balance >= 0)
);

CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    min_limit INT NULL CHECK (min_limit >= 0),
    balance INT NULL CHECK (balance >= 0)
);

CREATE TABLE group_members (
    id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    group_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

CREATE TABLE machines (
    id SERIAL PRIMARY KEY,
    make TEXT NOT NULL,
    price INT NOT NULL CHECK (price > 0),
    owner_c INT NULL,
    owner_g INT NULL,
    FOREIGN KEY (owner_c) REFERENCES customers(id) ON DELETE CASCADE,
    FOREIGN KEY (owner_g) REFERENCES groups(id) ON DELETE CASCADE
);

INSERT INTO customers (email, balance) VALUES
    ('aladar@gmail.com', 1000),
    ('bela@gmail.com', 2000),
    ('cecilia@gmail.com', 3000),
    ('dalma@gmail.com', 4000),
    ('elemer@gmail.com', 5000),
    ('ferenc@gmail.com', 6000),
    ('gabriella@gmail.com', 7000),
    ('helena@gmail.com', 8000),
    ('imre@gmail.com', 9000),
    ('jolan@gmail.com', 9999)
;

INSERT INTO groups (name, min_limit, balance) VALUES
    ('Gold diggers', 800, 1100),
    ('Farmers', 500, 100),
    ('For the Earth', 0, 0)
;

INSERT INTO group_members (customer_id, group_id) VALUES
    (1,1),
    (2,1),
    (3,2),
    (4,2)
;

INSERT INTO machines (make, price, owner_c, owner_g) VALUES
    ('John Deere 5100M', 300, null, 1),
    ('John Deere 6175M', 400, null, 2),
    ('John Deere 6320', 500, null, 2),
    ('John Deere 6430', 600, null, null),
    ('John Deere 6920S', 900, null, null),
    ('John Deere 7230R', 1000, null, null),
    ('John Deere 7250R', 1100, null, null),
    ('John Deere 8370RT', 2000, null, null),
    ('Pöttinger 12 Hamster', 300, null, null),
	('Gehl 3000', 250, 3, null),
    ('Gehl 4240', 500, null, 1),
    ('HR910', 500, null, 2),
    ('Claas Mega C600', 600, null, null)
;