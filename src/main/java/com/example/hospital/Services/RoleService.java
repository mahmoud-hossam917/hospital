package com.example.hospital.Services;

import com.example.hospital.Models.Roles;
import com.example.hospital.Models.User;
import com.example.hospital.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public boolean addRole(Roles role){
        if(isRoleExits(role.getName()))return false;

        roleRepository.save(role);
        return true;
    }
    public boolean isRoleExits(String name){

        Optional<Roles> role=roleRepository.findByName(name);
        return (role.isPresent());
    }
    public Roles getRole(Long id){
        Optional<Roles> role=roleRepository.findById(id);
        if(role.isPresent())return  role.get();
        return null;
    }
    public boolean updateRole(Roles role){
        if(getRole(role.getId())==null)return false;
        roleRepository.save(role);
        return true;

    }
    public boolean removeRole(Long id){
        Roles role =getRole(id);
        if(role==null)return false;
        roleRepository.delete(role);
        return true;
    }
    public List<Roles> getRoles(){
        return  roleRepository.findAll();
    }
    public Set<User> getUsersHasRole(Long id){
        Roles role=getRole(id);
        if(role==null)return  null;

        return  role.getUsers();
    }




}
