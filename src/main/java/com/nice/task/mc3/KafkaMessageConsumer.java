package com.nice.task.mc3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nice.task.mc3.dto.MessageDTO;
import com.nice.task.mc3.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class KafkaMessageConsumer {

    @Autowired
    private RestService httpRequestService;

    /**
     * Subscribe to our kafka topic and listen for messages
     *
     * @param message incoming
     * @throws JsonProcessingException while parsing message body
     * @throws InterruptedException interrupted
     */
    @KafkaListener(topics = "messages", groupId = "group")
    public void listenMessages(String message) throws JsonProcessingException, InterruptedException {
        ObjectMapper om = new ObjectMapper();
        MessageDTO messageDTO = om.readValue(message, MessageDTO.class);

        messageDTO.setMC3_timestamp(Date.from(Instant.now()));
        Thread.sleep(1500);//simulate long running task (1.5 sec)
        httpRequestService.sendMessageViaHttpPostRequest(messageDTO);
    }

}
