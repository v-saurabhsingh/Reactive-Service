package org.reactive.service.r2dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;

public class AbstractService {

    @Autowired
    protected DatabaseClient databaseClient;
}
