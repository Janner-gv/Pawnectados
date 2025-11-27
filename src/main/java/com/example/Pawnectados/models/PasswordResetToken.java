package com.example.Pawnectados.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(nullable = false)
    private String email;

    private LocalDateTime expirationDate;

    public PasswordResetToken() {}

    public PasswordResetToken(String token, String email, LocalDateTime expirationDate){
        this.token = token;
        this.email = email;
        this.expirationDate = expirationDate;
    }
    public Long getId(){ return id;}
    public String getToken(){return token;}
    public String getEmail(){return email;}
    public LocalDateTime getExpirationDate(){return expirationDate;}

    public void setToken(String token) {this.token = token;}
    public void setEmail(String email) {this.email = email;}
    public void setExpirationDate(LocalDateTime expirationDate) {this.expirationDate = expirationDate;}
}
