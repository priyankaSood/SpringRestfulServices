package com.infosys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.infosys.dao.ItemDAOImpl;
import com.infosys.model.Order;
import com.infosys.model.Payload;



@Controller
public class ItemController {
	//private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	@RequestMapping(value = ItemRestURIConstants.GET_ALL_ITEMS, method = RequestMethod.GET)
	public @ResponseBody Payload getAllItems() {
		//logger.info("Start getAllItems.");
		Payload payload;
		ItemDAOImpl dao =new ItemDAOImpl();
		payload = dao.listItems();
		return payload;
	}
	
	@RequestMapping(value = ItemRestURIConstants.CREATE_ORDER, method = RequestMethod.POST)
	
	public @ResponseBody Order createEmployee(@RequestBody Order order) {
		//logger.info("Start createOrder.");
		Order orderDetails;
		ItemDAOImpl dao =new ItemDAOImpl();
		orderDetails = dao.createOrder(order);
		return orderDetails;
	}
}
