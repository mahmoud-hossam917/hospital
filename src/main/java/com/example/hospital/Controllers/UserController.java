package com.example.hospital.Controllers;

import com.example.hospital.Error.DaplicateException;
import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.Response;
import com.example.hospital.Models.ResponseWithListOfObject;
import com.example.hospital.Models.ResponseWithObject;
import com.example.hospital.Models.User;
import com.example.hospital.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("hospital")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("adduser")
    public ResponseEntity addUser(@RequestBody User user) {
        if (userService.AddUser(user)) {
            return ResponseEntity.ok(user);
        } else {
            throw new DaplicateException("User is already exits");
        }

    }

    @DeleteMapping("removeuser")
    public ResponseEntity removeUser(@RequestParam Long id) {
        if (userService.RemoveUser(id)) {

            Response response = new Response("Success", "Deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @PutMapping("updateuser")
    public ResponseEntity updateUser(@RequestBody User user) {

        if (userService.UpdateUser(user)) {

            ResponseWithObject response = new ResponseWithObject<User>("success", "User updated successfully", user);
            return ResponseEntity.ok(response);
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @GetMapping("getusers")
    public ResponseEntity getUsers() {
        List<User> users = userService.getAllUsers();
        ResponseWithListOfObject response = new ResponseWithListOfObject<User>("success", "Data retreived successfully", users);
        return ResponseEntity.ok(response);
    }

    @PutMapping("addroletouser")
    public ResponseEntity addRoleToUser(@RequestParam Long userID, @RequestParam Long roleID) {

        if (userService.addRoleToUser(userID, roleID)) {
            return ResponseEntity.ok("role added successfully");
        }
        throw new NotFoundException("There something wrong");
    }


}
