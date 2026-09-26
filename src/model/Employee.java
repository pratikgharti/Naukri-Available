package model;

public class Employee {

    private int employeeId;
    private String name;
    private String companyName;
    private String email;
    private String password;

    public Employee(int employeeId, String name, String companyName,
                    String email, String password) {

        this.employeeId = employeeId;
        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
    }

    public Employee(String name, String companyName,
                    String email, String password) {

        this.name = name;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}