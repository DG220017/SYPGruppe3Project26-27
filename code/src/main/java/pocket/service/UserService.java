package pocket.service;

import jakarta.transaction.Transactional;
import pocket.entity.PocketUser;
import org.springframework.stereotype.Service;
import pocket.exception.PocketUserNotFoundException;
import pocket.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<PocketUser> getAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public PocketUser removeUser(long id){
        PocketUser pocketUser = getUserById(id);
        userRepository.delete(pocketUser);
        return pocketUser;
    }

    @Transactional
    public PocketUser getUserById(long id){
        return userRepository.findById(id).orElseThrow(()->{
            return new PocketUserNotFoundException("Pocketuser with ID: "+id+" could not be found");
        });
    }

    @Transactional
    public PocketUser addUser(PocketUser pocketUser){
        userRepository.save(pocketUser);
        return pocketUser;
    }

    @Transactional
    public PocketUser getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(()->{
            return new PocketUserNotFoundException("Pocketuser with email: "+email+" could not be found");
        });
    }

}
