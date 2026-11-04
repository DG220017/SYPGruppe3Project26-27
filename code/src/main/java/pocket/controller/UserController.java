package pocket.controller;

import pocket.entity.PocketUser;

import org.springframework.web.bind.annotation.*;
import pocket.exception.PocketUserNotFoundException;
import pocket.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<PocketUser> getAllUsers(){
         return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public PocketUser addUser(@PathVariable PocketUser user){
        return userService.addUser(user);
    }

    @DeleteMapping("/removeUser")
    public PocketUser removeUser(@PathVariable PocketUser user){
        return userService.removeUser(user);
    }

    @GetMapping("/findUserbyId")
    public Optional<PocketUser> getUserById(@PathVariable Long id){
        try{
            return userService.getUserById(id);
        }catch (PocketUserNotFoundException e){
            // Response entity
        }
        return null;
    }

    @GetMapping("/findUserbyEmail")
    public PocketUser getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
}
