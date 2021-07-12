package com.jfl.service.mongo.service;

import com.jfl.service.mongo.entity.EventLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventLogService {

    Flux<EventLog> getEventList();

    Mono<EventLog> saveEventLog(EventLog eventLog);

}
