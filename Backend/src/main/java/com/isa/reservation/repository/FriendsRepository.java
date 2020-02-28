package com.isa.reservation.repository;

import com.isa.reservation.model.Friends;
import com.isa.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

    Friends findFriendsById(Long id);
    List<Friends> findAllByUser1OrUser2(User user, User user1);

}
