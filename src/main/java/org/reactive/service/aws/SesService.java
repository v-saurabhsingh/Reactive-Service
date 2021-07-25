package org.reactive.service.aws;

import org.reactive.service.aws.modal.EmailSendRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.SendRawEmailResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface SesService {

    CompletableFuture<SendEmailResponse> sendMail(EmailSendRequest emailSendRequest);

    CompletableFuture<SendRawEmailResponse> sendRawMail(EmailSendRequest emailSendRequest) throws MessagingException, IOException;
}
