INSERT INTO users (first_name, last_name, email, phone_number, password, birth_date, country, street, house_no_flat_no,
                   postcode, city)
VALUES ('Adam', 'Kowalski', 'adamkowalski@gmail.com', '48123456789', 'password', '1970-01-01', 'Polska', 'Złota',
        '12/24', '12-345', 'Warszawa'),
       ('Kasia', 'Iksińska', 'kasiaiksinska@vp.pl', '48987654321', 'pass', '1990-10-27', 'Polska', 'Połczyńska',
        '1/307', '98-765', 'Warszawa');

INSERT INTO user_company_details (user_id, company_name, nip_number, country, street, house_no_flat_no, postcode, city)
VALUES (1, 'Najlepsza firma', '1234567890', 'Polska', 'Warszawska', '201/87', '33-444', 'Warszawa');

INSERT INTO user_wallet (user_id, currency, value)
VALUES (1, 'PLN', 200.0),
       (2, 'PLN', 20.0);

INSERT INTO user_driving_license (user_id, photo)
VALUES (1, 'C:\\Users\\adama\\IdeaProjects\\car-sharing-app\\driving_license\\user_1_dl'),
       (2, 'C:\\Users\\adama\\IdeaProjects\\car-sharing-app\\driving_license\\user_2_dl');

INSERT INTO vehicle_types (name, driving_price, stop_over_price, distance_price)
VALUES ('Economy', 0.50, 0.20, 0.80),
       ('Comfort', 0.80, 0.20, 0.80),
       ('Premium', 1.00, 0.20, 1.00);

INSERT INTO vehicle_models
    (type_id, brand, model)
VALUES (1, 'Fiat', '500'),
       (2, 'Toyota', 'Corolla Hybrid'),
       (3, 'Mercedes', 'GLA');

INSERT INTO vehicles (model_id, registration, max_fuel, current_fuel, max_range,
                      current_range, latitude, longitude)
VALUES (1, 'AA12345', 30.0, 15.0, 500.0, 250.0, '52.241729', '20.919107'),
       (2, 'BB12345', 35.0, 15.0, 600.0, 342.0, '52.241571', '20.917374'),
       (3, 'CC12345', 40.0, 35.0, 600.0, 72.0, '52.242576', '20.920786');

INSERT INTO rentals_history
(user_id, vehicle_id, rent_date, return_date, driving_time, driving_price, distance, distance_price, stop_over_time,
 stop_over_price, total_price, is_active)
VALUES (1, 1, '2020-11-15 15:00:00', '2020-11-15 17:00:00', '01:50:00', 55.0, 10.0, 8.0, '00:10:00', 2.0, 65.0, 0),
       (2, 2, '2020-11-15 13:00:00', '2020-11-15 15:00:00', '01:40:00', 80.0, 20.0, 16.0, '00:20:00', 4.0, 100.0, 0),
       (1, 3, '2020-11-15 18:00:00', '2020-11-15 20:00:00', '01:30:00', 100.0, 30.0, 30.0, '00:30:00', 6.0, 136, 0);

INSERT INTO promotions
    (name, code, description)
VALUES ('Tanszy kilometr', 'taniejzakm', 'Teraz za każdy kilometr zapłacisz 20% taniej'),
       ('Pierwszy przejazd 50% taniej', 'pierwszyraztaniej',
        'Za pierwszy przejazd naszym samochodem płacisz tylko 50% ceny');
