package org.reactive.service.aws.impl;

import org.reactive.service.aws.S3Service;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;

@Data
@Slf4j
@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;
}
