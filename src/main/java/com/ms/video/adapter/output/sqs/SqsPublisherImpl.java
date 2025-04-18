package com.ms.video.adapter.output.sqs;

import com.ms.video.config.SqsProperties;
import com.ms.video.domain.model.Video;
import com.ms.video.port.output.SqsPublisher;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Component
public class SqsPublisherImpl implements SqsPublisher {

    private final SqsClient sqsClient;
    private final String queueUrl;

    public SqsPublisherImpl(SqsClient sqsClient, SqsProperties props) {
        this.sqsClient = sqsClient;
        this.queueUrl = props.getUrl();
    }

    @Override
    public void publish(Video video) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody("New video: " + video.getTitle())
                .build();

        sqsClient.sendMessage(request);
    }
}
