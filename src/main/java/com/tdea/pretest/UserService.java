package com.tdea.pretest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity findById(int id) {
        Optional<UserEntity> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            return findUser.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {
        Optional<UserEntity> findUser = userRepository.findByUsername(username);
        if (findUser.isPresent()) {
            return findUser.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    public boolean checkPassword(String username, String password) {
        UserEntity user = findByUsername(username);
        return UserUtils.isPasswordEqual(user.getPassword(), password);
    }

    public List<UserEntity> findALL() {
        List<UserEntity> users = userRepository.findAll();
        return users;
    }
}
