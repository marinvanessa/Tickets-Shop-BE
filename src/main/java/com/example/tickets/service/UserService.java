package com.example.tickets.service;

import com.example.tickets.model.User;
import com.example.tickets.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final @NonNull UserRepository repository;

    public User addUser(User user) {
        user.setId(UUID.randomUUID());

        return repository.save(user);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        if (repository.findByUsernameAndPassword(username, password).isEmpty()) {
            System.out.println("no user found");
        }

        return repository.findByUsernameAndPassword(username, password).get();
    }

    public void deleteUser(UUID userId) {
        if (repository.findById(userId).isEmpty()) {
            System.out.println("no user find");
        }
        repository.deleteById(userId);
    }
}
