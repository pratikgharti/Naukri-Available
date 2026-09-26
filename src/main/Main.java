
        package main;

import model.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Check database connection
        Connection connection = DBConnection.getConnection();

        if (connection == null) {
            System.out.println("Database connection failed!");
            return;
        }


        System.out.println("     Welcome to, NAUKRI AVAILABLE");

        while (true) {

            System.out.println();
            System.out.println("------------- MAIN MENU -------------");
            System.out.println("1. Admin");
            System.out.println("2. Employeer");
            System.out.println("3. Jobseeker");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------");

            int choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    adminLogin();
                    break;

                case 2:
                    employeeLogin();
                    break;

                case 3:
                    jobseekerMenu();
                    break;

                case 4:
                    System.out.println("Thank you for using the system!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    public static void adminLogin() {

        System.out.println();
        System.out.println("========== ADMIN LOGIN ==========");

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        String sql = "SELECT * FROM admins WHERE email = ? AND password = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println();
                System.out.println("Login successful!");
                System.out.println("Welcome, " + result.getString("name"));

                int adminId = result.getInt("admin_id");

                adminMenu(adminId);

            } else {

                System.out.println("Invalid email or password!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // ADMIN MENU
    // =========================================================

    public static void adminMenu(int adminId) {

        while (true) {

            System.out.println();
            System.out.println("============= ADMIN MENU =============");
            System.out.println("1. Create New Admin");
            System.out.println("2. Add Employer");
            System.out.println("3. View All Employers");
            System.out.println("4. Delete Employer");
            System.out.println("5. View All Jobs");
            System.out.println("6. Delete Job");
            System.out.println("7. Logout");
            System.out.println("--------------------------------------");

            int choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    createAdmin();
                    break;

                case 2:
                    addEmployee();
                    break;

                case 3:
                    viewAllEmployees();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    viewAllJobs();
                    break;

                case 6:
                    deleteJob();
                    break;

                case 7:
                    System.out.println("Admin logged out.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // =========================================================
    // CREATE ADMIN
    // =========================================================

    public static void createAdmin() {

        System.out.println();
        System.out.println("========== CREATE ADMIN ==========");

        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        String sql =
                "INSERT INTO admins (name, email, password) VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);

            statement.executeUpdate();

            System.out.println("Admin created successfully!");

        } catch (Exception e) {

            System.out.println("Could not create admin.");
            e.printStackTrace();
        }
    }


    // =========================================================
    // ADD EMPLOYEE
    // =========================================================

    public static void addEmployee() {

        System.out.println();
        System.out.println("========== ADD EMPLOYEE ==========");

        scanner.nextLine();

        System.out.print("Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Company Name: ");
        String companyName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        String sql =
                "INSERT INTO employees " +
                        "(name, company_name, email, password) " +
                        "VALUES (?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, companyName);
            statement.setString(3, email);
            statement.setString(4, password);

            statement.executeUpdate();

            System.out.println("Employer added successfully!");

        } catch (Exception e) {

            System.out.println("Could not add employer.");
            e.printStackTrace();
        }
    }


    // =========================================================
    // VIEW ALL EMPLOYEES
    // =========================================================

    public static void viewAllEmployees() {

        System.out.println();
        System.out.println("========== ALL EMPLOYEES ==========");

        String sql = "SELECT * FROM employees";

        try {

            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("----------------------------------");
                System.out.println("Employee ID: "
                        + result.getInt("employee_id"));

                System.out.println("Name: "
                        + result.getString("name"));

                System.out.println("Company: "
                        + result.getString("company_name"));

                System.out.println("Email: "
                        + result.getString("email"));
            }

            if (!found) {
                System.out.println("No employers found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // DELETE EMPLOYEE
    // =========================================================

    public static void deleteEmployee() {

        int employeeId = getInt("Enter Employer ID to delete: ");

        String sql =
                "DELETE FROM employees WHERE employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employer deleted successfully!");
            } else {
                System.out.println("Employer not found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // VIEW ALL JOBS
    // =========================================================

    public static void viewAllJobs() {

        System.out.println();
        System.out.println("=============== ALL JOBS ===============");

        String sql =
                "SELECT jobs.job_id, jobs.job_title, " +
                        "jobs.description, jobs.salary, jobs.location, " +
                        "employees.company_name " +
                        "FROM jobs " +
                        "JOIN employees " +
                        "ON jobs.employee_id = employees.employee_id";

        try {

            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("----------------------------------------");

                System.out.println("Job ID: "
                        + result.getInt("job_id"));

                System.out.println("Job Title: "
                        + result.getString("job_title"));

                System.out.println("Company: "
                        + result.getString("company_name"));

                System.out.println("Description: "
                        + result.getString("description"));

                System.out.println("Salary: "
                        + result.getString("salary"));

                System.out.println("Location: "
                        + result.getString("location"));
            }

            if (!found) {
                System.out.println("No jobs available.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // DELETE JOB
    // =========================================================

    public static void deleteJob() {

        int jobId = getInt("Enter Job ID to delete: ");

        String sql =
                "DELETE FROM jobs WHERE job_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Job deleted successfully!");
            } else {
                System.out.println("Job not found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // EMPLOYEE LOGIN
    // =========================================================

    public static void employeeLogin() {

        System.out.println();
        System.out.println("========= EMPLOYEE LOGIN =========");

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        String sql =
                "SELECT * FROM employees WHERE email = ? AND password = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                int employeeId =
                        result.getInt("employee_id");

                String employeeName =
                        result.getString("name");

                System.out.println();
                System.out.println("Login successful!");
                System.out.println("Welcome, " + employeeName);

                employeeMenu(employeeId);

            } else {

                System.out.println("Invalid email or password!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // EMPLOYEE MENU
    // =========================================================

    public static void employeeMenu(int employeeId) {

        while (true) {

            System.out.println();
            System.out.println("=========== EMPLOYEE MENU ===========");
            System.out.println("1. Create New Job");
            System.out.println("2. View My Jobs");
            System.out.println("3. Update Job");
            System.out.println("4. Delete Job");
            System.out.println("5. View Applicants");
            System.out.println("6. Accept Applicant");
            System.out.println("7. Reject Applicant");
            System.out.println("8. Logout");
            System.out.println("-------------------------------------");

            int choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    createJob(employeeId);
                    break;

                case 2:
                    viewMyJobs(employeeId);
                    break;

                case 3:
                    updateJob(employeeId);
                    break;

                case 4:
                    deleteMyJob(employeeId);
                    break;

                case 5:
                    viewApplicants(employeeId);
                    break;

                case 6:
                    updateApplicationStatus(employeeId, "Accepted");
                    break;

                case 7:
                    updateApplicationStatus(employeeId, "Rejected");
                    break;

                case 8:
                    System.out.println("Employee logged out.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // =========================================================
    // CREATE JOB
    // =========================================================

    public static void createJob(int employeeId) {

        System.out.println();
        System.out.println("=========== CREATE JOB ===========");

        scanner.nextLine();

        System.out.print("Job Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Salary: ");
        String salary = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        String sql =
                "INSERT INTO jobs " +
                        "(employee_id, job_title, description, salary, location) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employeeId);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, salary);
            statement.setString(5, location);

            statement.executeUpdate();

            System.out.println("Job created successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // VIEW EMPLOYEE'S JOBS
    // =========================================================

    public static void viewMyJobs(int employeeId) {

        String sql =
                "SELECT * FROM jobs WHERE employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employeeId);

            ResultSet result = statement.executeQuery();

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("----------------------------------");

                System.out.println("Job ID: "
                        + result.getInt("job_id"));

                System.out.println("Title: "
                        + result.getString("job_title"));

                System.out.println("Description: "
                        + result.getString("description"));

                System.out.println("Salary: "
                        + result.getString("salary"));

                System.out.println("Location: "
                        + result.getString("location"));
            }

            if (!found) {
                System.out.println("You have not posted any jobs.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // UPDATE JOB
    // =========================================================

    public static void updateJob(int employeeId) {

        int jobId = getInt("Enter Job ID to update: ");

        scanner.nextLine();

        System.out.print("New Job Title: ");
        String title = scanner.nextLine();

        System.out.print("New Description: ");
        String description = scanner.nextLine();

        System.out.print("New Salary: ");
        String salary = scanner.nextLine();

        System.out.print("New Location: ");
        String location = scanner.nextLine();

        String sql =
                "UPDATE jobs SET job_title = ?, " +
                        "description = ?, salary = ?, location = ? " +
                        "WHERE job_id = ? AND employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, salary);
            statement.setString(4, location);
            statement.setInt(5, jobId);
            statement.setInt(6, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Job updated successfully!");
            } else {
                System.out.println("Job not found or you do not own this job.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // DELETE EMPLOYEE'S OWN JOB
    // =========================================================

    public static void deleteMyJob(int employeeId) {

        int jobId = getInt("Enter Job ID to delete: ");

        String sql =
                "DELETE FROM jobs " +
                        "WHERE job_id = ? AND employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobId);
            statement.setInt(2, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Job deleted successfully!");
            } else {
                System.out.println("Job not found or you do not own this job.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // VIEW APPLICANTS
    // =========================================================

    public static void viewApplicants(int employeeId) {

        String sql =
                "SELECT applications.application_id, " +
                        "jobs.job_title, " +
                        "jobseekers.name, " +
                        "jobseekers.email, " +
                        "applications.application_date, " +
                        "applications.status " +
                        "FROM applications " +
                        "JOIN jobs ON applications.job_id = jobs.job_id " +
                        "JOIN jobseekers " +
                        "ON applications.jobseeker_id = jobseekers.jobseeker_id " +
                        "WHERE jobs.employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, employeeId);

            ResultSet result = statement.executeQuery();

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("----------------------------------------");

                System.out.println("Application ID: "
                        + result.getInt("application_id"));

                System.out.println("Job: "
                        + result.getString("job_title"));

                System.out.println("Applicant: "
                        + result.getString("name"));

                System.out.println("Email: "
                        + result.getString("email"));

                System.out.println("Applied Date: "
                        + result.getDate("application_date"));

                System.out.println("Status: "
                        + result.getString("status"));
            }

            if (!found) {
                System.out.println("No applicants found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // ACCEPT / REJECT APPLICANT
    // =========================================================

    public static void updateApplicationStatus(
            int employeeId,
            String status) {

        int applicationId =
                getInt("Enter Application ID: ");

        String sql =
                "UPDATE applications " +
                        "JOIN jobs ON applications.job_id = jobs.job_id " +
                        "SET applications.status = ? " +
                        "WHERE applications.application_id = ? " +
                        "AND jobs.employee_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, status);
            statement.setInt(2, applicationId);
            statement.setInt(3, employeeId);

            int rows = statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Application status changed to " + status + "!");

            } else {

                System.out.println(
                        "Application not found or you do not own this job.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // JOBSEEKER MENU
    // =========================================================

    public static void jobseekerMenu() {

        while (true) {

            System.out.println();
            System.out.println("========= JOBSEEKER MENU =========");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Back");
            System.out.println("----------------------------------");

            int choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    jobseekerSignup();
                    break;

                case 2:
                    jobseekerLogin();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // =========================================================
    // JOBSEEKER SIGNUP
    // =========================================================

    public static void jobseekerSignup() {

        System.out.println();
        System.out.println("========= JOBSEEKER SIGNUP =========");

        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        String sql =
                "INSERT INTO jobseekers " +
                        "(name, email, password, phone, address) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, phone);
            statement.setString(5, address);

            statement.executeUpdate();

            System.out.println("Signup successful!");

        } catch (Exception e) {

            System.out.println("Signup failed.");
            e.printStackTrace();
        }
    }


    // =========================================================
    // JOBSEEKER LOGIN
    // =========================================================

    public static void jobseekerLogin() {

        System.out.println();
        System.out.println("========= JOBSEEKER LOGIN =========");

        System.out.print("Email: ");
        String email = scanner.next();

        System.out.print("Password: ");
        String password = scanner.next();

        String sql =
                "SELECT * FROM jobseekers " +
                        "WHERE email = ? AND password = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                int jobseekerId =
                        result.getInt("jobseeker_id");

                System.out.println();
                System.out.println("Login successful!");

                jobseekerDashboard(jobseekerId);

            } else {

                System.out.println("Invalid email or password!");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // JOBSEEKER DASHBOARD
    // =========================================================

    public static void jobseekerDashboard(int jobseekerId) {

        while (true) {

            System.out.println();
            System.out.println("========= JOBSEEKER DASHBOARD =========");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View All Jobs");
            System.out.println("4. Apply for Job");
            System.out.println("5. View My Applications");
            System.out.println("6. Logout");
            System.out.println("---------------------------------------");

            int choice = getInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    viewProfile(jobseekerId);
                    break;

                case 2:
                    updateProfile(jobseekerId);
                    break;

                case 3:
                    viewAllJobs();
                    break;

                case 4:
                    applyForJob(jobseekerId);
                    break;

                case 5:
                    viewMyApplications(jobseekerId);
                    break;

                case 6:
                    System.out.println("Jobseeker logged out.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    // =========================================================
    // VIEW PROFILE
    // =========================================================

    public static void viewProfile(int jobseekerId) {

        String sql =
                "SELECT * FROM jobseekers WHERE jobseeker_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobseekerId);

            ResultSet result = statement.executeQuery();

            if (result.next()) {

                System.out.println();
                System.out.println("========== MY PROFILE ==========");

                System.out.println("ID: "
                        + result.getInt("jobseeker_id"));

                System.out.println("Name: "
                        + result.getString("name"));

                System.out.println("Email: "
                        + result.getString("email"));

                System.out.println("Phone: "
                        + result.getString("phone"));

                System.out.println("Address: "
                        + result.getString("address"));
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // UPDATE PROFILE
    // =========================================================

    public static void updateProfile(int jobseekerId) {

        System.out.println();
        System.out.println("========== UPDATE PROFILE ==========");

        scanner.nextLine();

        System.out.print("New Name: ");
        String name = scanner.nextLine();

        System.out.print("New Phone: ");
        String phone = scanner.nextLine();

        System.out.print("New Address: ");
        String address = scanner.nextLine();

        String sql =
                "UPDATE jobseekers " +
                        "SET name = ?, phone = ?, address = ? " +
                        "WHERE jobseeker_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, name);
            statement.setString(2, phone);
            statement.setString(3, address);
            statement.setInt(4, jobseekerId);

            statement.executeUpdate();

            System.out.println("Profile updated successfully!");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // APPLY FOR JOB
    // =========================================================

    public static void applyForJob(int jobseekerId) {

        int jobId =
                getInt("Enter Job ID you want to apply for: ");

        // Check if job exists
        String checkJob =
                "SELECT * FROM jobs WHERE job_id = ?";

        // Check duplicate application
        String checkApplication =
                "SELECT * FROM applications " +
                        "WHERE job_id = ? AND jobseeker_id = ?";

        String insert =
                "INSERT INTO applications " +
                        "(job_id, jobseeker_id, application_date, status) " +
                        "VALUES (?, ?, CURDATE(), 'Pending')";

        try {

            Connection connection = DBConnection.getConnection();

            // Check job
            PreparedStatement jobStatement =
                    connection.prepareStatement(checkJob);

            jobStatement.setInt(1, jobId);

            ResultSet jobResult =
                    jobStatement.executeQuery();

            if (!jobResult.next()) {

                System.out.println("Job does not exist.");
                return;
            }

            // Check duplicate application
            PreparedStatement applicationStatement =
                    connection.prepareStatement(checkApplication);

            applicationStatement.setInt(1, jobId);
            applicationStatement.setInt(2, jobseekerId);

            ResultSet applicationResult =
                    applicationStatement.executeQuery();

            if (applicationResult.next()) {

                System.out.println(
                        "You have already applied for this job.");

                return;
            }

            // Insert application
            PreparedStatement insertStatement =
                    connection.prepareStatement(insert);

            insertStatement.setInt(1, jobId);
            insertStatement.setInt(2, jobseekerId);

            insertStatement.executeUpdate();

            System.out.println(
                    "Application submitted successfully!");

            System.out.println(
                    "Application status: Pending");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // VIEW MY APPLICATIONS
    // =========================================================

    public static void viewMyApplications(int jobseekerId) {

        System.out.println();
        System.out.println("========= MY APPLICATIONS =========");

        String sql =
                "SELECT applications.application_id, " +
                        "jobs.job_title, " +
                        "employees.company_name, " +
                        "applications.application_date, " +
                        "applications.status " +
                        "FROM applications " +
                        "JOIN jobs " +
                        "ON applications.job_id = jobs.job_id " +
                        "JOIN employees " +
                        "ON jobs.employee_id = employees.employee_id " +
                        "WHERE applications.jobseeker_id = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobseekerId);

            ResultSet result = statement.executeQuery();

            boolean found = false;

            while (result.next()) {

                found = true;

                System.out.println("---------------------------------------");

                System.out.println("Application ID: "
                        + result.getInt("application_id"));

                System.out.println("Job: "
                        + result.getString("job_title"));

                System.out.println("Company: "
                        + result.getString("company_name"));

                System.out.println("Applied Date: "
                        + result.getDate("application_date"));

                System.out.println("Status: "
                        + result.getString("status"));
            }

            if (!found) {

                System.out.println(
                        "You have not applied for any jobs.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // =========================================================
    // INTEGER INPUT
    // =========================================================

    public static int getInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.next());

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number.");
            }
        }
    }
}
