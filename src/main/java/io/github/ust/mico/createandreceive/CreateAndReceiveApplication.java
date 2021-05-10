package io.github.ust.mico.createandreceive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.ust.mico.createandreceive.messageprocessing.MessageGenerator;

@SpringBootApplication
public class CreateAndReceiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreateAndReceiveApplication.class, args);
        new MessageGenerator();
    }

}
