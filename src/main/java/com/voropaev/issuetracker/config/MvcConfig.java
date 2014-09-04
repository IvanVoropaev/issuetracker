package com.voropaev.issuetracker.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.springsecurity3.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc /*<mvc:annotation-driven/>*/
@ComponentScan(basePackages = {"com.voropaev.issuetracker", "org.thymeleaf.extras.springsecurity3"}) /*<context:component-scan base-package="com.voropaev" />*/
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
		logger.info("resourses adeed");
		System.out.println("Resourses added");
    }
	
	/*<mvc:default-servlet-handler/>*/
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        logger.info("default-servlet-handler");
    }
		
	@Bean
	public TemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setTemplateMode("HTML5");
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setSuffix(".html");
		logger.info("TemplateResolver");
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		Set<IDialect> set = new HashSet<IDialect>();
		set.add(new SpringSecurityDialect());
		set.add(new SpringStandardDialect());
		engine.setAdditionalDialects(set);
		logger.info("SpringTemplateEngine");
		return engine;
	}
	
	@Bean 
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		logger.info("ThymeleafViewResolver");
		return resolver;
	}		
}
