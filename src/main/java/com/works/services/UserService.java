package com.works.services;

import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.RoleRepository;
import com.works.repositories.UserRepository;
import com.works.utils.REnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements UserDetailsService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder encoder) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Optional<User> optionalUser = userRepository.findByEmailEqualsIgnoreCase(username);
        if (optionalUser.isPresent()){
            User vtUser = optionalUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    username,
                    vtUser.getPassword(),
                    vtUser.isEnabled(),
                    vtUser.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(vtUser.getRoles())

            );
        }else {
            throw new UsernameNotFoundException("This user is not found");
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities (List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority( role.getRoleName() ));
        }
        return authorities;
    }

    public ResponseEntity register (User user){
        Optional<User> optionalUser = userRepository.findByEmailEqualsIgnoreCase(user.getEmail());
        Map<REnum,Object> hm = new LinkedHashMap<>();
        if (optionalUser.isPresent()){
            //bu kullanıcı daha önce kayıtlı. Email vardır.
            //throw new UsernameNotFoundException("User Register Fail.");
            hm.put(REnum.status,false);
            hm.put(REnum.result,user);
            hm.put(REnum.message,"This user is alreadt registed.");
            return new ResponseEntity(hm,HttpStatus.BAD_REQUEST);
        }else {
            user.setPassword(encoder().encode(user.getPassword()));
            hm.put(REnum.status,true);
            hm.put(REnum.result,userRepository.save(user));
            return new ResponseEntity(user, HttpStatus.OK);
        }
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
