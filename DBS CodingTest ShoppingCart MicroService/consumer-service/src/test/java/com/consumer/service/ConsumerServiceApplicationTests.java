package com.consumer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan( basePackages = { "com.consumer.service"} )
public class ConsumerServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

}
