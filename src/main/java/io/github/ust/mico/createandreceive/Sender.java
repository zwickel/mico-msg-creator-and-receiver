package io.github.ust.mico.createandreceive;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import io.github.ust.mico.createandreceive.configuration.KafkaConfig;
import io.github.ust.mico.createandreceive.kafka.MicoCloudEventImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Sender {

  @Autowired
  private KafkaTemplate<String, MicoCloudEventImpl<JsonNode>> kafkaTemplate;

  // @Value("${kafka.output-topic}")
  // private String topic;

  @Autowired
  private KafkaConfig kafkaConfig;

  public void send(MicoCloudEventImpl<JsonNode> cloudEvent) {
    send(cloudEvent, kafkaConfig.getOutputTopic());
  }

  public void send(MicoCloudEventImpl<JsonNode> cloudEvent, String topic) {
    log.info("sending msg:'{}' to topic:'{}'", cloudEvent, topic);
    kafkaTemplate.send(topic, cloudEvent);
  }
}
