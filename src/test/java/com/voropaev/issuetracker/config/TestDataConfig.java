package com.voropaev.issuetracker.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
//@PropertySource("/WEB-INF/mybatis/mybatis.properties")
@MapperScan("com.voropaev.issuetracker.mapper")
public class TestDataConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(TestDataConfig.class);
	
	/*@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}*/
	
	@Bean
    public DataSource dataSource() {		
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:testshema.sql")
            .addScript("classpath:testdata.sql")
            .build();
    }
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(dataSource());
		logger.info("DataSourceTransactionManager");
		return manager;
	}
	
	@Bean 
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setTypeAliasesPackage("com.voropaev.issuetracker.domain");
		logger.info("SqlSessionFactory");
		return sessionFactory.getObject();
	}

}