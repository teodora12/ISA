INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Sjedinjene Drzave', 'Houston', 'Bellaire Boulevard', '3', 29.6989805, -95.6629681);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Novi Zeland', 'Auckland', 'Belgium Street', '5',-36.7959392, 175.0444923);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Australija', 'Running Creek', 'Kiewa Valley Highway', '11', -36.6845488, 147.13870659999998);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Austrija', 'Knittelfeld', 'Austriastraße', '23', 47.2127112, 14.832708300000036);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Srbija', 'Surcin Beograd', 'Стари аеродромски пут', '50', 44.8080941, 20.294172799999956);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Turska', 'Bakırköy Istanbul', 'Yeşilköy Halkalı Caddesi', '93',40.9658349, 28.797335799999928);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Francuska', 'Roissy-en-France', 'Dev de Roissy-en-France', '1', 49.0014595, 2.50757950000002);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Velika Britanija', 'Longford London', 'Southampton Road West', '1', 51.45918409999999, -0.4691341000000193);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Ujedinjeni Arapski Emirati', 'Abu Dhabi', 'International Airport Road', '1', 24.4018917, 54.595023699999956);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Nemacka', 'Frankfurt am Main', 'Hugo-Eckener-Ring', '1', 50.05316879999999, 8.578212699999995);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Francuska', 'Nice', 'Rue Costes et Bellonte', '1', 43.6675552, 7.210142000000019);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Sjedinjene Drzave', 'Queens County', 'JFK Access Road', '1', 40.64677579999999, -73.78171459999999);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Holandija', 'Haarlemmermeer', 'Evert van de Beekstraat', '202', 52.3052023, 4.752573699999971);

INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Srbija', 'Novi Sad', 'Novosadskog sajma', '35', 45.2543657, 19.827130399999987);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Spain', 'Madrid', 'Calle Amador de los Ríos', '3', 40.42707219999999, -3.6915054999999484);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Spain', 'Madrid', 'Cuesta de Santo Domingo', '5', 40.4196974, -3.7099964000000227);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('Spain', 'Madrid', 'Plaza de las Cortes', '10', 40.4156199, -3.6970400000000154);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('United States', 'New York', 'West 23rd Street', '204', 40.7439532, -73.99609240000001);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('United States', 'Secaucus', 'Harmon Meadow Boulevard', '455', 40.78905450000001, -74.04428719999999);
INSERT INTO address (country, city, street, address_number, latitude, longitude) VALUES ('United States', 'Hawthorne', 'Hindry Avenue', '14400', 33.90075999999999, -118.37149959999999);



INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Ataturk Airport', 'IST', 6);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Belgrade Nikola Tesla Airport', 'BEG', 5);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Paris Charles de Gaulle Airport', 'CDG', 7);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('London Heathrow', 'LHR', 8);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Abu Dhabi International Airport', 'AUH', 9);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Frankfurt am Main Airport', 'FRA', 10);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Nice Côte d Azur Airport', 'NCE', 11);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('John F. Kennedy International Airport', 'JFK', 12);
INSERT INTO destinations (name, short_name, address_address_id) VALUES ('Amsterdam Airport Schiphol', 'AMS', 13);

INSERT INTO airlines (name, description, address_address_id, number_of_rating,sum_rating,average_rating) VALUES ('RyanAir', 'RyanAir airline company', 1,20,90,4.5);
INSERT INTO airlines (name, description,  address_address_id, number_of_rating,sum_rating,average_rating) VALUES ('WizzAir', 'WizzAir airline company', 2,20,90,4.5);
INSERT INTO airlines (name, description,  address_address_id, number_of_rating,sum_rating,average_rating) VALUES ('AirSerbia', 'AirSerbia airline company', 3,20,90,4.5);
INSERT INTO airlines (name, description, address_address_id, number_of_rating,sum_rating,average_rating) VALUES ('Turkish Airlines', 'Turkish Airlines airline company',4,20,90,4.5);

INSERT INTO airplane_seat_arrangement (name, seat_rows, seat_columns) VALUES ('Airbus A330-200', 5, 2);
INSERT INTO airplane_seat_arrangement (name, seat_rows, seat_columns) VALUES ('Boeing 767-300 (763)', 3, 4);

