package io.github.ust.mico.createandreceive;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import io.github.ust.mico.createandreceive.kafka.MicoCloudEventImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sender {

  @Autowired
  SimpMessagingTemplate websocketsTemplate;

  @Autowired
  private KafkaTemplate<String, MicoCloudEventImpl<JsonNode>> kafkaTemplate;

  @Value("${kafka.output-topic}")
  private String topic;

  public void send(MicoCloudEventImpl<JsonNode> cloudEvent) {
    send(cloudEvent, topic);
  }

  public void send(MicoCloudEventImpl<JsonNode> cloudEvent, String topic) {
    websocketsTemplate.convertAndSend("/topic/messaging-bridge", cloudEvent);
    log.info("sending msg:'{}' to topic:'{}'", cloudEvent, topic);
    kafkaTemplate.send(topic, cloudEvent);
  }
}
