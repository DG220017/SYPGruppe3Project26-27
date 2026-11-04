package pocket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PocketUser>> getAllUsers(){
         return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<PocketUser> addUser(@PathVariable PocketUser user){
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/removeUser")
    public ResponseEntity<PocketUser> removeUser(@PathVariable PocketUser user){
        return new ResponseEntity<>(userService.removeUser(user), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findUserbyId")
    public ResponseEntity<PocketUser> getUserById(@PathVariable Long id){
        try{
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        }catch (PocketUserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findUserbyEmail")
    public ResponseEntity<PocketUser> getUserByEmail(@PathVariable String email){
        try{
            return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
        }catch (PocketUserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
