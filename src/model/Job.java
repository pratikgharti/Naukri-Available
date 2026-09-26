package model;

public class Job {

    private int jobId;
    private int employeeId;
    private String jobTitle;
    private String description;
    private String salary;
    private String location;

    public Job(int jobId, int employeeId, String jobTitle,
               String description, String salary, String location) {

        this.jobId = jobId;
        this.employeeId = employeeId;
        this.jobTitle = jobTitle;
        this.description = description;
        this.salary = salary;
        this.location = location;
    }

    public Job(int employeeId, String jobTitle,
               String description, String salary, String location) {

        this.employeeId = employeeId;
        this.jobTitle = jobTitle;
        this.description = description;
        this.salary = salary;
        this.location = location;
    }

    public int getJobId() {
        return jobId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }
}