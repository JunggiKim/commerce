package io.dodn.springboot.core.api.controller.v1.api.service.mail;

import io.dodn.springboot.core.domain.mail.MailService;
import io.dodn.springboot.storage.db.core.entity.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

//    @Mock
//    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;



    @DisplayName("메일 전송 테스트")
    @Test
    void testSample(){
        //  given
//        BDDMockito.given(mailSendClient.sendEmail(anyString(),anyString(),anyString(),anyString()))
//                .willReturn(true);
        //  when

//        boolean result = mailService.sendMail("", "", "", "");

        //  then

//        assertThat(result).isTrue();
//        Mockito.verify(mailSendHistoryRepository,Mockito.times(1)).save(any(MailSendHistory.class));

    }
}