package com.example.tickets.service;

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
                return  repository.findByEmail(username).orElseThrow(() ->
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
//    public User getUserByUsernameAndPassword(String username, String password) {
//        Optional<User> optionalUser = repository.findByUsername(username);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//
//            if (passwordEncoder.matches(password, user.getPassword())) {
//                return user;
//            } else {
//                System.out.println("Password does not match for the given user");
//                return null;
//            }
//
//        } else {
//            System.out.println("No user found with the given username and password");
//            return null;
//        }
//    }

//    public void deleteUser(UUID userId) {
//        if (repository.findById(userId).isEmpty()) {
//            System.out.println("no user find");
//        }
//        repository.deleteById(userId);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByUsername(username).orElse(null);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
//        );
//    }
}
