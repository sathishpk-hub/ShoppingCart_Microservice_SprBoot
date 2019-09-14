package com.consumer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.consumer.service.daoImpl.ConsumerDao;
import com.consumer.service.daoImpl.ConsumerDaoImpl;

//@EnableJms
@SpringBootApplication
@EnableDiscoveryClient
//@ComponentScan(basePackageClasses=ConsumerController.class)
@ComponentScan( basePackages = { "com.consumer.service", "org.springframework.jms" ," org.springframework.jms.core"} )
public class ConsumerServiceApplication {
	
	public static final String ACCOUNTS_SERVICE_URL = "http://product-microservice";

	public static void main(String[] args) {
		SpringApplication.run(ConsumerServiceApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ConsumerDao consumerDao(){
		return new ConsumerDaoImpl(ACCOUNTS_SERVICE_URL);
	}
		
	
	// Only required due to defining myFactory in the receiver
	/*
	 * @Bean public JmsListenerContainerFactory<?> myFactory( ConnectionFactory
	 * connectionFactory ,DefaultJmsListenerContainerFactoryConfigurer configurer) {
	 * 
	 * DefaultJmsListenerContainerFactory factory = new
	 * DefaultJmsListenerContainerFactory(); configurer.configure(factory,
	 * connectionFactory); return factory;
	 * 
	 * }
	 * 
	 * 
	 * //Serialize message content to json using TextMessage
	 * 
	 * @Bean public MessageConverter jacksonJmsMessageConverter() {
	 * MappingJackson2MessageConverter converter = new
	 * MappingJackson2MessageConverter(); converter.setTargetType(MessageType.TEXT);
	 * converter.setTypeIdPropertyName("_type"); return converter; }
	 */
		  

}
