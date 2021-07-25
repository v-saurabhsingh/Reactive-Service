package org.reactive.service.mongo.repo;

import org.reactive.service.mongo.entity.EventLog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends ReactiveMongoRepository<EventLog,String> {
}
