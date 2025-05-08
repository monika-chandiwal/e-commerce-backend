package com.backend.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(unique = true, nullable = false)
    private String email;
    private String username;
        private String password;
    @Lob
    private Blob profilePic;

    public Users( String email, String username, String password, boolean toFactorAuthentication) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.toFactorAuthentication = toFactorAuthentication;
    }


    private boolean toFactorAuthentication;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






    public Users() {
    }

    public Users(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Blob getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Blob profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isToFactorAuthentication() {
        return toFactorAuthentication;
    }

    public void setToFactorAuthentication(boolean toFactorAuthentication) {
        this.toFactorAuthentication = toFactorAuthentication;
    }
}
