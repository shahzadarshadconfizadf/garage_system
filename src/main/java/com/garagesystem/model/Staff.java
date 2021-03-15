package com.garagesystem.model;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_sequence")
    @SequenceGenerator(name = "staff_sequence", sequenceName = "staff_sequence", allocationSize = 2)
    @Column(name = "staff_id")
    private long id;

    @Column(name = "staff_first_name")
    private String firstName;

    @Column(name = "staff_last_name")
    private String lastName;

    @Column(name = "staff_ext_number")
    private String extNumber;

    @Column(name = "staff_comments")
    private String comments;

    @Column(name = "staff_username")
    private String userName;

    @Column(name = "staff_password")
    private String password;

    @Column(name = "staff_role")
    private String role;
    public Staff(){

    }

    public Staff(String firstName, String lastName, String extNumber, String comments, String userName, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.extNumber = extNumber;
        this.comments = comments;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getExtNumber() {
        return extNumber;
    }

    public void setExtNumber(String extNumber) {
        this.extNumber = extNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", extNumber='" + extNumber + '\'' +
                ", comments='" + comments + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
