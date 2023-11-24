package com.jha.authhub.service;

import com.jha.authhub.model.UserEntity;
import com.jha.authhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findOneByName(userName).orElseThrow();
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        if(userEntity.getRole().equals("ADMIN")) {
            auth.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
            auth.add(new SimpleGrantedAuthority("USER"));
        }

        return new User(userEntity.getName(), userEntity.getPassword(), true, true, true, true, auth);
    }

}
