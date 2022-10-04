package com.example.hospital.Controllers;


import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.MyUserDetails;
import com.example.hospital.Models.Response;
import com.example.hospital.Models.SignInModel;
import com.example.hospital.Models.TokenResponse;
import com.example.hospital.Security.TokenUtilies;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    TokenUtilies tokenUtilies;

    @PostMapping("/login")
    public ResponseEntity<Object> Login(@RequestBody SignInModel signInModel ){

        MyUserDetails userDetails = new MyUserDetails(signInModel.getEmail(), signInModel.getPassword());
        String token=tokenUtilies.getJWTToken(userDetails);
        if(token==null){
            throw  new NotFoundException("Bad credentials");
        }
        TokenResponse response = new TokenResponse(token);
        return  ResponseEntity.ok(response);
    }
}
