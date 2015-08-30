package com.infosys.dao;



import java.util.List;

import com.infosys.model.Item;
import com.infosys.model.Order;
import com.infosys.model.Payload;






public interface ItemDAO {

	//public void save(Item p);
	
	public Payload listItems();
	public Order createOrder(Order order);
	
}
