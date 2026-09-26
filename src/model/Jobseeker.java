package model;

public class Jobseeker {

    private int jobseekerId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

    public Jobseeker(int jobseekerId, String name, String email,
                     String password, String phone, String address) {

        this.jobseekerId = jobseekerId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Jobseeker(String name, String email, String password,
                     String phone, String address) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}