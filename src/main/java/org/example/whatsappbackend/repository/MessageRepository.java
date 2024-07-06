package org.example.whatsappbackend.repository;

import org.example.whatsappbackend.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
