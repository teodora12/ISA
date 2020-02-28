package com.isa.reservation.service.impl;

import com.isa.reservation.dto.ActivateAccountDto;
import com.isa.reservation.dto.RegisterUserDto;
import com.isa.reservation.model.Authority;
import com.isa.reservation.model.Reservation;
import com.isa.reservation.model.User;
import com.isa.reservation.repository.AuthorityRepository;
import com.isa.reservation.repository.UserRepository;
import com.isa.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment env;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;


    public User getUserByUsername(String username) {
        User user =  this.userRepository.findUserByUsername(username);
        return user;
    }

    public User getUserById(Long id) {
        User user =  this.userRepository.findUserById(id);
        return user;
    }

    /*
    public User getUserByEmail(String email){
        User user = this.userRepository.findUserByEmail(email);
        return user;
    }
    */

    public User registerUser(RegisterUserDto registerUserDto) {

        boolean exist = false;
        List<User> allUsers = this.userRepository.findAll();
        for(User u: allUsers){
            if(u.getEmail().equals(registerUserDto.getEmail())){
                exist = true;
            }
        }
        if(exist == true){
            return null;
        }else {
            User user = new User();
            user.setName(registerUserDto.getName());
            user.setLastName(registerUserDto.getLastName());
            user.setEnabled(false);
            user.setCity(registerUserDto.getCity());
            user.setEmail(registerUserDto.getEmail());
            user.setUsername(registerUserDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
            user.setNumber(registerUserDto.getNumber());
            Authority authority = this.authorityRepository.findAuthorityByName("ROLE_USER");
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authority);
            user.setAuthorities(authorities);

            return this.userRepository.save(user);
        }
    }


    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void sendMailForActivation(User user) throws MailException, InterruptedException {

        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Aktivacija naloga");

        String url="http://localhost:4200/activate/" + user.getEmail();
        mail.setText("Vas nalog ce biti aktiviran klikom na sledeci link:" + " " + url);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Override
    public User activateAccount(ActivateAccountDto activateAccountDto){

        User user =  userRepository.findUserByUsername(activateAccountDto.getEmail());
        user.setEnabled(true);
        return this.userRepository.save(user);

    }

    @Override
    public User findByUsername(String username) {
        User u = userRepository.findUserByUsername(username);
        return u;
    }

    @Override
    public User updateUser(User user) {

        User savedUser = this.userRepository.findUserByUsername(user.getUsername());
        savedUser.setCity(user.getCity());
        savedUser.setEmail(user.getEmail());
        savedUser.setInChargeOf(user.getInChargeOf());
        savedUser.setLastName(user.getLastName());
        savedUser.setName(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(!savedUser.getPassword().equals(user.getPassword())) {
            savedUser.setPassword(user.getPassword());
        }

        savedUser.setNumber(user.getNumber());
        savedUser.setEnabled(user.isEnabled());

        savedUser = this.userRepository.save(savedUser);

        return savedUser;
    }


}
