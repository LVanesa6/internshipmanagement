DROP DATABASE IF EXISTS internship_management_db;
CREATE DATABASE internship_management_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE internship_management_db;

-- User table (for login)
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_intern INT,
    username VARCHAR(50) NOT NULL UNIQUE,
    role ENUM('INTERN', 'SUPERVISOR') NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Intern table
CREATE TABLE intern (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    academic_program VARCHAR(100) NOT NULL,
    entry_date DATE NOT NULL,
    practice_status ENUM('ACTIVE', 'FINISHED', 'PENDING') NOT NULL,
    supervisor_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES user(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Progress table
CREATE TABLE progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    registration_date DATE NOT NULL,
    feedback TEXT,
    intern_id INT,
    FOREIGN KEY (intern_id) REFERENCES intern(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO user (username, role) VALUES ('supervisor1', 'SUPERVISOR');
INSERT INTO user (username, role) VALUES ('supervisor2', 'SUPERVISOR');
INSERT INTO user (username, role) VALUES ('supervisor3', 'SUPERVISOR');
