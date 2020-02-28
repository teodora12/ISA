package com.isa.reservation.service;

import com.isa.reservation.dto.ActivateAccountDto;
import com.isa.reservation.dto.RegisterUserDto;
import com.isa.reservation.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User registerUser (RegisterUserDto registerUserDto);
  //  User getUserByEmail(String email);
    User getUserById(Long id);
    List<User> getAllUsers();
    void sendMailForActivation(User user) throws InterruptedException;
    User activateAccount(ActivateAccountDto activateAccountDto);
    User getUserByUsername(String name);
    User findByUsername(String username);
    User updateUser(User user);


}
