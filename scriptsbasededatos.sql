DROP DATABASE internship_management_db;
CREATE DATABASE internship_management_db;
USE internship_management_db;

-- User table (for login)
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_intern INT,
    username VARCHAR(50) NOT NULL UNIQUE,
    role ENUM('INTERN', 'SUPERVISOR') NOT NULL
);

-- Intern table
CREATE TABLE intern (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    academic_program VARCHAR(100) NOT NULL,
    entry_date DATE NOT NULL,
    practice_status ENUM('ACTIVE', 'FINISHED', 'PENDING') NOT NULL,
    supervisor_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES user(id)
);

-- Progress table
CREATE TABLE progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    registration_date DATE NOT NULL,
    feedback TEXT,
    intern_id INT,
    FOREIGN KEY (intern_id) REFERENCES intern(id)
);
