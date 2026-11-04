package pocket.service;

import pocket.entity.PocketUser;
import org.springframework.stereotype.Service;
import pocket.exception.PocketUserNotFoundException;
import pocket.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<PocketUser> getAllUsers(){
        return userRepository.findAll();
    }

    public PocketUser removeUser(PocketUser pocketUser){
        userRepository.delete(pocketUser);
        return pocketUser;
    }

    public Optional<PocketUser> getUserById(long id){
        return userRepository.findById(id);
    }

    public PocketUser addUser(PocketUser pocketUser){
        userRepository.save(pocketUser);
        return pocketUser;
    }

    public PocketUser getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()->{
            return new PocketUserNotFoundException("Pocketuser with email: "+email+" could not be found");
        });
    }

}
