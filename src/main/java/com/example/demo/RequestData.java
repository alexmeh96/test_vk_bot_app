package com.example.demo;

import lombok.Data;

@Data
public class RequestData {
    private String type;
    private Long group_id;
    private String event_id;
    private ObjectClass object;
}

@Data
class ObjectClass {
    private Message message;
}

@Data
class Message {
    private String text;
    private Long from_id;
    private Long date;
    private Long peer_id;
}