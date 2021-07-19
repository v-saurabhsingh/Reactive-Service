package com.jfl.service.aws.modal;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EmailSendRequest {

    private final String source;
    private final List<String> addresses;
    private final List<String> ccAddresses;
    private final List<String> bccAddresses;
    private final List<String> replyToAddresses;
    private final String subject;
    private final String textData;
    private final String htmlData;
    private final String attachment;


}
