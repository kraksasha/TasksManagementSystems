package com.example.tasksmanagementsystems.Service;

import com.example.tasksmanagementsystems.Entity.Role;
import com.example.tasksmanagementsystems.Entity.Task;
import com.example.tasksmanagementsystems.Entity.User;
import com.example.tasksmanagementsystems.Repository.UserRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private User userNow;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        List<User> list = userRepository.findAll();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getEmail().equals(email)){
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        userNow = user;
        return new
                org.springframework.security.core.userdetails.User(user.getFirstName(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new
                SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void regNewUser(User user){
        userRepository.save(user);
    }

    public List<Task> getMyTasks(){
        User user = userRepository.findById(userNow.getId()).get();
        return user.getTasks();
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public boolean uniqueOrNotEmail(User user){
        List<User> list = userRepository.findAll();
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getEmail().equals(user.getEmail())){
                return false;
            }
        }
        return true;
    }
}
