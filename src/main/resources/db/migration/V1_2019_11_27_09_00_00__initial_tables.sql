create table parcel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parcel_number VARCHAR(50) NOT NULL,
    delivery_date VARCHAR(8) NOT NULL,
    delivery_state VARCHAR(20) NOT NULL,
    address_id INT
);

create table address (
    id INT AUTO_INCREMENT PRIMARY KEY,
    parcel INT REFERENCES parcel(id),
    street VARCHAR(250),
    zip VARCHAR(250),
    number VARCHAR(250),
    consignee VARCHAR(250)
);
