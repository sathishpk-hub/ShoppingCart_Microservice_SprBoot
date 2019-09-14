package com.consumer.service.listener;

import java.io.IOException;

import javax.jms.MessageConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.consumer.service.entity.ProductEntity;
import com.consumer.service.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
@EnableJms
public class MessageQueueListener {

	/*
	 * Receive Message/Object from JMS ActiveMQ
	 */
	
	@Autowired
	ProductRepository productRepository;
	
	
	 private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

	 // JSON string to Java object
     //String jsonInString = "{\"name\":\"mkyong\",\"age\":37,\"skills\":[\"java\",\"python\"]}";
     //Staff staff2 = mapper.readValue(jsonInString, Staff.class);

     
    @JmsListener(destination = "test-queue")
    public void listener(String jsonInString){
    	ObjectMapper mapper = new ObjectMapper();
        try {
        	ProductEntity productEntity = mapper.readValue(jsonInString, ProductEntity.class);
			logger.info("Message received {} ", productEntity);
			
			//save product into database table 'product' using JPA hibernate
			saveMessageIntoDB(productEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	    
	
	 private int count = 1;
	/*
	 * @JmsListener(destination = "OrderQueue", containerFactory = "myFactory")
	 * public void receiveOrderMessage(ProductEntity productEntity) { //this will
	 * print product entity object by default calling "toString()" method of
	 * ProductEntity.class System.out.println("<" + count + "> Received <"
	 * +productEntity + ">"); //orderRepository.save(productEntity2); }
	 */
	 
	 
	 private void saveMessageIntoDB(ProductEntity productEntity) {
		 ProductEntity productEntityAfterSaved = productRepository.save(productEntity);
		 System.out.println("productEntityAfterSaved ="+productEntityAfterSaved);
		 
	 }
	 
	
	 
	 
}
