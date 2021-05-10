package io.github.ust.mico.createandreceive;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.ust.mico.createandreceive.kafka.MicoCloudEventImpl;
import io.github.ust.mico.createandreceive.messageprocessing.CloudEventManipulator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageGenerator {

  // @Autowired
  // CloudEventManipulator cloudEventManipulator;

  @Autowired
  private Sender sender;

  public MessageGenerator(Sender sender) {
    this.sender = sender;
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
    CloudEventManipulator cloudEventManipulator = new CloudEventManipulator();
    cloudEventManipulator.setMissingHeaderFields(cloudEvent, "");
    // Set return address.
    cloudEvent.setReturnTopic("car");
    log.info("Created msg: '{}'", cloudEvent);
    sender.send(cloudEvent);
  }
}
