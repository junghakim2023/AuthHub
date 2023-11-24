package com.jha.authhub.service;

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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        if(true) {
            auth.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
            auth.add(new SimpleGrantedAuthority("USER"));
        }

        return new User("","",true,true,true,true,auth);
    }

}
