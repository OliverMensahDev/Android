package com.example.olivermensah.synccases.custom;

/**
 * Created by olivermensah on 12/3/17.
 */

public class User {

    private String name;
    private String email;
    private String phone;
    private String organization;
    private String age;
    private String id;


    public User(String name, String email, String phone, String organization, String age, String id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.organization = organization;
        this.age = age;
        this.id = id;
    }
    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", organization='" + organization + '\'' +
                ", age='" + age + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

