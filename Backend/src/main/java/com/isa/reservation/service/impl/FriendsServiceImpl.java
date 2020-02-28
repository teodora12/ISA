package com.isa.reservation.service.impl;
import com.isa.reservation.dto.FriendshipDto;
import com.isa.reservation.model.Authority;
import com.isa.reservation.repository.AuthorityRepository;
import org.springframework.security.core.GrantedAuthority;
import com.isa.reservation.model.Friends;
import com.isa.reservation.model.User;
import com.isa.reservation.repository.FriendsRepository;
import com.isa.reservation.repository.UserRepository;
import com.isa.reservation.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FriendsServiceImpl implements FriendsService {


    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public List<Friends> getAll() {
        return this.friendsRepository.findAll();
    }

    @Override
    public List<Friends> getFriendsByUser(User user) {

        List<Friends> friends = this.friendsRepository.findAllByUser1OrUser2(user, user);

        return friends;
    }

    @Override
    public Friends getFriendsById(Long id) {
        return this.friendsRepository.findFriendsById(id);
    }

    @Override
    public Friends updateFriendship(Friends friends) {

        Friends savedFriendship = this.friendsRepository.findFriendsById(friends.getId());

        savedFriendship.setAccepted(friends.isAccepted());

        savedFriendship = this.friendsRepository.save(savedFriendship);

        return savedFriendship;
    }

    @Override
    public Friends newFrendship(FriendshipDto friendsDto) {
        Friends friends = new Friends();
        friends.setUser1(this.userRepository.findUserById(friendsDto.getUser1()));
        friends.setUser2(this.userRepository.findUserById(friendsDto.getUser2()));
        friends.setAccepted(false);
        return this.friendsRepository.save(friends);
    }

    @Override
    public void deleteFriendship(Long id) {

        this.friendsRepository.deleteById(id);

    }

    @Override
    public List<User> searchForNewFriends(String nameLastName, Long id) {

        String[] searchString = nameLastName.split(" ");
        Set<User> users = new HashSet<>();
        for (String s : searchString) {
            if (!s.equals("")) {
                users.addAll(this.userRepository.findUsersByNameLikeOrLastNameLikeOrNameContainsOrLastNameContains(s, s, s, s));
            }
        }
        User loggedUser = this.userRepository.findUserById(id);

        List<Friends> friendsOfLoggedUser = this.friendsRepository.findAllByUser1OrUser2(loggedUser, loggedUser);

        List<User> usersWhoAreNotFriends = new ArrayList<>();
        for (User u : users){
            boolean matches = true;
            boolean roleFound = false;
            for (int i = 0; i < u.getAuthorities().size(); i++) {
                if (u.getAuthorities().toArray()[i].equals(this.authorityRepository.findAuthorityByName("ROLE_USER"))) {
                    roleFound = true;
                    for (Friends f : friendsOfLoggedUser) {
                        if (u.getUsername().equals(f.getUser1().getUsername()) ||    // ako se nalazi medju prijateljima
                                u.getUsername().equals(f.getUser2().getUsername())) {
                            matches = false;
                            break;
                        }
                    }
                    break;                  // ako nadje tu rolu, ne trazi vise
                }
            }
            if (matches && roleFound) {
                usersWhoAreNotFriends.add(u);
            }
        }

        return usersWhoAreNotFriends;
    }


}
