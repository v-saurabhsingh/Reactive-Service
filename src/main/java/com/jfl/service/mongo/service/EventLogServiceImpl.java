package com.jfl.service.mongo.service;

import com.jfl.service.mongo.entity.EventLog;
import com.jfl.service.mongo.repo.EventLogRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
@Slf4j
@Service
public class EventLogServiceImpl implements EventLogService {

    private final EventLogRepository eventLogRepository;

    @PostConstruct
    public void init(){
        EventLog eventLog=new EventLog();
        eventLog.setEventName("SERVER_START");
        saveEventLog(eventLog)
                .subscribe();
    }

    @PreDestroy
    public void destroy(){
        EventLog eventLog=new EventLog();
        eventLog.setEventName("SERVER_STOP");
        saveEventLog(eventLog)
                .subscribe();
    }

    @Override
    public Flux<EventLog> getEventList(){
        return eventLogRepository.findAll();
    }

    public Mono<EventLog> saveEventLog(EventLog eventLog){
        return eventLogRepository.save(eventLog);
    }


}
