package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/vk-bot")
public class BotController {

    @Value("${vkbot.url}")
    private String url;

    @Value("${vkbot.token}")
    private String token;

    @Value("${vkbot.groupId}")
    private Long groupId;

    @Value("${vkbot.codeAnswerServer}")
    private String codeAnswerServer;

    @PostMapping
    public ResponseEntity<?> eventHandler(@RequestBody RequestData requestData) {
        System.out.println(requestData);

        if (requestData.getType().equals("confirmation") && requestData.getGroup_id().equals(groupId)) {
            return new ResponseEntity<>(codeAnswerServer, HttpStatus.OK);
        }

        if (requestData.getType().equals("message_new")) {

            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("access_token", token)
                    .queryParam("v", "5.131")
                    .queryParam("peer_id", requestData.getObject().getMessage().getFrom_id())
                    .queryParam("message", "Вы сказали: " + requestData.getObject().getMessage().getText())
                    .queryParam("user_id", requestData.getObject().getMessage().getFrom_id())
                    .queryParam("random_id", "0");

            HttpEntity<String> response = restTemplate.exchange(
                    URLDecoder.decode(builder.toUriString(), StandardCharsets.UTF_8),
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    String.class);

            System.out.println(response.getBody());
        }

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
