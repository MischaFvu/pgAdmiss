package com.webapp.pgadmiss.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.webapp.pgadmiss.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID>, JpaSpecificationExecutor<Message> {

    Message findByMsgId(UUID msgId);
}
