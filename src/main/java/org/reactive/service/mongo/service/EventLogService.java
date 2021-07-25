package org.reactive.service.mongo.service;

import org.reactive.service.mongo.entity.EventLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventLogService {

    Flux<EventLog> getEventList();

    Mono<EventLog> saveEventLog(EventLog eventLog);

}
