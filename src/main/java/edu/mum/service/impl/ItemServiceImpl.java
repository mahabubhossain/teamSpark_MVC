package edu.mum.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.dao.ItemDao;
import edu.mum.domain.Item;
import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.domain.User;
import edu.mum.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	public void save(Item Item) {
		itemDao.save(Item);
	}

	public Item update(Item Item) {
		return itemDao.update(Item);
	}

	public List<Item> findAll() {
		return (List<Item>) itemDao.findAll();
	}

	public Item findOne(Long id) {
		return itemDao.findOne(id);
	}

	@Override
	public List<Item> findBySellerOrBuyer(Integer price, User buyer, User seller) {

		return itemDao.findBySellerOrBuyer(price, buyer, seller);
	}

	public List<Item> findByCategoryName(String categoryName) {
		return itemDao.findByCategoryName(categoryName);
	}

	public List<Item> findItemCriteria(Integer initialPrice, User buyer, User seller) {
		return itemDao.findItemCriteria(initialPrice, buyer, seller);
	}

	public void publish(Item item, RabbitTemplate rabbitTemplate) {
		OrderItem orderItem = new OrderItem(2, item);
		// Make a list of the orderItems [ only 1]
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		orderItems.add(orderItem);

		// Create order...
		Order order = new Order("" + item.getId(), orderItems);
		rabbitTemplate.convertAndSend(order);

		System.out.println("Successfully sent - " + order.getItems().get(0).getProduct().getName());
	}
}
