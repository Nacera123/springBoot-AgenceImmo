package com.agenceImmo.service;


import com.agenceImmo.model.User;
import com.agenceImmo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    public User add(User user){
      return   userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findUsersByEmailIgnoreCase(username);
        if (userDetails == null){
            throw  new UsernameNotFoundException("User not found");
        }

        return userDetails;
    }
}
