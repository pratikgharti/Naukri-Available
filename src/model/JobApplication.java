package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JobApplication {

    // Apply for a job
    public static void applyForJob(int jobId, int jobseekerId) {

        String sql = "INSERT INTO applications " +
                "(job_id, jobseeker_id, application_date, status) " +
                "VALUES (?, ?, CURDATE(), 'Pending')";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobId);
            statement.setInt(2, jobseekerId);

            statement.executeUpdate();

            System.out.println("Application submitted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Employer views applicants
    public static void viewApplicants(int jobId) {

        String sql = "SELECT applications.application_id, " +
                "jobseekers.name, " +
                "jobseekers.email, " +
                "applications.application_date, " +
                "applications.status " +
                "FROM applications " +
                "JOIN jobseekers " +
                "ON applications.jobseeker_id = jobseekers.jobseeker_id " +
                "WHERE applications.job_id = ?";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                System.out.println("Application ID: "
                        + result.getInt("application_id"));

                System.out.println("Name: "
                        + result.getString("name"));

                System.out.println("Email: "
                        + result.getString("email"));

                System.out.println("Date: "
                        + result.getDate("application_date"));

                System.out.println("Status: "
                        + result.getString("status"));

                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Employer accepts or rejects an application
    public static void updateStatus(int applicationId, String status) {

        String sql = "UPDATE applications " +
                "SET status = ? " +
                "WHERE application_id = ?";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, status);
            statement.setInt(2, applicationId);

            statement.executeUpdate();

            System.out.println("Application status updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Jobseeker checks their applications
    public static void viewMyApplications(int jobseekerId) {

        String sql = "SELECT jobs.job_title, " +
                "applications.application_date, " +
                "applications.status " +
                "FROM applications " +
                "JOIN jobs " +
                "ON applications.job_id = jobs.job_id " +
                "WHERE applications.jobseeker_id = ?";

        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setInt(1, jobseekerId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                System.out.println("Job: "
                        + result.getString("job_title"));

                System.out.println("Applied Date: "
                        + result.getDate("application_date"));

                System.out.println("Status: "
                        + result.getString("status"));

                System.out.println("----------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}