package edu.mum.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import edu.mum.domain.Order;
import edu.mum.domain.RouteOrder;
import edu.mum.domain.RouteOrder.RouteOrderType;
import edu.mum.emailservice.EmailService;

public class OrderListener {

	@Autowired
	private JavaMailSender mailSender;

	public void listen(Order order) {
		String name = order.getItems().get(0).getProduct().getName();
		System.out.println("---------- TOPIC Order for Product: " + name);

		sendEmail(order);
	}

	private void sendEmail(Order order) {
		RouteOrder r = new RouteOrder(order.getItems().get(0).getProduct().getName(), order.getItems().size(),
				RouteOrderType.PICKUP, order.getOrderStatus());
		EmailService mailSender = new EmailService();

		try {
			mailSender.handle(r);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sendEmail1(Order order) {
		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo("ulziierdene121@gmail.com");
		email.setSubject("Hi New Item has been added!");
		email.setText("Please check!");

		// sends the e-mail
		mailSender.send(email);
	}
}
