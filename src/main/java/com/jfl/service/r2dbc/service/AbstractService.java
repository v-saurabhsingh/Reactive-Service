package com.jfl.service.r2dbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;

public class AbstractService {

    @Autowired
    protected DatabaseClient databaseClient;
}
