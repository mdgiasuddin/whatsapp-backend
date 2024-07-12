package org.example.whatsappbackend.repository;

import org.example.whatsappbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    List<User> findByIdIn(List<Integer> ids);

    boolean existsByEmail(String email);

    @Query("select u from User  u where u.id <> :reqUserId and (u.fullName ilike :query or u.email ilike :query)")
    List<User> searchUser(Integer reqUserId, String query);
}
