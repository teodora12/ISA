package com.isa.reservation.service;

import com.isa.reservation.dto.FriendshipDto;
import com.isa.reservation.model.Friends;
import com.isa.reservation.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface FriendsService {

    List<Friends> getAll();
    List<Friends> getFriendsByUser(User user);
    Friends getFriendsById(Long id);
    Friends updateFriendship(Friends friends);
    Friends newFrendship(FriendshipDto friends);
    void deleteFriendship(Long id);
    List<User> searchForNewFriends(String nameLastName, Long id);        // trazi user-e koji mu nisu prijatelji a odgovaraju

}
