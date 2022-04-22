-- создание таблицы покупателей
CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(30) NOT NULL ,
                          last_name VARCHAR(30) NOT NULL
);
-- создание таблицы продуктов
CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         product_name VARCHAR(30) UNIQUE NOT NULL ,
                         price NUMERIC(10,2) NOT NULL
);
-- создание таблицы покупок
CREATE TABLE purchase (
                          id SERIAL PRIMARY KEY,
                          customer_id SERIAL NOT NULL REFERENCES customer(id) ON DELETE CASCADE,
                          product_id SERIAL NOT NULL REFERENCES product(id),
                          purchases_date DATE NOT NULL
);