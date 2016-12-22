package edu.mum.controller;

import java.util.ArrayList;
import java.util.List;

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

import edu.mum.domain.Authority;
import edu.mum.domain.Item;
import edu.mum.domain.Order;
import edu.mum.domain.OrderItem;
import edu.mum.service.ItemService;
import edu.mum.service.OrderService;

@Controller
@RequestMapping("/addToCarts")
public class addToCartController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
 
	
 	@RequestMapping("/addToCart")
	public String getItemById(Model model, @RequestParam("id") Long id) {

		Item item = itemService.findOne(id);
		model.addAttribute("item", item);
		return "item";
	}
 	
 	

	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewItemForm(@ModelAttribute("cartItem") Item newItem) {
	   return "items";
	}
	   
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewItemForm(@ModelAttribute("cartItem") @Valid Item itemToBeAdded, BindingResult result) {
		if(result.hasErrors()) {
			return "items";
		}

 		try {
 			OrderItem orderItems = new OrderItem();
 		    orderItems.setProduct(itemToBeAdded);
 		    orderItems.setQuantity(1);
 		   List <OrderItem> listOfOrderItem = new ArrayList<OrderItem>();
 		  listOfOrderItem.add(orderItems);
 		   
 			Order order = new Order("52",listOfOrderItem);
 		    //order.setOrderNumber("52");
 		    
 		   orderService.save(order);
 		    //orderItems.add(itemToBeAdded);
			//itemService.save(itemToBeAdded);
		} catch (Exception up) {
	      System.out.println("Transaction Failed!!!");
 
		}
		
	   	return "redirect:/items";
	}
	
   
}
