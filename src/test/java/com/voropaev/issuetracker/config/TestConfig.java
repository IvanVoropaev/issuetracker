package com.voropaev.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( { TestDataConfig.class } )
public class TestConfig {

}
