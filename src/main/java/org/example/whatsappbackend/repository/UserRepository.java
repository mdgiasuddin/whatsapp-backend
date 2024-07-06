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

    @Query("select u from User  u where u.fullName like :query or u.email like :query")
    List<User> searchUser(String query);
}
