package com.example.hospital.Security;

import com.example.hospital.Models.MyUserDetails;
import com.example.hospital.Repositories.UserRepository;
import com.example.hospital.Services.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenUtilies {

    private long tokenValidation = 60400L;
    private String secretKey = "HRProject-api&&&&14";
    private final String prefix="Bearer ";

    @Autowired
    UserService userService;

    public String getJWTToken(MyUserDetails userDetails) {


        if(!userService.login(userDetails.getUsername(), userDetails.getPassword()))
            return  null;
        Map<String, Object> claims = new HashMap<String, Object>();

        claims.put("sub", userDetails.getUsername());
        claims.put("authorities",secretKey);
        claims.put("Created", new Date());

        String token;
        try {
            token = Jwts.builder().setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(getExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

            return token;
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
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token.replace(this.prefix,""))
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
