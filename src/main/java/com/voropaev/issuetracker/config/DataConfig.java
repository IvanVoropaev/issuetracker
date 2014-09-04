package com.voropaev.issuetracker.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
//@EnableTransactionManagement
@PropertySource("/WEB-INF/spring/jdbc.properties")
//@PropertySource("/WEB-INF/mybatis/mybatis.properties")
@MapperScan("com.voropaev.issuetracker.mapper")
public class DataConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(DataConfig.class);
	
	@Value("${jdbc.driverClassName}") private String driverClassName;
	@Value("${jdbc.databaseurl}") private String databaseUrl;
	@Value("${jdbc.username}") private String userName;
	@Value("${jdbc.password}") private String password;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(databaseUrl);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		logger.info("DriverManagerDataSource");
		return dataSource;
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
		//sessionFactory.setConfigLocation(new ClassPathResource("/WEB-INF/mybatis/sqlmap-config.xml"));
		sessionFactory.setTypeAliasesPackage("com.voropaev.issuetracker.domain");
		logger.info("SqlSessionFactory");
		return sessionFactory.getObject();
	}
	
	/*@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer conf = new MapperScannerConfigurer();
		conf.setBasePackage("com.voropaev.issuetracker.mapper");
		return conf;
	}*/
	

}
