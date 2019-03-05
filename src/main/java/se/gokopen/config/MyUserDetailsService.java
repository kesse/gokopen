package se.gokopen.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import se.gokopen.persistence.entity.UserEntity;
import se.gokopen.persistence.repository.UserRepository;

@Service
public class MyUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Name "+username +"Not Found");
        }
        return new User(user.getUsername(), passwordEncoder().encode(user.getPassword()), getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(UserEntity user){

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        if(user.getRole().equalsIgnoreCase("ROLE_ADMIN")){
            grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
        return grantedAuthority;
    }
}
