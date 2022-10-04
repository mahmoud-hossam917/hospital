package com.example.hospital.Controllers;

import com.example.hospital.Error.DaplicateException;
import com.example.hospital.Error.NotFoundException;
import com.example.hospital.Models.*;
import com.example.hospital.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("hospital")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("getroles")
    public ResponseEntity<List<Roles>>  getRoles(){
        return ResponseEntity.ok( roleService.getRoles());
    }

    @PostMapping("addrole")
    public ResponseEntity addRole(@RequestBody Roles role){

        if(roleService.addRole(role)) {
            ResponseWithObject<Roles> response=
                    new ResponseWithObject<Roles>("success","Added role successfully" ,role);
            return ResponseEntity.ok(response);

        }
        throw  new DaplicateException("Role is already exist");

    }

    @PutMapping("updaterole")
    public ResponseEntity updateRole(@RequestBody Roles role){

        if(roleService.updateRole(role)){
            ResponseWithObject<Roles> response=
                    new ResponseWithObject<Roles>("success","Updated role successfully" ,role);
            return ResponseEntity.ok(response);
        }
        throw  new NotFoundException("The Role does not exist");
    }
    @DeleteMapping("removerole")
    public ResponseEntity removeRole(@RequestParam Long id){
        if(roleService.removeRole(id)){
            Response response=
                    new Response("success","Removed role successfully");
            return ResponseEntity.ok(response);
        }
        throw  new NotFoundException("The Role does not exist");
    }

    @GetMapping("get_users_has_role")
    public  ResponseEntity getUsersHasRole(@RequestParam Long id){
       Optional<Set<User>>users= Optional.ofNullable(roleService.getUsersHasRole(id));

       if(users.isPresent()){
         ResponseWithSetOfObject<User> response= new
                 ResponseWithSetOfObject<User>("success","Data Retrieved successfully" , users.get());
         return  ResponseEntity.ok(response);
       }
      throw  new NotFoundException("Role with "+id+" does not exist");
    }






}
