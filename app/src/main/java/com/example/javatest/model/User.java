package com.example.javatest.model;

public class User {

    private int idUser;
    private String name;
    private String dob;
    private String gender;
    private String userName;
    private String password;
    private int role;

    public User() {
    }

    public User(int idUser, String name, String dob, String gender,
                String userName, String password, int role) {
        this.idUser = idUser;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String name, String dob, String gender,
                String userName, String password, int role) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    // Getter & Setter

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }
}