DROP TABLE IF EXISTS insurance_bonds;
DROP TABLE IF EXISTS insurance_services;
DROP TABLE IF EXISTS insurance_companies;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL CHECK (email <> ''),
    salary INT NOT NULL CHECK (salary >= 0)
);

CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    licence_plate TEXT UNIQUE NOT NULL,
    manufacturer TEXT NOT NULL,
    model TEXT NOT NULL
);

CREATE TABLE insurance_companies (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE insurance_services (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    minimum_salary INT NOT NULL CHECK (minimum_salary >= 0),
    length INT NOT NULL CHECK (length >= 1),
    issuer TEXT NOT NULL,
    FOREIGN KEY (issuer) REFERENCES insurance_companies(name) ON DELETE CASCADE
);

CREATE TABLE insurance_bonds (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    car_id INT NOT NULL,
    company_id INT NOT NULL,
    service_id INT NOT NULL,
    issued_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (car_id) REFERENCES cars(id) ON DELETE CASCADE,
    FOREIGN KEY (company_id) REFERENCES insurance_companies(id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES insurance_services(id) ON DELETE CASCADE
);


/* Add new user */
INSERT INTO users (email, salary) VALUES ('aladar@gmail.com', 1000);
INSERT INTO users (email, salary) VALUES
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

/* List users */
SELECT email FROM users;

/* User details */
SELECT email, salary FROM users;

/* Delete existing user */
BEGIN;
    ROLLBACK;
    DELETE FROM users WHERE id=1;
    DELETE FROM insurance_bonds WHERE user_id=1;
COMMIT;

/* List users with no salary */
SELECT email FROM users WHERE salary=0;

/* Add new car */
INSERT INTO cars (licence_plate, manufacturer, model) VALUES ('ABC123', 'Ford', 'Ranger');
INSERT INTO cars (licence_plate, manufacturer, model) VALUES
    ('ASR157', 'Audi', 'A4'),
    ('NFD753', 'Opel', 'Astra'),
    ('CSD483', 'Fiat', '500'),
    ('ADR147', 'Ford', 'Escort'),
    ('FGJ963', 'IFA', '123')
;

/* List cars */
SELECT manufacturer, model FROM cars;

/* List insured cars */
SELECT licence_plate FROM cars JOIN insurance_bonds ON cars.id = car_id;

/* List uninsured cars */
SELECT licence_plate FROM cars LEFT JOIN insurance_bonds ON cars.id = car_id WHERE car_id IS NULL;

/* Delete existing car */
DELETE FROM cars WHERE id=1;

/* Add new insurance company */
BEGIN;
    ROLLBACK;
    INSERT INTO insurance_companies (name) VALUES ('K&H');
    INSERT INTO insurance_services (name, minimum_salary, length, issuer) VALUES ('General issue', 1000, 1, 'K&H');
COMMIT;

/* List insurance companies */
SELECT name FROM insurance_companies;

/* Delete existing insurance company */
/*
DELETE FROM insurance_companies
WHERE NOT EXISTS (SELECT NULL
                    FROM insurance_bonds
                   WHERE insurance_bonds.company_id = insurance_companies.id);
*/

/* Add new insurance service */
INSERT INTO insurance_services (name, minimum_salary, length, issuer) VALUES ('Ice', 500, 1, 'K&H');

/* List insurance services */
SELECT name FROM insurance_services;

/* List insurance company services */
SELECT name, issuer, minimum_salary, length FROM insurance_services WHERE issuer LIKE '%era%';

/* Delete an existing insurance service */


/* Insure car */
INSERT INTO insurance_bonds (user_id, car_id, company_id, service_id, issued_date) VALUES (1, 1, 1, 1, '2019-01-01');

/* Lengthen insurance bond */
BEGIN;
    ROLLBACK;
    DELETE FROM insurance_bonds WHERE user_id=1 AND car_id=1 AND company_id=1 AND service_id=1;
    INSERT INTO insurance_bonds (user_id, car_id, company_id, service_id, issued_date) VALUES (1, 1, 1, 1, '2020-01-01');
COMMIT;

/* List invalid insurance bonds */
SELECT licence_plate FROM cars LEFT JOIN insurance_bonds ON cars.id = car_id WHERE insurance_bonds.issued_date < NOW();

/* List valid insurance bonds */
SELECT licence_plate FROM cars LEFT JOIN insurance_bonds ON cars.id = car_id WHERE insurance_bonds.issued_date >= NOW();

/* List number of insurance bonds issued by companies */
SELECT company_id, COUNT(company_id)
FROM insurance_bonds
INNER JOIN insurance_companies ON insurance_companies.id = insurance_bonds.company_id
GROUP BY company_id
ORDER BY COUNT(company_id) ASC;