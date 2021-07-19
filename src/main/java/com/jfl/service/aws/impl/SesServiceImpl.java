package com.jfl.service.aws.impl;

import com.jfl.service.aws.SesService;
import com.jfl.service.aws.modal.EmailSendRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

@Data
@Slf4j
@Service
public class SesServiceImpl implements SesService {

    private final SesClient sesClient;

    @Async
    @Override
    public CompletableFuture<SendEmailResponse> sendMail(EmailSendRequest emailSendRequest) {
        Body.Builder bodyBuilder=Body.builder();
        if(StringUtils.isNotEmpty(emailSendRequest.getTextData()))
            bodyBuilder.text(Content.builder().data(emailSendRequest.getTextData()).charset("UTF-8").build());
        if(StringUtils.isNotEmpty(emailSendRequest.getHtmlData()))
            bodyBuilder.html(Content.builder().data(emailSendRequest.getHtmlData()).charset("UTF-8").build());
        Message message=Message.builder()
                .subject(Content.builder().data(emailSendRequest.getSubject()).charset("UTF-8").build())
                .body(bodyBuilder.build())
                .build();
        SendEmailRequest sendEmailRequest=SendEmailRequest.builder()
                .source(emailSendRequest.getSource())
                .replyToAddresses(emailSendRequest.getReplyToAddresses())
                .destination(builder-> builder.toAddresses(emailSendRequest.getAddresses())
                        .ccAddresses(emailSendRequest.getCcAddresses())
                        .bccAddresses(emailSendRequest.getBccAddresses())
                        .build()).message(message)
                .build();
        SendEmailResponse response=sesClient.sendEmail(sendEmailRequest);
        log.info("Email sent...........");
        return CompletableFuture.completedFuture(response);
    }

    @Async
    @Override
    public CompletableFuture<SendRawEmailResponse> sendRawMail(EmailSendRequest emailSendRequest) throws MessagingException, IOException {
        // Create a multipart/alternative child container
        MimeMultipart msgBody = new MimeMultipart("alternative");
        // Create a wrapper for the HTML and text parts
        MimeBodyPart wrap = new MimeBodyPart();
        // Define the text part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(emailSendRequest.getTextData(), "text/plain; charset=UTF-8");
        // Define the HTML part
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(emailSendRequest.getHtmlData(), "text/html; charset=UTF-8");
        // Add the text and HTML parts to the child container
        msgBody.addBodyPart(textPart);
        msgBody.addBodyPart(htmlPart);
        // Add the child container to the wrapper object
        wrap.setContent(msgBody);
        // Create a multipart/mixed parent container
        MimeMultipart msg = new MimeMultipart("mixed");
        // Add the multipart/alternative part to the message
        msg.addBodyPart(wrap);

        // Define the attachment
        MimeBodyPart att = new MimeBodyPart();
        DataSource fds = new FileDataSource(emailSendRequest.getAttachment());
        att.setDataHandler(new DataHandler(fds));
        att.setFileName(fds.getName());
        // Add the attachment to the message.
        msg.addBodyPart(att);

        // Add the parent container to the message
        //Session session = Session.getDefaultInstance(new Properties());
        //MimeMessage message = new MimeMessage(session);
        //message.setContent(msg);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        msg.writeTo(outputStream);
        ByteBuffer buf = ByteBuffer.wrap(outputStream.toByteArray());
        byte[] arr = new byte[buf.remaining()];
        buf.get(arr);
        SdkBytes data = SdkBytes.fromByteArray(arr);
        RawMessage rawMessage= RawMessage.builder()
                .data(data)
                .build();
        SendRawEmailRequest sendRawEmailRequest=SendRawEmailRequest.builder()
                .source(emailSendRequest.getSource())
                .destinations(emailSendRequest.getAddresses())
                .rawMessage(rawMessage)
                .build();
        SendRawEmailResponse response=sesClient.sendRawEmail(sendRawEmailRequest);
        return CompletableFuture.completedFuture(response);
    }
}
