package com.jfl.service.aws.impl;

import com.jfl.service.aws.SecretManagerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@Data
@Slf4j
@Service
public class SecretManagerServiceImpl implements SecretManagerService {

    private final SecretsManagerClient secretsManagerClient;

}
