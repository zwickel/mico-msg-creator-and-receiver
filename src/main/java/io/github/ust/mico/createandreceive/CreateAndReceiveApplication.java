package io.github.ust.mico.createandreceive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CreateAndReceiveApplication {

    @Autowired
    static Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(CreateAndReceiveApplication.class, args);

        new MessageGenerator(sender);
    }

}
