package edu.mum.amqp;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.domain.Item;
import edu.mum.service.ItemService;
import edu.mum.service.impl.ItemServiceImpl;

public class AmqpMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "context/mailContext.xml", "context/order-app-context.xml" });

		/*
		 * ApplicationContext context = new GenericXmlApplicationContext(
		 * "classpath:META-INF/spring/order-app-context.xml");
		 */

		RabbitTemplate directTemplate = context.getBean("directTemplate", RabbitTemplate.class);
		ItemService directService = new ItemServiceImpl();
		Item it = new Item();
		it.setApprovalDatetime(new Date());
		it.setDescription("Test Product");
		it.setInitialPrice(new BigDecimal(1000));
		it.setName("Dummy Item1");

		directService.publish(it, directTemplate);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// context.close();
	}
}
