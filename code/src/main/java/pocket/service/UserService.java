package pocket.service;

import jakarta.transaction.Transactional;
import pocket.DTO.PocketUserResponse;
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
    public List<PocketUserResponse> getAllUsers(){
        return userRepository.findAll().stream().map(PocketUserResponse :: fromPocketUser).sorted().toList();
    }

    @Transactional
    public PocketUserResponse removeUser(long id){
        PocketUserResponse pocketUser = getUserById(id);
        return pocketUser;
    }

    @Transactional
    public PocketUserResponse getUserById(long id){
        return PocketUserResponse.fromPocketUser(userRepository.findById(id).orElseThrow(()->new PocketUserNotFoundException("Pocketuser with ID: "+id+" could not be found")));
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
