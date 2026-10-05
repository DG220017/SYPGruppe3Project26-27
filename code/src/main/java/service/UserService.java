package service;

import entity.Account;
import entity.User;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User removeUser(User user){
        userRepository.delete(user);
        return user;
    }

    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }




}