INSERT INTO flights (duration, distance, flight_changes,airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (2, 1023, 1, 1,  '2019-03-02 12-30', '2019-03-02 14:45', 1, 'Each guest is allowed one piece of cabin baggage AND 1 laptop bag OR 1 handbag on-board. The main cabin baggage shall not exceed 56cm x 36cm x 23cm and does not weigh more than 7kg.', 'Enjoy more leg room and personal space and inflight dining featuring finest produce and premium wines', 220, 235,450,760,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (4, 3130, 0, 1, '2019-02-06 02-00', '2019-02-06 07:23', 2, 'Cabin baggage: 1 piece max Up to 7kg (15lb) + 1 small item such as handbag or thin/small laptop. Checked baggage: 1 piece max Up to 23kg (50lb) total', 'Includes an inflight meal full of fresh ingredients and flavours, a range of beverages and wines plus movies, music and more.', 320, 350, 620, 1230,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (1, 620, 0, 2, '2019-02-21 16-00', '2019-02-21 19:25', 1, 'Max. weight per bag: 23kg (50lb). Max. linear dimensions per bag: 158 cm (62in)','Complimentary meals with additional services varying, depending on flight times and routing.', 120, 145, 280, 560,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (1, 4020, 1, 2, '2019-02-13 16-40', '2019-02-13 18:25', 1, 'Max. weight per bag: 23kg (50lb). Max. linear dimensions per bag: 158 cm (62in)','Complimentary meals with additional services varying, depending on flight times and routing.', 450, 520, 890, 1420,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes,airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (2, 1023, 1, 3,  '2019-02-12 12-30', '2019-02-12 14:45', 1, 'Each guest is allowed one piece of cabin baggage AND 1 laptop bag OR 1 handbag on-board. The main cabin baggage shall not exceed 56cm x 36cm x 23cm and does not weigh more than 7kg.', 'Enjoy more leg room and personal space and inflight dining featuring finest produce and premium wines', 220, 235,450,760,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (4, 3130, 0, 4, '2019-02-17 02-00', '2019-02-17 07:23', 2, 'Cabin baggage: 1 piece max Up to 7kg (15lb) + 1 small item such as handbag or thin/small laptop. Checked baggage: 1 piece max Up to 23kg (50lb) total', 'Includes an inflight meal full of fresh ingredients and flavours, a range of beverages and wines plus movies, music and more.', 320, 350, 620, 1230,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (1, 620, 0, 3, '2019-02-22 16-00', '2019-02-22 19:25', 1, 'Max. weight per bag: 23kg (50lb). Max. linear dimensions per bag: 158 cm (62in)','Complimentary meals with additional services varying, depending on flight times and routing.', 120, 145, 280, 560,20,90,4.5);
INSERT INTO flights (duration, distance, flight_changes, airline_airline_id, departure_date_time, arrival_date_time, seat_arrangement_id, baggage_description, additional_services, economy_price, premium_economy_price, business_price, first_price, number_of_rating,sum_rating,average_rating) VALUES (1, 4020, 1, 4, '2019-03-19 16-40', '2019-03-19 18:25', 1, 'Max. weight per bag: 23kg (50lb). Max. linear dimensions per bag: 158 cm (62in)','Complimentary meals with additional services varying, depending on flight times and routing.', 450, 520, 890, 1420,20,90,4.5);

INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 2, 1 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 3, 1 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('connecting', 5, 1 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 1, 2 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 3, 2 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 3, 3 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 2, 3 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 3, 4 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 1, 4 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('connecting', 8, 4 );

INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 9, 5 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 8, 5 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('connecting', 3, 5 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 5, 6 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 2, 6 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 6, 7 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 9, 7 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('departure', 4, 8 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('arrival', 7, 8 );
INSERT INTO flight_destinations(description, destination_destination_id, flight_flight_id) VALUES ('connecting', 8, 8 );

INSERT INTO airline_destinations (airline, destination) VALUES (1, 1);
INSERT INTO airline_destinations (airline, destination) VALUES (1, 2);
INSERT INTO airline_destinations (airline, destination) VALUES (1, 3);
INSERT INTO airline_destinations (airline, destination) VALUES (1, 5);
INSERT INTO airline_destinations (airline, destination) VALUES (1, 7);
INSERT INTO airline_destinations (airline, destination) VALUES (1, 9);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 1);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 2);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 3);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 4);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 6);
INSERT INTO airline_destinations (airline, destination) VALUES (2, 8);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 9);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 8);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 7);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 3);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 6);
INSERT INTO airline_destinations (airline, destination) VALUES (3, 4);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 1);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 2);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 4);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 5);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 7);
INSERT INTO airline_destinations (airline, destination) VALUES (4, 8);


