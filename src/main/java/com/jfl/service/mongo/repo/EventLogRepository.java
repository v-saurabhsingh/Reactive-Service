package com.jfl.service.mongo.repo;

import com.jfl.service.mongo.entity.EventLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends ReactiveMongoRepository<EventLog,String> {
}
