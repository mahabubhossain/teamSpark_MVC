package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.domain.Item;
import edu.mum.service.ItemService;
import edu.mum.service.impl.ItemServiceImpl;

@Controller
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping({ "", "/all" })
	public String list(Model model) {
		model.addAttribute("items", itemService.findAll());
		return "items";
	}

	@RequestMapping("/item")
	public String getItemById(Model model, @RequestParam("id") Long id) {

		Item item = itemService.findOne(id);
		model.addAttribute("item", item);
		return "item";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewItemForm(@ModelAttribute("newItem") Item newItem) {
		return "addItem";
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewItemForm(@ModelAttribute("newItem") @Valid Item itemToBeAdded, BindingResult result) {
		if (result.hasErrors()) {
			return "addItem";
		}

		try {
			itemService.save(itemToBeAdded);
		} catch (Exception up) {
			System.out.println("Transaction Failed!!!");
		}

		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "context/mailContext.xml", "context/order-app-context.xml" });

		RabbitTemplate directTemplate = context.getBean("directTemplate", RabbitTemplate.class);
		ItemService directService = new ItemServiceImpl();
		directService.publish(itemToBeAdded, directTemplate);

		return "redirect:/items";
	}
}