INSERT INTO  carServices (address_address_id,name,description,number_of_rating, sum_rating,average_rating) VALUES (5,'Sixt mydriver', 'Book a private car service in Europe. Sixt mydriver offers professional drivers and premium vehicles in Europe.',20,90,4.5);
INSERT INTO  carServices (address_address_id,name,description,number_of_rating, sum_rating,average_rating) VALUES (6,'Auto Europe', 'Auto Europe offers chauffeur services in most major cities throughout Europe',20,92,4.6);
INSERT INTO  carServices (address_address_id,name,description,number_of_rating, sum_rating,average_rating) VALUES (7,'Europcar Rent a Car', 'Europcar Rent a Car Serbia',20,88,4.4);

INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Citroen C4 Picasso','Citroen','C4 Picasso',true,20,5,1,2008,'car',20,90,4.5);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Volkswagen Passat','Volkswagen','Passat',false,25,5,3,2013,'car',20,88,4.4);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Toyota Aygo','Toyota','Aygo',false,35,6,1,2018,'van',20,86,4.3);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Peugeot 407','Peugeot','407',false,30,6,2,2012,'van',20,92,4.6);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Fiat Grande Punt','Fiat','Grande Punto',false,30,5,2,2012,'car',20,84,4.2);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Chevrolet Prisma','Chevrolet','Prisma',false,30,5,1,2012,'car',20,82,4.1);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Mazda 3','Mazda','3',false,30,5,1,2012,'car',20,94,4.7);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Renault Clio','Renault','Clio',false,30,5,1,2012,'car',20,90,4.5);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Nissan Micra','Nissan','Micra',false,30,5,1,2012,'car',20,88,4.4);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Volkswagen Passat','Volkswagen','Passat',false,30,5,1,2012,'car',20,86,4.3);
INSERT INTO cars (name,make,model,in_use,price,number_of_seats,car_service_car_service_id,year_of_manufacture,type,number_of_rating, sum_rating,average_rating) values ('Opel Corsa','Opel','Corsa',false,30,5,1,2012,'car',20,92,4.6);

INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-01-02 12-30', '2019-01-10 14:45',1,3,4);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-04-03 12-30', '2019-04-10 14:45',6,7,2);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-03-05 12-30', '2019-03-20 14:45',7,4,5);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-04-03 12-30', '2019-04-10 14:45',8,1,3);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-11-02 12-30', '2019-11-10 14:45',9,3,4);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-04-03 12-30', '2019-04-10 14:45',10,3,4);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-03-02 12-30', '2019-03-10 14:45',11,3,4);
INSERT INTO carreservations(pick_up_date, drop_off_date,car_car_id,drop_off_address_address_id, pick_up_address_address_id) values ('2019-03-28 12-30', '2019-04-04 14:45',9,3,4);


INSERT INTO affiliates (name, address_address_id, car_service_car_service_id)  values ('Affiliate Houston',1,1);
INSERT INTO affiliates (name, address_address_id, car_service_car_service_id)  values ('Affiliate Auckland',2,2);
INSERT INTO affiliates (name, address_address_id, car_service_car_service_id)  values ('Affiliate Running Creek',3,3);
INSERT INTO affiliates (name, address_address_id, car_service_car_service_id)  values ('Affiliate Knittelfeld',4,1);


