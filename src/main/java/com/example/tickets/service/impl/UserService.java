package com.example.tickets.service.impl;

import com.example.tickets.enums.Role;
import com.example.tickets.model.User;
import com.example.tickets.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull UserRepository repository;

//    private final @NonNull PasswordEncoder passwordEncoder;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return repository.findByEmail(username).orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));
            }
        };
    }

//    public User addUser(User user) {
//        user.setId(UUID.randomUUID());
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        user.setRole(Role.NORMAL_USER);
//        return repository.save(user);
//    }
//

    public User getUserById(UUID uuid) {
        if (repository.findById(uuid).isPresent()) {
            return repository.findById(uuid).get();
        }
        return null;
    }


    public void deleteUser(UUID userId) {
        if (repository.findById(userId).isEmpty()) {
            System.out.println("no user find");
        }
        repository.deleteById(userId);
    }


}
