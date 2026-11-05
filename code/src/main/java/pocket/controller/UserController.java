package pocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pocket.DTO.PocketUserResponse;
import pocket.entity.PocketUser;

import org.springframework.web.bind.annotation.*;
import pocket.exception.PocketUserNotFoundException;
import pocket.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PocketUserResponse>> getAllUsers(){
         return new ResponseEntity<>(userService.getAllUsers().stream().map(PocketUserResponse::fromPocketUser).toList(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<PocketUserResponse> addUser(@RequestBody PocketUser user){
        return new ResponseEntity<>(PocketUserResponse.fromPocketUser(userService.addUser(user)),HttpStatus.CREATED);
    }

    @DeleteMapping("/removeUser/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable long id){
        try{
            userService.removeUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (PocketUserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findUserbyId/{id}")
    public ResponseEntity<PocketUserResponse> getUserById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(PocketUserResponse.fromPocketUser(userService.getUserById(id)), HttpStatus.OK);
        }catch (PocketUserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findUserbyEmail{email}")
    public ResponseEntity<PocketUserResponse> getUserByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(PocketUserResponse.fromPocketUser(userService.getUserByEmail(email)),HttpStatus.OK);
        }catch (PocketUserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Für JUnit Test zwecke
    public UserService getUserService() {
        return userService;
    }
}
