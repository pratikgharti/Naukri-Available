# Online Job Recruitment System

A Java-based Online Job Recruitment System developed using **Java OOP, MySQL, and JDBC**.

The system allows administrators, employers, and jobseekers to manage the complete job recruitment process.

## Features

### Admin

* Admin login
* Create new admin
* Add employees/employers
* View all employees
* Delete employees
* View all jobs
* Delete jobs

### Employee / Employer

* Employee login
* Create job postings
* View own jobs
* Update job details
* Delete jobs
* View applicants
* Accept applicants
* Reject applicants

### Jobseeker

* Jobseeker signup
* Jobseeker login
* View profile
* Update profile
* View available jobs
* Apply for jobs
* View submitted applications
* Check application status

## Application Status

The recruitment process works as follows:

```text
Jobseeker applies for a job
        ↓
Application stored in database
        ↓
Status = Pending
        ↓
Employer views applicants
        ↓
Employer accepts or rejects
        ↓
Status = Accepted / Rejected
        ↓
Jobseeker checks application status
```

## Technologies Used

* Java
* Java OOP
* MySQL
* JDBC
* MySQL Connector/J
* IntelliJ IDEA

## Database

The project uses a MySQL database named:

```text
job_recruitment
```

Main tables:

```text
admins
employees
jobseekers
jobs
applications
```

The complete database structure and sample data are included in:

```text
database/job_recruitment.sql
```

## Project Structure

```text
JobRecruitmentSystem
│
├── src
│   ├── main
│   │   └── Main.java
│   │
│   └── model
│       ├── DBConnection.java
│       ├── Admin.java
│       ├── Employee.java
│       ├── Jobseeker.java
│       ├── Job.java
│       └── JobApplication.java
│
├── database
│   └── job_recruitment.sql
│
├── README.md
└── .gitignore
```

## Setup

### 1. Clone the Repository

```bash
git clone YOUR_REPOSITORY_URL
```

Open the project in **IntelliJ IDEA**.

### 2. Create the Database

Open **MySQL Workbench** and import/run the SQL file:

```text
database/job_recruitment.sql
```

This file will create the `job_recruitment` database, required tables, and the included data.

### 3. Configure Database Connection

Open:

```text
src/model/DBConnection.java
```

Update the MySQL username and password according to your local MySQL setup.

Example:

```java
private static final String USER = "root";
private static final String PASSWORD = "YOUR_MYSQL_PASSWORD";
```

The database URL is:

```text
jdbc:mysql://localhost:3306/job_recruitment
```

### 4. Add MySQL Connector/J

Make sure **MySQL Connector/J** is added to the project libraries/classpath.

### 5. Run the Project

Run:

```text
src/main/Main.java
```

The system will display the main menu:

```text
1. Admin
2. Employee
3. Jobseeker
4. Exit
```

## Database Workflow

The system connects Java to MySQL using JDBC.

```text
Java Application
       ↓
     JDBC
       ↓
    MySQL
       ↓
job_recruitment Database
```

Job application workflow:

```text
Jobseeker
    ↓
Views Jobs
    ↓
Applies for Job
    ↓
Application Status = Pending
    ↓
Employer Views Applicant
    ↓
Accept / Reject
    ↓
Database Updated
    ↓
Jobseeker Checks Status
```

## Author

**Pratik Gharti**

Java OOP Project - Online Job Recruitment System
