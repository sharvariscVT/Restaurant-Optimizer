DROP TABLE IF EXISTS TableBooking;

CREATE TABLE TableBooking
(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    first_name VARCHAR(32) NOT NULL,
    middle_name VARCHAR(32),
    last_name VARCHAR(32) NOT NULL,
    email VARCHAR(128) NOT NULL,
	booking_date DATE NOT NULL,	
	booking_time DATE NOT NULL,
	number_people INT(32) NOT NULL,
    PRIMARY KEY (id)
);