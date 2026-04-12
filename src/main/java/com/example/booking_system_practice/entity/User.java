package com.example.booking_system_practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservationList = new ArrayList<>();

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
