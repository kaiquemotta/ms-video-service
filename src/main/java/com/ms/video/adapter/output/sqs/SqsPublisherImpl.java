package com.ms.video.adapter.output.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SqsPublisherImpl implements SqsPublisher {

    private final AmazonSQSAsync amazonSQS;
    private final ObjectMapper objectMapper;
    private final String queueUrl;

    public SqsPublisherImpl(AmazonSQSAsync amazonSQS,
                            ObjectMapper objectMapper,
                            @Value("${sqs.queue.url}") String queueUrl) {
        this.amazonSQS = amazonSQS;
        this.objectMapper = objectMapper;
        this.queueUrl = queueUrl;
    }

    @Override
    public void publish(Video video) {
        try {
            String messageBody = objectMapper.writeValueAsString(video);
            SendMessageRequest request = new SendMessageRequest(queueUrl, messageBody);
            amazonSQS.sendMessage(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish video to SQS", e);
        }
    }
}