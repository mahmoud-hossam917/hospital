package com.example.hospital.Models;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;


    private String userName;
    public String name ;
    public String age ;
    public String address ;
    public String email ;
    public String phoneNumber ;


    @ManyToMany
    @JoinTable(name="users_roles",
            joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))

    private Set<Roles> roles =new HashSet<Roles>();


}
