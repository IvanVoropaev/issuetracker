package com.voropaev.issuetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import( { SecurityConfig.class, DataConfig.class  } )
public class AppConfig {
	
}
