package com.example.javatest.model;

public class Staff {

    private String id;
    private String name;
    private String birth;
    private String gender;

    public Staff(String id, String name, String birth, String gender) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getBirth() { return birth; }
    public String getGender() { return gender; }

    public void setName(String name) { this.name = name; }
    public void setBirth(String birth) { this.birth = birth; }
    public void setGender(String gender) { this.gender = gender; }
}
