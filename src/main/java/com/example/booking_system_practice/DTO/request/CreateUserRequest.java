package com.example.booking_system_practice.DTO.request;

public class CreateUserRequest {
    private String name;
    private String email;

    public String getName() { return name; }
    public String getEmail() { return email; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
