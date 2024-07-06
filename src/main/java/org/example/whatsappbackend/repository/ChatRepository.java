package org.example.whatsappbackend.repository;

import org.example.whatsappbackend.model.entity.Chat;
import org.example.whatsappbackend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    @Query("select c from Chat c inner join c.participants u where u = :user")
    List<Chat> findChatByUser(@Param("user") User user);

    @Query("select c from Chat c where c.groupChat = false and :user1 member of c.participants and :user2 member of c.participants")
    Chat findSingleChatByUsers(@Param("user1") User user1, @Param("user2") User user2);
}
