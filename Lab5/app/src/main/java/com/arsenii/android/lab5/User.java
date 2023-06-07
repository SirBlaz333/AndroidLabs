package com.arsenii.android.lab5;

public class User {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String photo;

    public User(String firstName, String lastName, String email, String address, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.photo = photo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoto() {
        return photo;
    }
}
