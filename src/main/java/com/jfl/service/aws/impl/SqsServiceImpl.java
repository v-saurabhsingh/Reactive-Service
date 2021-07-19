package com.jfl.service.aws.impl;

import com.jfl.service.aws.SqsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Data
@Slf4j
@Service
public class SqsServiceImpl implements SqsService {

    private final SqsClient sqsClient;
}
