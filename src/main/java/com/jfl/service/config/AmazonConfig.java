package com.jfl.service.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sqs.SqsClient;

@Data
@Slf4j
@Configuration
public class AmazonConfig {

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider(@Value ("${cloud.aws.credentials.access-key}") String accessKey,
                                                         @Value ("${cloud.aws.credentials.secret-key}") String secretKey) {
        AwsCredentials awsCredentials=AwsBasicCredentials.create(accessKey,secretKey);
        return StaticCredentialsProvider.create(awsCredentials);
    }

    @Bean
    public S3Client s3Client(AwsCredentialsProvider awsCredentialsProvider) {
        return S3Client.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    public SnsClient snsClient(AwsCredentialsProvider awsCredentialsProvider) {
        return SnsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    public SqsClient sqsClient(AwsCredentialsProvider awsCredentialsProvider) {
        return SqsClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }

    @Bean
    public SesClient sesClient(AwsCredentialsProvider awsCredentialsProvider) {
        return SesClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }


    @Bean
    public SecretsManagerClient secretsManagerClient(AwsCredentialsProvider awsCredentialsProvider) {
        return SecretsManagerClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(awsCredentialsProvider)
                .build();
    }


}
