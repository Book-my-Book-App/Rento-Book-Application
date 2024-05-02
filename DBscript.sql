CREATE DATABASE db_LibraryManagementSystem;
USE db_LibraryManagementSystem;

CREATE TABLE user(
user_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
user_name CHAR(30) NOT NULL,
user_email VARCHAR (30) NOT NULL,
address VARCHAR(200) NOT NULL,
user_phoneNo VARCHAR(13) NOT NULL,
noOfIssuedBooks INT
);


CREATE TABLE admin(
admin_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
admin_name char(30) NOT NULL,
admin_email VARCHAR (30) NOT NULL,
admin_phoneNo VARCHAR(20) NOT NULL
);


CREATE TABLE login(
login_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
password VARCHAR(100) NOT NULL,
admin_id INT,
user_id INT, 
FOREIGN KEY(admin_id) REFERENCES admin(admin_id),
FOREIGN KEY(user_id) REFERENCES user(user_id)
);


CREATE TABLE bookCategory(
book_category_id INT PRIMARY KEY NOT NULL,
book_category_name VARCHAR(20) NOT NULL,
book_description VARCHAR(200) NOT NULL 
);

CREATE TABLE eventCategory(
event_category_id INT PRIMARY KEY NOT NULL,
event_category_name VARCHAR(20) NOT NULL,
event_description VARCHAR(200) NOT NULL 
);


CREATE TABLE books(
isbn VARCHAR(13) PRIMARY KEY NOT NULL, 
author char(30) NOT NULL,
title char(30) NOT NULL,
Book_copies INT NOT NULL,
noOfBooks INT DEFAULT 1,
book_category_id INT,
FOREIGN KEY(book_category_id) REFERENCES bookCategory(book_category_id)
);


CREATE TABLE reviews(
    user_id INT,
    book_id VARCHAR(13),
    rating INT,
    review VARCHAR(100), 
    FOREIGN KEY(user_id) REFERENCES user(user_id),
    FOREIGN KEY(book_id) REFERENCES books(isbn)
);


CREATE TABLE event(
event_id INT PRIMARY KEY NOT NULL,
event_date VARCHAR(30) NOT NULL,
event_name char(30) NOT NULL,
event_address VARCHAR(50) NOT NULL,
event_category_id INT,
user_id INT,
FOREIGN KEY (user_id) REFERENCES user(user_id), 
FOREIGN KEY(event_category_id) REFERENCES eventCategory(event_category_id)
);

CREATE TABLE checkouts(
utr_no INT PRIMARY KEY NOT NULL,
book_id VARCHAR(13) NOT NULL,
user_id INT NOT NULL,
checkout_date DATE NOT NULL,
due_date DATE NOT NULL,
return_date DATE,
FOREIGN KEY (book_id) REFERENCES books(isbn),
FOREIGN KEY (user_id) REFERENCES user(user_id)
); 