package com.example.hospital.Services;

import com.example.hospital.Models.Roles;
import com.example.hospital.Models.User;
import com.example.hospital.Repositories.UserRepository;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    public User getUser(String email , String password){

        Optional<User> user = userRepository.findUsersByEmailAndPassword(email,password);
        if(user.isPresent()){
            return  user.get();
        }else{
            return  null;
        }
    }
    public User isUserExist(String email){
        Optional<User>user =userRepository.findUsersByEmail(email);

        if(user.isPresent()){
            return  user.get();
        }else return  null;
    }
    public User findUserById(Long id){
        Optional<User>user = userRepository.findById(id);
        if(user.isPresent()){
            return  user.get();
        }else return  null;
    }
    public boolean AddUser(User user){
        if(isUserExist(user.getEmail())==null){
            userRepository.save(user);
            return  true;
        }else return false;
    }
    public boolean RemoveUser(Long id){
        User user=findUserById(id);
        if(user!=null){
            userRepository.delete(user);
            return  true;
        }else return false;
    }
    public boolean UpdateUser(User user){
        User isExits=findUserById(user.getId());
        if(isExits!=null){
            userRepository.save(user);
            return  true;
        }else return false;
    }
    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
    public boolean addRoleToUser(Long userID ,Long roleID ){
        User user = findUserById(userID);
        Roles role=roleService.getRole(roleID);

        if(user==null||role==null)return false;
        user.getRoles().add(role);
        role.getUsers().add(user);
        userRepository.save(user);
        return  true;
    }
    public boolean login(String username, String password){
        Optional<User> user= userRepository.findByEmailAndPassword(username, password);
        return  user.isPresent();
    }
}
