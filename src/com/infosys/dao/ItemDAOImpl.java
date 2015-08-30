package com.infosys.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.infosys.model.Category;
import com.infosys.model.Item;
import com.infosys.model.Order;
import com.infosys.model.Payload;
import com.infosys.util.DBUtilImpl;
public class ItemDAOImpl implements ItemDAO {
	
	DBUtilImpl dbUtil = new DBUtilImpl();
	@SuppressWarnings("unchecked")
	@Override
	public Payload listItems() {
		Connection conn = null;
		
		Payload payload = new Payload();
		
		List<Item> itemList = null;
		Category category = null;
		
		Item item = null;
		List<Category> categories = new ArrayList<Category>();
		InputStream is = null;
		String previousCategory = null;
		String currentCategory = null;
		String previousItem = null;
		String currentItem = null;
		try {
			
			Properties props =new Properties();
			is= this.getClass().getResourceAsStream("/com/infosys/resources/config.properties");
			props.load(is);
			String tableName =props.getProperty("tablename");
			
			conn = dbUtil.getConnection();
			//PreparedStatement ps=conn.prepareStatement("select * from FURNITUREGURU");	
			PreparedStatement ps=conn.prepareStatement("select * from "+tableName);
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				//read item
				currentItem = rs.getString(1);
				//new item
				if(!(currentItem.equalsIgnoreCase(previousItem))){
				//add previous item to the list
					if (previousItem!=null){
						itemList.add(item);
					}
					
				//read catgegory				
				currentCategory = rs.getString(2);
				//new category
				if(!(currentCategory.equalsIgnoreCase(previousCategory))){
					//add previous category to the list
					if(previousCategory!=null){
						category.setItems(itemList);
						categories.add(category);
					}
					//create new category object
					
					category =new Category();
					itemList =new ArrayList<Item>();
					category.setCat_name(currentCategory);				
					
				}
					//create a new item
					item =new Item();
					item.setSku(rs.getString(1));
					item.setItem_name(rs.getString(3));
					item.setPrice(rs.getString(4));
					item.setColor(rs.getString(5));
					item.setDescription(rs.getString(6));
					item.setImageurl(rs.getString(7));
				}
					
					previousCategory = currentCategory;					
					previousItem = currentItem;
					
			}
			//adding last catgory
			itemList.add(item);
			category.setItems(itemList);
			
			categories.add(category);
			payload.setCategory(categories);
			payload.setWebsite_name(tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return payload;
	}

	public Order createOrder(Order order) {
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = dbUtil.getConnection();
			String userName = order.getUserName();
			String address = order.getAddress();
			String paymentMethod = order.getPaymentMethod();
			String itemName = order.getItemName();
			String sku = order.getSku();
			Integer quantity = order.getQuantity();
			//Integer orderId =190;
			String date = order.getOrderDate();
			PreparedStatement ps=conn.prepareStatement("insert into Order(userName,address,paymentMethod,itemName,sku,quantity,orderDate) values(?,?,?,?,?,?,?)");
			//ps.setInt(1, orderId);
			ps.setString(1,userName);
			ps.setString(2,address);
			ps.setString(3,paymentMethod);
			ps.setString(4,itemName);
			ps.setString(5,sku);
			ps.setInt(6, quantity);
			ps.setString(7,  date);
			int i=ps.executeUpdate();
			if(i>0){
				 ps=conn.prepareStatement("select * from order");
				 rs=ps.executeQuery();
				 while (rs.next()){
					 order.setOrderId(rs.getString(1));
					 System.out.println("you have successfully placed an order with orderid: "+ order.getOrderId());
				 }
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
