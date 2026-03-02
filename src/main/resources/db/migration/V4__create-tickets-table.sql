CREATE TABLE IF NOT EXISTS tickets (
  id SERIAL PRIMARY KEY,
  departure_date_time TIMESTAMP,
  seat_number INT,
  price NUMERIC(10,2),
  route_id BIGINT,
  FOREIGN KEY (route_id) REFERENCES routes (id)
);