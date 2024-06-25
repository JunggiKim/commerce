package io.dodn.springboot.storage.db.core.domain.mail;


import io.dodn.springboot.storage.db.core.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MailSendHistory extends BaseEntity {


    private String fromEmial;

    private String toEmail;

    private String subject;

    private String content;


    @Builder
    private MailSendHistory(Long id, String fromEmial, String toEmail, String subject, String content) {
        this.id = id;
        this.fromEmial = fromEmial;
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }




}
