package main.java.com.bdazure.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.bdazure.demo.exception.UserNotFoundException;
import main.java.com.bdazure.demo.model.User;
import main.java.com.bdazure.demo.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> opUser = userRepository.findById(id);

        if(opUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> opUser = userRepository.findById(id);

        if(opUser.isPresent()) {
            return opUser.get();
        } else {
            throw new UserNotFoundException("User not found");
        }
    }

    public User updateUser(Long id, User newUser) throws UserNotFoundException {
        Optional<User> opUser = userRepository.findById(id);

        if(opUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        User user = opUser.get();
        user.setName(newUser.getName());
        return userRepository.save(newUser);
    }
}
