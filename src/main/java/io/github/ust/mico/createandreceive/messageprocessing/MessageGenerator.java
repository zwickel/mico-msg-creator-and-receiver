package io.github.ust.mico.createandreceive.messageprocessing;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import io.github.ust.mico.createandreceive.Sender;
import io.github.ust.mico.createandreceive.kafka.MicoCloudEventImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageGenerator {

  @Autowired
  private CloudEventManipulator cloudEventManipulator;

  @Autowired
  SimpMessagingTemplate websocketsTemplate;

  @Autowired
  private Sender sender;

  public MessageGenerator() {
    try {
      startProduction();
    } catch (InterruptedException ie) {
      // TODO: handle exception
    }
  }

  private void startProduction() throws InterruptedException {
    while (true) {
      this.produceMessage();
      Thread.sleep(5000);
    }
  }

  private void produceMessage() {
    MicoCloudEventImpl<JsonNode> cloudEvent = new MicoCloudEventImpl<JsonNode>();
    cloudEvent.setRandomId();
    cloudEvent.setBaseCloudEvent(cloudEvent);
    log.info("Created msg: '{}'", cloudEvent);
    cloudEventManipulator.setMissingHeaderFields(cloudEvent, "");
    log.info("Create msg: '{}'", cloudEvent);
    websocketsTemplate.convertAndSend("/topic/messaging-bridge", cloudEvent);
    sender.send(cloudEvent);
  }
}
