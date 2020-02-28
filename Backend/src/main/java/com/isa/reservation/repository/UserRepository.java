package com.isa.reservation.repository;

import com.isa.reservation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll ();
  //  User findUserByEmail(String email);
    User findUserById(Long id);
    User findUserByUsername(String username);
    User findUserByName(String name);
    Set<User> findUsersByNameLikeOrLastNameLikeOrNameContainsOrLastNameContains(String name, String lastName,
                                                                                String name1, String lastName1);

}
