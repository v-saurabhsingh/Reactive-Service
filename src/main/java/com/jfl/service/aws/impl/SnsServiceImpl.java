package com.jfl.service.aws.impl;

import com.jfl.service.aws.SnsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;

@Data
@Slf4j
@Service
public class SnsServiceImpl implements SnsService {

    private final SnsClient snsClient;
}
