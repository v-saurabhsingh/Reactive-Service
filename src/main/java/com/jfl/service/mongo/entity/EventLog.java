package com.jfl.service.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collation = "event_log")
public class EventLog {

    @Id
    private String id;
    private String eventName;
    @CreatedDate
    private Date eventDateTime;
}
