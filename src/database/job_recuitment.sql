CREATE DATABASE job_recruitment;
USE job_recruitment;

CREATE TABLE admins (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE employeees (
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE jobseekers (
    jobseeker_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(200)
);

CREATE TABLE jobs (
    job_id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    job_title VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    salary VARCHAR(50),
    location VARCHAR(100),

    FOREIGN KEY (employee_id)
    REFERENCES employees(employee_id)
    ON DELETE CASCADE
);

CREATE TABLE applications (
    application_id INT PRIMARY KEY AUTO_INCREMENT,
    job_id INT NOT NULL,
    jobseeker_id INT NOT NULL,
    application_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Pending',

    FOREIGN KEY (job_id)
    REFERENCES jobs(job_id)
    ON DELETE CASCADE,

    FOREIGN KEY (jobseeker_id)
    REFERENCES jobseekers(jobseeker_id)
    ON DELETE CASCADE
);

INSERT INTO admins (name, email, password)
VALUES ('Pratik', 'pratik00@gmail.com', 'pratik@11');

select *from employees;
select *from admins