INSERT INTO users (name, last_name, email,password, enabled, username) VALUES ('Milica','Matic','user@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user@yahoo.com' );
INSERT INTO users (name,email,password, enabled, username) VALUES ('Ana', 'admin@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'admin@yahoo.com' );
INSERT INTO users (name,email,password, enabled, username, in_charge_of) VALUES ('Nikolina', 'adminAirline@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminAirline@yahoo.com',1);
INSERT INTO users (name,email,password, enabled, username) VALUES ('Teodora', 'adminHotel@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminHotel@yahoo.com' );
INSERT INTO users (name,email,password, enabled, username,in_charge_of) VALUES ('Jovana', 'adminService1@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminService1@yahoo.com',1);
INSERT INTO users (name,last_name,email,password, enabled, username) VALUES ('Maja','Simic', 'user1@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user1@yahoo.com' );
INSERT INTO users (name,last_name,email,password, enabled, username) VALUES ('Nikola', 'Matic', 'user2@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user2@yahoo.com' );
INSERT INTO users (name,last_name,email,password, enabled, username) VALUES ('Kristina','Dondur', 'user3@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user3@yahoo.com' );
INSERT INTO users (name,last_name,email,password, enabled, username) VALUES ('Marko', 'Stevanovic', 'user4@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'user4@yahoo.com' );
INSERT INTO users (name,email,password, enabled, username, in_charge_of) VALUES ('Stefan', 'adminAirline1@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminAirline1@yahoo.com',2);
INSERT INTO users (name,email,password, enabled, username, in_charge_of) VALUES ('Danijela', 'adminAirline2@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminAirline2@yahoo.com',3);
INSERT INTO users (name,email,password, enabled, username,in_charge_of) VALUES ('Jelena', 'adminService2@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminService2@yahoo.com',2);
INSERT INTO users (name,email,password, enabled, username,in_charge_of) VALUES ('Milos', 'adminService3@yahoo.com','$2a$10$d2bYEem94Do7dck2CP14M.p4u3r2CPb7Di9uyrkxdDF0ibSbU5Bpy',true, 'adminService3@yahoo.com',3);



INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');
INSERT INTO authority (id, name) VALUES (3, 'ROLE_ADMIN_AIRLINE');
INSERT INTO authority (id, name) VALUES (4, 'ROLE_ADMIN_HOTEL');
INSERT INTO authority (id, name) VALUES (5, 'ROLE_ADMIN_SERVICE');

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (3, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (4, 4);
INSERT INTO user_authority (user_id, authority_id) VALUES (5, 5);
INSERT INTO user_authority (user_id, authority_id) VALUES (6, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (7, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (8, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (9, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (10, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (11, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (12, 5);
INSERT INTO user_authority (user_id, authority_id) VALUES (13, 5);


INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (6, 7, true);
INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (6, 9, true);
INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (9, 7, false);
INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (8, 6, false);
INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (1, 7, true);
INSERT INTO friends (user1_id, user2_id, is_accepted) VALUES (8, 1, false);



INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Hotel Park','Hotel Park Novi Sad',7.7,14);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('The Pavilions Madrid Hotel','',9.5,15);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Gran Meliá Palacio de los Duques','',9.8,16);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Chelsea Savoy Hotel','',9.4,18);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Courtyard by Marriott Secaucus Meadowlands','',9.2,19);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Ayres Hotel Manhattan','',9.6,20);
INSERT INTO hotels(name,description,average_rating,address_address_id) values ('Villa Real Hotel','',9.1,17);



insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-10 14:45','2019-01-15 14:45',4,4,125.0,1,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-20 14:45',4,4,225.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-15 14:45',4,4,255.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-15 14:45',4,4,199.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-15 14:45',4,4,215.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-15 14:45',4,4,235.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Guests room',1,4.5,'2019-01-15 14:45','2019-01-15 14:45',4,4,255.0,7,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,'2019-01-15 14:45','2019-01-15 14:45',6,6,250.0,1,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,'2019-01-15 14:45','2019-01-15 14:45',6,6,574.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,'2019-01-15 14:45','2019-01-15 14:45',6,6,679.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,'2019-01-15 14:45','2019-01-15 14:45',6,6,555.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,'2019-01-15 14:45','2019-01-15 14:45',6,6,454.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,null,null,6,6,524.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Family room',0,5.5,null,null,6,6,725.0,7,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,150.0,1,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,256.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,456.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,356.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,280.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,320.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Classic room',1,4.5,null,null,2,2,335.0,7,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Premium room',1,7.5,null,null,2,2,359.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Premium room',1,7.5,null,null,2,2,459.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Premium room',1,7.5,null,null,2,2,379.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Premium room',1,7.5,null,null,2,2,500.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe room',1,9.5,null,null,4,4,669.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe room',1,9.5,null,null,4,4,769.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe room',1,9.5,null,null,4,4,569.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe room',1,9.5,null,null,4,4,650.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe room',1,9.5,null,null,4,4,599.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Room, 1 King Bed with Sofa bed',1,8.0,null,null,4,4,275.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Room, 1 King Bed with Sofa bed',1,8.0,null,null,4,4,175.0,4,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Room, 1 King Bed with Sofa bed',1,8.0,null,null,4,4,320.0,5,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Room, 1 King Bed with Sofa bed',1,8.0,null,null,4,4,255.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Room, 1 King Bed with Sofa bed',1,8.0,null,null,4,4,235.0,7,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe King Studio Suite',1,9.5,null,null,1,1,152.0,1,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe King Studio Suite',1,9.5,null,null,1,1,260.0,2,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe King Studio Suite',1,9.5,null,null,1,1,300.0,3,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe King Studio Suite',1,9.5,null,null,1,1,285.0,6,null);
insert into rooms(name,is_available,average_rating,date_of_arrival,date_of_departure,max_number_of_guests,number_of_beds,price,hotel_hotel_id, room_reservation_room_reservation_id) values ('Deluxe King Studio Suite',1,9.5,null,null,1,1,270.0,7,null);

insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-01 14:45:00","2019-04-02 16:00:00",4,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-01 14:45:00","2019-04-02 16:00:00",6,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-01 14:45:00","2019-04-02 16:00:00",1,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-01 14:45:00","2019-04-02 16:00:00",3,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-01 14:45:00","2019-04-02 16:00:00",4,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-04 14:45:00","2019-04-05 16:00:00",4,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-03-03 14:45:00","2019-03-09 16:00:00",4,6);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-03-23 14:45:00","2019-03-24 16:00:00",1,1);
insert into roomreservations (arrival_date, departure_date, number_of_beds, number_of_nights) values ("2019-04-27 14:45:00","2019-04-29 16:00:00",2,2);


INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'reserved', 220, 185);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'taken', 220, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 220, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 235, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'free', 235, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'free', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'reserved', 760, 690);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 760, 0);


INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'reserved', 320, 275);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'free', 320, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,3,'free', 320, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',1,4,'free', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,1,'free', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',2,3,'free', 620, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',2,4,'reserved', 620, 540);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,1,'free', 620, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,2,'taken', 1230, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,3,'free', 1230, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,4,'free', 1230, 0);

INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'free', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'taken', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 145, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'taken', 145, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'reserved', 280, 195);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'reserved', 280, 210);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'free', 280, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'free', 560, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 560, 0);

INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'reserved', 450, 370);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 520, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'free', 520, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'taken', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'taken', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'free', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'free', 1420, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 1420, 0);


INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'reserved', 220, 185);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'taken', 220, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 220, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 235, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'free', 235, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'free', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'reserved', 760, 690);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 760, 0);


INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'reserved', 320, 275);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'free', 320, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,3,'free', 320, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',1,4,'free', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,1,'free', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 350, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',2,3,'free', 620, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',2,4,'reserved', 620, 540);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,1,'free', 620, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,2,'taken', 1230, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,3,'free', 1230, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',3,4,'free', 1230, 0);

INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'free', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'taken', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 120, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 145, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'taken', 145, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'reserved', 280, 195);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'reserved', 280, 210);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'free', 280, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'free', 560, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 560, 0);

INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,1,'taken', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',1,2,'reserved', 450, 370);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('ECONOMY',2,1,'free', 450, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',2,2,'taken', 520, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('PREMIUM_ECONOMY',3,1,'free', 520, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',3,2,'taken', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,1,'taken', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('BUSINESS',4,2,'free', 890, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,1,'free', 1420, 0);
INSERT INTO seat (seat_class, seat_row, seat_column, state, price, discount_price) VALUES ('FIRST',5,2,'free', 1420, 0);


INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 1);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 2);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 3);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 4);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 5);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 6);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 7);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 8);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 9);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (1, 10);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 11);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 12);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 13);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 14);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 15);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 16);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 17);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 18);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 19);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 20);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 21);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (2, 22);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 23);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 24);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 25);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 26);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 27);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 28);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 29);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 30);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 31);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (3, 32);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 33);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 34);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 35);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 36);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 37);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 38);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 39);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 40);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 41);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (4, 42);


INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 43);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 44);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 45);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 46);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 47);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 48);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 49);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 50);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 51);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (5, 52);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 53);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 54);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 55);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 56);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 57);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 58);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 59);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 60);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 61);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 62);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 63);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (6, 64);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 65);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 66);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 67);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 68);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 69);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 70);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 71);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 72);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 73);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (7, 74);

INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 75);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 76);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 77);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 78);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 79);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 80);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 81);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 82);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 83);
INSERT INTO flights_seats (flights_flight_id, seats_seat_id) VALUES (8, 84);


INSERT INTO flight_reservation (flight_id, user_id) VALUES (1, 1);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (1, 6);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (1, 9);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (2, 1);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (2, 6);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (3, 7);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (3, 8);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (4, 7);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (4, 8);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (4, 9);

INSERT INTO flight_reservation (flight_id, user_id) VALUES (5, 1);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (5, 6);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (5, 9);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (6, 1);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (6, 6);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (7, 7);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (7, 8);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (8, 7);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (8, 8);
INSERT INTO flight_reservation (flight_id, user_id) VALUES (8, 9);

INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (1, 'Matic', 'Milica', '322442', 2, 1);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (0, 'Savic', 'Ana', '56674', 4, 2);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (6, 'Simic', 'Maja', '322442', 7, 2);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (9, 'Stevanovic', 'Marko', '322442', 8, 3);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (1, 'Matic', 'Milica', '322442', 16, 4);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (6, 'Simic', 'Maja', '322442', 20, 5);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (0, 'Katic', 'Milica', '322442', 24, 6);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (7, 'Matic', 'Nikola', '322442', 26, 6);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (8, 'Dondur', 'Kristina', '322442', 27, 7);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (7, 'Matic', 'Nikola', '322442', 33, 8);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (8, 'Dondur', 'Kristina', '322442', 36, 9);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (9, 'Stevanovic', 'Marko', '322442', 38, 10);


INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (1, 'Matic', 'Milica', '322442', 44, 11);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (0, 'Savic', 'Ana', '56674', 48, 12);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (6, 'Simic', 'Maja', '322442', 49, 12);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (9, 'Stevanovic', 'Marko', '322442', 50, 13);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (1, 'Matic', 'Milica', '322442', 58, 14);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (6, 'Simic', 'Maja', '322442', 62, 15);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (0, 'Katic', 'Milica', '322442', 66, 16);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (7, 'Matic', 'Nikola', '322442', 68, 16);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (8, 'Dondur', 'Kristina', '322442', 71, 17);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (7, 'Matic', 'Nikola', '322442', 75, 18);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (8, 'Dondur', 'Kristina', '322442', 78, 19);
INSERT INTO passenger_on_flight_seat (passenger_id, passenger_last_name, passenger_name, passenger_passport, seat_seat_id, flight_reservation) VALUES (9, 'Stevanovic', 'Marko', '322442', 80, 20);

INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (1,1, 1,1,'2019-02-10 10-30');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (2,6, 2,2, '2019-02-12 12-32');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (3,7, 6,3, '2019-02-11 14-10');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (4,8, 7,4, '2019-02-12 18-05');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (5,9, 3,5, '2019-02-09 15-52');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (6,1, 4,6, '2019-01-27 12-30');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (7,6, 5,7, '2019-02-08 16-20');
INSERT INTO reservations(car_reservation_car_reservation_id, user_id, flight_reservation_flight_reservation_id, room_reservation_room_reservation_id, date_created) values (8,7, 8,8, '2019-02-08 11-49');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (8, 9, '2019-02-12 08-25');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (9, 10, '2019-02-12 22-15');

INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (1, 11, '2019-02-10 15-30');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (6, 12, '2019-02-19 12-32');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (7, 16, '2019-02-11 04-10');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (8, 17, '2019-02-12 07-05');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (9, 13, '2019-02-12 19-52');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (1, 14, '2019-01-27 20-30');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (6, 15, '2019-02-12 11-20');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (7, 18, '2019-02-08 11-49');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (8, 19, '2019-02-12 08-25');
INSERT INTO reservations(user_id, flight_reservation_flight_reservation_id, date_created) values (9, 20, '2019-02-12 22-15');

