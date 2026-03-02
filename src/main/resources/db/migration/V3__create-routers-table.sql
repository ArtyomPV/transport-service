CREATE TABLE IF NOT EXISTS routes (
    id SERIAL NOT NULL PRIMARY KEY ,
    departure_point VARCHAR(150),
    destination_point VARCHAR(150),
    duration_minutes INT,
    carrier_id BIGINT,
    FOREIGN KEY (carrier_id) REFERENCES carriers(id)
);