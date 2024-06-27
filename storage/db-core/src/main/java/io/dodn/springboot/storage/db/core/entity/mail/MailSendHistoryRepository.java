package io.dodn.springboot.storage.db.core.entity.mail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailSendHistoryRepository  extends JpaRepository<MailSendHistory,Long> {
}
