USE `VWLab`;

CREATE TABLE IF NOT EXISTS part_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    car_model_id VARCHAR(255) NOT NULL,
    component_name VARCHAR(255) NOT NULL,
    component_id VARCHAR(255) NOT NULL,
    unit_in_stock INT
    );

INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('AU1','ENGINE','EA1','60');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('AU1','BRAKEPAD','BA1','70');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('AU2','ENGINE','EA2','40');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('AU2','BRAKEPAD','BA2','30');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('BE1','ENGINE','EB1','60');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('BE1','BRAKEPAD','BB1','55');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('BE2','ENGINE','EB2','65');
INSERT INTO part_details(car_model_id,component_name,component_id,unit_in_stock) VALUES ('BE2','BRAKEPAD','BB2','95');