package com.example.hospital.Models;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data


public class TokenResponse {

    private String token;
    public TokenResponse() {
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenResponse(String token) {
        super();


        this.token = token;
    }



}
