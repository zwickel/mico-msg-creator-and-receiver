package io.github.ust.mico.createandreceive.configuration;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * Configuration of the kafka connection.
 */
@Component
@Setter
@Getter
@ConfigurationProperties("kafka")
public class KafkaConfig {

    /**
     * The URL of the Kafka bootstrap server.
     */
    @NotBlank
    private String bootstrapServer;

    /**
     * The group id is a string that uniquely identifies the group of consumer
     * processes to which this consumer belongs.
     */
    @NotBlank
    private String groupId;

    /**
     * The Kafka input topic.
     */
    @NotBlank
    private String inputTopic;

    /**
     * The Kafka output topic.
     */
    @NotBlank
    private String outputTopic;

    /**
     * Used to report message processing errors
     */
    @NotBlank
    private String invalidMessageTopic;

    /**
     * Used to report routing errors
     */
    @NotBlank
    private String deadLetterTopic;

    @NotBlank
    private String testMessageOutputTopic;

}
