package com.nice.task.mc3.service;

import com.nice.task.mc3.dto.MessageDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private static final String MC1_URL = "http://localhost:8081/receive-message";
    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void sendMessageViaHttpPostRequest(MessageDTO messageDTO) {
        RequestEntity<MessageDTO> request = RequestEntity
                .post(MC1_URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(messageDTO);

        restTemplate.exchange(request, String.class);
    }

}
