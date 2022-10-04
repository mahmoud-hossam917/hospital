package com.example.hospital.Security;

import com.example.hospital.Models.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtilies {

    private long tokenValidation = 60400L;
    private String tokenSecret = "hospitalProject-api&&&&14";

    public String GenrateToken() {
        MyUserDetails userDetails= new MyUserDetails();
        Map<String, Object> claims = new HashMap<String, Object>();

        claims.put("sub", userDetails.getUsername());
        claims.put("Created", new Date());
        System.out.println("Before create token");
        String token;
        try {
            token = Jwts.builder().setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(getExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, tokenSecret).compact();
            System.out.println("After create token");
            return "Bearer "+ token;
        }catch (Exception e){
            System.out.println("Error creating token"+e.getMessage());
        }
        return null;
    }

    public Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenValidation * 1000);
    }

    private Claims GetClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(tokenSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
    private boolean IsTokenExpired(String token){
        Date date = GetClaims(token).getExpiration();
        return  date.before(new Date());
    }
    public String GetUsernameFromToken(String token){
        Claims claims = GetClaims(token);
        return claims.getSubject();
    }
    public boolean IsTokenInvalid(String token, UserDetails userDetails ) {
        Claims claims = GetClaims(token);
        return!IsTokenExpired(token) &&!userDetails.getUsername().equals(claims.getSubject());
    }

}
