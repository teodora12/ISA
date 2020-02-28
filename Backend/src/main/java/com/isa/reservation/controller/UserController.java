package com.isa.reservation.controller;

import com.isa.reservation.dto.ActivateAccountDto;
import com.isa.reservation.dto.FindUserDto;
import com.isa.reservation.dto.RegisterUserDto;
import com.isa.reservation.model.User;
import com.isa.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping (value = "/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDto registerUserDto) throws InterruptedException {
        User user = userService.registerUser(registerUserDto);
        if(user == null){
            return ResponseEntity.notFound().build();
        }

        userService.sendMailForActivation(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping (value = "/register/activate")
    public User activateAccount(@RequestBody ActivateAccountDto activateAccountDto)  {
        User user = userService.activateAccount(activateAccountDto);
        return user;
    }


    @GetMapping(value = "/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);

        return user;
    }


    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        User savedUser = this.getUserByUsername(user.getUsername());
        if (savedUser == null) {
            return ResponseEntity.notFound().build();
        }
        //savedUser = this.userService.updateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }


    @GetMapping(value = "/findUser")
    //   @PreAuthorize("hasRole('USER')")
    public User findUser(@RequestBody FindUserDto findUserDto) {

        return this.userService.findByUsername(findUserDto.getUsername());
    }



}
