package com.infosys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.infosys.model.Item;
import com.infosys.util.DBUtilImpl;
public class ItemDAOImpl implements ItemDAO {
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> listItems() {
		Connection conn = null;
		DBUtilImpl dbUtil = new DBUtilImpl();
		List<Item> itemList = null;
		Item item;
		try {
			conn = dbUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from FurnitureGuru");			
			ResultSet rs=ps.executeQuery();
			while (rs.next()){
				System.out.println(rs.getString(0));
				item =new Item();
				item.setSku(rs.getString(0));
				item.setPrice(rs.getString(1));
				item.setColor(rs.getString(2));
				item.setDescription(rs.getString(3));
				itemList.add(item);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return itemList;
	}

}
