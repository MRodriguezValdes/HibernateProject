package org.hibernate;


public class Student {

    private int id;

    private String name;

    private int age;
    private String lastname;
    private String email;
    private String phone;


    public Student() {

    }


    public Student(String name, String lastName, String email, String phone,int age) {
        this.setName(name);
        this.setLastname(lastName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setAge(age);
    }


    // Getters and Setters

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
