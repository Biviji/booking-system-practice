package com.example.booking_system_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class User {

    private Long userId;
    private String userName;
    private String userEmail;

//    public User(Long userId, String userName, String userEmail) {
//        this.userId = userId;
//        this.userName = userName;
//        this.userEmail = userEmail;
//    }
//
//    public Long getUserId() { return userId; }
//    public String getUserName() { return userName; }
//    public String getUserEmail() { return userEmail; }
//    public void setUserId(Long userId) { this.userId = userId; }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(userId, user.getUserId());
//    }
//
//    @Override
//    public int hashCode() { return Objects.hash(userId); }
//
//    @Override
//    public String toString() {
//        return "\nUserID: " + userId + "\nUsername: " + userName + "\nEmail of the user: " + userEmail + "\n";
//    }
}
