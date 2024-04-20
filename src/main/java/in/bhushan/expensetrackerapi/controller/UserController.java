package in.bhushan.expensetrackerapi.controller;

import in.bhushan.expensetrackerapi.entity.User;
import in.bhushan.expensetrackerapi.entity.UserModel;
import in.bhushan.expensetrackerapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid  @RequestBody UserModel user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id){
        return new ResponseEntity<User>(userService.read(id),HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@RequestBody UserModel user,@PathVariable Long id){
        User mUser = userService.update(user,id);
        return new ResponseEntity<User>(mUser,HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
