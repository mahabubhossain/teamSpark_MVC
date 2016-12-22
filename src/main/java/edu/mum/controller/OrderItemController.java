package edu.mum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.domain.Item;
import edu.mum.domain.Order;
import edu.mum.service.ItemService;
import edu.mum.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderItemController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
 
/* 	@RequestMapping({"","/all"})
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
*/
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewItemForm(@ModelAttribute("newItem") Item newItem) {
	   return "addItem";
	}
	   
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewItemForm(@ModelAttribute("newItem") @Valid Order confirmedOrder, BindingResult result) {
		if(result.hasErrors()) {
			return "addItem";
		}

 		try {
			//itemService.save(confirmedOrder);
			orderService.save(confirmedOrder);
		} catch (Exception up) {
	      System.out.println("Transaction Failed!!!");
 
		}
		
	   	return "redirect:/items";
	}
	
   
}
