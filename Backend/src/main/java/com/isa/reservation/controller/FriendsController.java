package com.isa.reservation.controller;

import com.isa.reservation.dto.FriendshipDto;
import com.isa.reservation.model.Friends;
import com.isa.reservation.model.User;
import com.isa.reservation.service.FriendsService;
import com.isa.reservation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value="/api/friends")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Friends>> getFriendUsersByUsername(@PathVariable String username) {

        User user = this.userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        List<Friends> friends = this.friendsService.getFriendsByUser(user);
        return new ResponseEntity(friends, HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Friends>> getAllFriends() {

        List<Friends> friends = this.friendsService.getAll();
        if (friends == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(friends, HttpStatus.OK);
    }

    @GetMapping(value = "/accept/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Friends> acceptFriendship(@PathVariable Long id){

        Friends friends = this.friendsService.getFriendsById(id);
        if (friends == null) {
            return ResponseEntity.notFound().build();
        }
        friends.setAccepted(true);
        friends = this.friendsService.updateFriendship(friends);
        return new ResponseEntity(friends, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Friends> newFriendship(@RequestBody FriendshipDto friendsDto){

        Friends friends = this.friendsService.newFrendship(friendsDto);

        if (friends == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(friends, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Friends>> deleteFriendship(@PathVariable Long id){

        Friends friends = this.friendsService.getFriendsById(id);
        if (friends == null) {
            return ResponseEntity.notFound().build();
        }
        this.friendsService.deleteFriendship(id);

        return new ResponseEntity<List<Friends>>(this.friendsService.getAll(),HttpStatus.OK);
    }

    @GetMapping(value = "/search/{id}/{nameLastName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<User>> searchForNewFriends(@PathVariable Long id, @PathVariable String nameLastName){

        List<User> users = this.friendsService.searchForNewFriends(nameLastName, id);

        return new ResponseEntity(users, HttpStatus.OK);
    }
}
