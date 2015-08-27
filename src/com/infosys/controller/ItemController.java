package com.infosys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infosys.dao.ItemDAOImpl;
import com.infosys.model.Item;



@Controller
public class ItemController {
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	@RequestMapping(value = ItemRestURIConstants.GET_ALL_ITEMS, method = RequestMethod.GET)
	public @ResponseBody List<Item> getAllItems() {
		logger.info("Start getAllItems.");
		List<Item> items;
		ItemDAOImpl dao =new ItemDAOImpl();
		items = dao.listItems();
		return items;
	}
}
