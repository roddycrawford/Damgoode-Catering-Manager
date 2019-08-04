package com.damgoodepies.catering.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.CompanySettings;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.DailyNote;
import com.damgoodepies.catering.model.MenuItem;
import com.damgoodepies.catering.model.Order;
import com.damgoodepies.catering.model.OrderItem;

public class CateringDaoImpl implements CateringDao
{
	private static String sqlGetAllAddresses = 
			" SELECT AddressId, BusinessName, StreetNum, StreetName, AddressType, RoomNum, " + 
			" 		 City, State, ZipCode, AddressNote " +
			" FROM address ";
	
	private static String sqlGetAddressByAddressId = 
			" SELECT AddressId, BusinessName, StreetNum, StreetName, AddressType, RoomNum, " + 
				   " City, State, ZipCode, AddressNote " +
			" FROM address " +
			" WHERE AddressId = ? ";
	
	private static String sqlAddAddress =
			" INSERT INTO Address (BusinessName, StreetNum, StreetName, AddressType, RoomNum, " + 
								 " City, State, ZipCode, AddressNote) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static String sqlUpdateAddress = 
			" UPDATE Address " +
			" SET BusinessName = ?, " +
			"	  StreetNum = ?, " +
			"	  StreetName = ?, " +
			"	  AddressType = ?, " +
			" 	  RoomNum = ?, " +
			" 	  City = ?, " +
			" 	  State = ?, " +
			"     ZipCode = ?, " +
			"     AddressNote = ? " +
			" WHERE AddressId = ? ";
	
	private static String sqlAddCustomer = 
			" INSERT INTO Customers (AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote) " +
			" VALUES(?, ?, ?, ?, ?, ?) ";
	
	private static String sqlGetAllCustomers = 
			" SELECT CustomerId, AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote " +
			" FROM Customers ";
	
	private static String sqlGetCustomerByCustomerId = 
			" SELECT CustomerId, AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote " +
			" FROM Customers " +
			" WHERE CustomerId = ? ";
	
	private static String sqlUpdateCustomer = 
			" UPDATE Customers " +
			" SET AddressId = ?, " +
			"	 FirstName = ?, " +
			"	 LastName = ?, " +
			"	 PhoneNumber = ?, " +
			"	 Email = ?, " +
			"	 CustomerNote = ? " +
			" WHERE CustomerId = ? ";
	
	private static String sqlGetCustomerByPhoneNumber =
			" SELECT CustomerId, AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote " +
			" FROM Customers " +
			" WHERE PhoneNumber = ? ";
	
	
	private static String sqlGetAllAvailableMenuItems = 
			" SELECT MenuItemId, Size, ItemName, DealCategory, MenuGroup, MenuPrice " +
			" FROM MenuItems " +
			" ORDER BY MenuItemId ASC ";
	
	private static String sqlGetCompanySettings =
			" SELECT TaxRate, StandardDeliveryFee, StandardCateringRate " +
			" FROM CompanySettings ";
	
	private static String sqlGetAllSauceOptions = 
			" SELECT Sauce " +
			" FROM SauceOptions ";
	
	private static String sqlGetAddressTypes = 
			" SELECT AddressType " +
			" FROM AddressTypes ";
	
	private static String sqlGetCrustOptions = 
			" SELECT CrustOption " +
			" FROM CrustOptions ";
	
	private static String sqlGetPaymentTypes =
			" SELECT PaymentType " +
			" FROM PaymentTypes ";
	
	private static String sqlGetAllDailyNotes = 
			" SELECT DailyNoteId, DailyNote, CreateDateTime " +
			" FROM DailyNotes " +
			" ORDER BY CreateDateTime ";
	
	private static String sqlAddDailyNote =
			" INSERT INTO DailyNotes(DailyNote) " +
			" VALUES(?) ";
	
	private static String sqlGetAllOrders = 
			" SELECT OrderId, CustomerId, CreateDateTime, LastUpdated, DueDateTime, " +
			" IsVoid, VoidReason, NumPeopleToFeed, PricePerHead, PaymentType, IsPaid, IsInPos, " +
			" IsBilledOrder, IsBuffetOrder, IsTaxExempt, HasDealsApplied, HasDeliveryFee, HasDollarDiscount, " +
			" HasPercentageDiscount, PercentageDiscountRate, PercentageDiscountAmt, " +
			" DollarDiscountAmt, Subtotal, Tax, DeliveryFee, CateringFee, Total, OrderNote " +
			" FROM Orders " +
			" WHERE IsVoid = FALSE " +
			" ORDER BY OrderId ";
	
	private static String sqlAddOrder = 
			" INSERT INTO Orders (CustomerId, DueDateTime, IsVoid, VoidReason, NumPeopleToFeed, PricePerHead, " +
			"	PaymentType, IsPaid, IsInPos, IsBilledOrder, IsBuffetOrder, IsTaxExempt, HasDealsApplied, " +
			"	HasDeliveryFee, HasDollarDiscount, HasPercentageDiscount, PercentageDiscountRate, PercentageDiscountAmt, " +
			"	DollarDiscountAmt, Subtotal, Tax, DeliveryFee, CateringFee, Total, OrderNote) " +
			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static String sqlAddOrderItem =
			" INSERT INTO OrderItems (OrderId, MenuItemId, Size, Crust, Sauce, ItemName, DealCategory, " +
			"	HasDealApplied, MarkedToRemoveFromOrder, MenuGroup, MenuPrice, ActivePrice, ItemNote) " +
			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static String sqlUpdateOrder =
			" UPDATE Orders " +
			"    SET CustomerId = ?, " + 
			"		 DueDateTime = ?, " + 
			"	  	 IsVoid = ?, " + 
			"		 VoidReason = ?, " +
			"		 NumPeopleToFeed = ?, " +
			"		 PricePerHead = ?, " +
			"		 PaymentType = ?, " +
			"		 IsPaid = ?, " +
			"		 IsInPos = ?, " +
			"		 IsBilledOrder = ?, " +
			"		 IsBuffetOrder = ?, " +
			"		 IsTaxExempt = ?, " +
			"		 HasDealsApplied = ?, " +
			"		 HasDeliveryFee = ?, " +
			"		 HasDollarDiscount = ?, " +
			"		 HasPercentageDiscount = ?, " +
			"		 PercentageDiscountRate = ?, " +
			"		 PercentageDiscountAmt = ?, " +
			"		 DollarDiscountAmt = ?, " +
			"		 Subtotal = ?, " +
			"		 Tax = ?, " +
			"		 DeliveryFee = ?, " +
			"		 CateringFee = ?, " + 
			"		 Total = ?, " +
			"		 OrderNote = ? " +
			"  WHERE OrderId = ? ";
	
	private static String sqlDeleteOrderItem =
			" DELETE FROM OrderItems WHERE OrderItemId = ? ";
	
	private static String sqlUpdateOrderItem = 
			" UPDATE OrderItems " +
			"    SET OrderId = ?, " +
			"		 MenuItemId = ?, " +
			"		 Size = ?, " +
			"		 Crust = ?, " +
			"		 Sauce = ?, " +
			"		 ItemName = ?, " +
			"		 DealCategory = ?, " +
			"		 HasDealApplied = ?, " +
			"		 MarkedToRemoveFromOrder = ?, " +
			"		 MenuGroup = ?, " +
			"		 MenuPrice = ?, " +
			"		 ActivePrice = ?, " +
			"		 ItemNote = ? " +
			"		 WHERE OrderItemId = ? ";
				
	
//	private static String sqlGetOrderItemsByOrderId = 
//			" SELECT OrderItemId, OrderId, MenuItemId, Size, Crust, Sauce, ItemName, " +
//			" DealCategory, HasDealApplied, MarkedToRemoveFromOrder, MenuGroup, " +
//			" MenuPrice, ActivePrice, ItemNote " +
//			" FROM OrderItems " +
//			" WHERE OrderId = ? " +
//			" ORDER BY OrderItemId ";
	
	private static String sqlGetOrderItemsByOrderId =
			" SELECT OrderItems.OrderId, OrderItems.OrderItemId, MenuItems.MenuItemId, OrderItems.ActivePrice, " +
			" OrderItems.Crust, OrderItems.HasDealApplied, OrderItems.ItemNote, OrderItems.MarkedToRemoveFromOrder, " +
			" MenuItems.DealCategory, MenuItems.ItemName, MenuItems.MenuGroup, MenuItems.MenuPrice, MenuItems.Size, " +
			" OrderItems.Sauce " +
			" FROM OrderItems " +
			" INNER JOIN MenuItems ON OrderItems.MenuItemId = MenuItems.MenuItemId " +
			" WHERE OrderItems.OrderId = ? " +
			" ORDER BY OrderItems.OrderItemId ASC ";
	
	@Override
	public Address addAddress(Address newAddress) 
	{		
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			INSERT INTO Address (BusinessName, StreetNum, StreetName, AddressType, RoomNum, City, State, ZipCode, AddressNote)
//			VALUES ('Damgoode Pies #1', '2701', 'Kavanaugh Blvd.', 'Business', 110, 'Little Rock', 'AR', '72205', 'The OGDGP');
			
			statement = conn.prepareStatement(sqlAddAddress);
			statement.setString(1, newAddress.getBusinessName());
			statement.setString(2, newAddress.getStreetNum());
			statement.setString(3, newAddress.getStreetName());
			statement.setString(4, newAddress.getAddressType());
			statement.setInt(5, newAddress.getRoomNum());
			statement.setString(6, newAddress.getCity());
			statement.setString(7, newAddress.getState());
			statement.setString(8, newAddress.getZipCode());
			statement.setString(9, newAddress.getAddressNote());
			
			rowCount = statement.executeUpdate();				
				
			newAddress.setAddressId(getLastInsertedId(conn));
			System.out.println("Rows effected: " + rowCount);
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println(newAddress);
		return newAddress;
	}
	
	private Integer getLastInsertedId(Connection conn) throws SQLException 
	{
		Integer key = 0;
		Statement statement = conn.createStatement();			
		ResultSet result = statement.executeQuery(" SELECT LAST_INSERT_ID() ");
		
		while(result.next()) 
		{
			key = result.getInt("LAST_INSERT_ID()");				
		}
		return key;
	}

	@Override
	public List<Address> getAllAddresses() 
	{
		List<Address> addresses = new ArrayList<Address>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllAddresses);
			
			while(result.next()) 
			{
				addresses.add(createAddress(result));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return addresses;
	}
	
	private Address createAddress(ResultSet result) throws SQLException
	{
//		{
//	        "addressId": 102,
//	        "addressNote": "fresh and magical",
//	        "addressType": "Business",
//	        "addressTypeAbr": "Suite",
//	        "businessName": "Damgoode Pies #1",
//	        "city": "Little Rock",
//	        "roomNum": 110,
//	        "state": "AR",
//	        "streetName": "Kavanaugh Blvd.",
//	        "streetNum": "2701",
//	        "zipCode": "72205"
//	    }
		
		Address address = new Address();	
		address.setAddressId(result.getInt("AddressId"));
		address.setAddressNote(result.getString("AddressNote"));
		address.setAddressType(result.getString("AddressType"));
		address.setBusinessName(result.getString("BusinessName"));
		address.setCity(result.getString("City"));
		address.setRoomNum(result.getInt("RoomNum"));
		address.setState(result.getString("State"));
		address.setStreetName(result.getString("StreetName"));
		address.setStreetNum(result.getString("StreetNum"));
		address.setZipCode(result.getString("ZipCode"));
		
//		movie.setCreateDateTime(result.getObject("createDate", LocalDateTime.class));
//		movie.setLastUpdated(result.getObject("updateDate", LocalDateTime.class));
		
		return address;
	}

	@Override
	public Address getAddressByAddressId(int addressId) 
	{	
		Address address = new Address();
//		List<Address> address = new ArrayList<Address>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{
			statement = conn.prepareStatement(sqlGetAddressByAddressId);
			statement.setInt(1, addressId);
			result = statement.executeQuery();				
				
			while(result.next()) 
			{
				address = createAddress(result);
			}
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return address;
	}

	@Override
	public Address updateAddress(Address updateAddress) 
	{		
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			" UPDATE Address " +
//			" SET BusinessName = ?, " +
//			"	  StreetNum = ?, " +
//			"	  StreetName = ?, " +
//			"	  AddressType = ?, " +
//			" 	  RoomNum = ?, " +
//			" 	  City = ?, " +
//			" 	  State = ?, " +
//			"     ZipCode = ?, " +
//			"     AddressNote = ? " +
//			" WHERE AddressId = ? ";
			
			statement = conn.prepareStatement(sqlUpdateAddress);
			statement.setString(1, updateAddress.getBusinessName());
			statement.setString(2, updateAddress.getStreetNum());
			statement.setString(3, updateAddress.getStreetName());
			statement.setString(4, updateAddress.getAddressType());
			statement.setInt(5, updateAddress.getRoomNum());
			statement.setString(6, updateAddress.getCity());
			statement.setString(7, updateAddress.getState());
			statement.setString(8, updateAddress.getZipCode());
			statement.setString(9, updateAddress.getAddressNote());
			statement.setInt(10, updateAddress.getAddressId());
			
			rowCount = statement.executeUpdate();				
			System.out.println("Row Updated: " + rowCount);
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println("update address: " + updateAddress);
		return updateAddress;
	}

	@Override
	public Customer addCustomer(Customer newCustomer) 
	{		
//		The service layer is sending the call to add address in the dao, so I don't need to do it here...
//		addAddress(newCustomer.getAddress());
		
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			INSERT INTO Customers (AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote)
//			VALUES(1, 'Roddy', 'Crawford', '5013520642', 'roddy@damgoode.com', 'Man what a super great guy');
			
			statement = conn.prepareStatement(sqlAddCustomer);
			statement.setInt(1, newCustomer.getAddress().getAddressId());
			statement.setString(2, newCustomer.getCustomerFirstName());
			statement.setString(3, newCustomer.getCustomerLastName());
			statement.setString(4, newCustomer.getPhoneNumber());
			statement.setString(5, newCustomer.getEmail());
			statement.setString(6, newCustomer.getCustomerNote());
			
			rowCount = statement.executeUpdate();				
				
			newCustomer.setCustomerId(getLastInsertedId(conn));
			System.out.println("Rows effected: " + rowCount);
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return newCustomer;
	}
	
	private Customer createCustomer(ResultSet result) throws SQLException
	{
//		{
//	        "address": {
//	            "addressId": 101,
//	            "addressNote": "so tasty",
//	            "addressType": "House",
//	            "addressTypeAbr": "",
//	            "businessName": "Damgoode Pies #2",
//	            "city": "Little Rock",
//	            "roomNum": 0,
//	            "state": "AR",
//	            "streetName": "Cantrell Rd.",
//	            "streetNum": "6706",
//	            "zipCode": "72207"
//	        },
//	        "addressId": 101,
//	        "customerFirstName": "Roddy",
//	        "customerId": 101,
//	        "customerLastName": "Crawford",
//	        "customerNote": "Roddy is the greatest",
//	        "email": "roddy@damgoode.com",
//	        "phoneNumber": "501-352-0642"
//	    }
//		" SELECT CustomerId, AddressId, FirstName, LastName, PhoneNumber, Email, CustomerNote " 
		
		Customer customer = new Customer();	
		customer.setCustomerId(result.getInt("CustomerId"));
//		customer.setAddressId(result.getInt("AddressId"));
		customer.setCustomerFirstName(result.getString("FirstName"));
		customer.setCustomerLastName(result.getString("LastName"));
		customer.setPhoneNumber(result.getString("PhoneNumber"));
		customer.setEmail(result.getString("Email"));
		customer.setCustomerNote(result.getString("CustomerNote"));
		customer.setAddress(getAddressByAddressId(result.getInt("AddressId")));
//		customer.setAddress(getAddressByAddressId(customer.getAddressId()).get(0));

		return customer;
	}

	private MenuItem createMenuItem(ResultSet result) throws SQLException
	{
//		{
//	        "dealCategory": "Cheese",
//	        "itemName": "Cheese",
//	        "menuGroup": "Pizza",
//	        "menuItemId": 101,
//	        "menuPrice": 18.99,
//	        "size": "18"
//	    }
		
		MenuItem menuItem = new MenuItem();	
		menuItem.setDealCategory(result.getString("DealCategory"));
		menuItem.setItemName(result.getString("ItemName"));
		menuItem.setMenuGroup(result.getString("MenuGroup"));
		menuItem.setMenuItemId(result.getInt("MenuItemId"));
		menuItem.setMenuPrice(result.getBigDecimal("MenuPrice"));
		menuItem.setSize(result.getString("Size"));
		
		return menuItem;
	}
	
	private OrderItem createOrderItem(ResultSet result) throws SQLException 
	{
//		{
//	        "activePrice": 18.99,
//	        "crust": "HandTossed",
//	        "hasDealApplied": false,
//	        "itemNote": "funk tastic",
//	        "markedToRemoveFromOrder": false,
//	        "menuItem": {
//	            "dealCategory": "Cheese",
//	            "itemName": "Cheese",
//	            "menuGroup": "Pizza",
//	            "menuItemId": 101,
//	            "menuPrice": 18.99,
//	            "size": "18"
//	        },
//	        "orderId": 1,
//	        "orderItemId": 101,
//	        "sauce": "Original Red"
//	    }
		
		MenuItem menuItem = createMenuItem(result);
		
		OrderItem orderItem = new OrderItem();
		orderItem.setActivePrice(result.getBigDecimal("ActivePrice"));
		orderItem.setCrust(result.getString("Crust"));
		orderItem.setHasDealApplied(result.getBoolean("HasDealApplied"));
		orderItem.setItemNote(result.getString("ItemNote"));
		orderItem.setMarkedToRemoveFromOrder(result.getBoolean("MarkedToRemoveFromOrder"));
		orderItem.setMenuItem(menuItem);
		orderItem.setOrderId(result.getInt("OrderId"));
		orderItem.setOrderItemId(result.getInt("OrderItemId"));
		orderItem.setSauce(result.getString("Sauce"));
		
		return orderItem;
	}

	private Order createOrder(ResultSet result) throws SQLException 
	{
//		 {
//		        "cateringFee": 0.00,
//		        "customer": {},
//		        "deliveryFee": 4.00,
//		        "dollarDiscountAmt": 0,
//		        "hasDealsApplied": false,
//		        "hasDeliveryFee": true,
//		        "hasDollarDiscount": false,
//		        "hasPercentageDiscount": true,
//		        "isBilledOrder": false,
//		        "isBuffetOrder": true,
//		        "isInPos": false,
//		        "isPaid": false,
//		        "isTaxExempt": false,
//		        "isVoid": false,
//		        "items": [],
//		        "numPeopleToFeed": 25,
//		        "orderCreateDateTime": "2019-07-31T23:54:00.037",
//		        "orderDueDateTime": "2019-07-31T23:54:00.037",
//		        "orderId": 1,
//		        "orderNote": "Do 10 jumping jacks",
//		        "paymentType": "cash",
//		        "percentageDiscountAmt": 21.740,
//		        "percentageDiscountRate": 50.0,
//		        "pricePerHead": 13.0,
//		        "subtotal": 21.740,
//		        "taxAmount": 4.78,
//		        "total": 30.52,
//		        "voidReason": ""
//		    }
		
		Customer customer = getCustomerByCustomerId(result.getInt("CustomerId"));
		List<OrderItem> orderItems = getOrderItemsByOrderId(result.getInt("OrderId"));
		Order order = new Order();
		
		order.setCateringFee(result.getBigDecimal("CateringFee"));
		order.setCustomer(customer);
		order.setDeliveryFee(result.getBigDecimal("DeliveryFee"));
		order.setDollarDiscountAmt(result.getBigDecimal("DollarDiscountAmt"));
		order.setHasDealsApplied(result.getBoolean("HasDealsApplied"));
		order.setHasDeliveryFee(result.getBoolean("HasDeliveryFee"));
		order.setHasDollarDiscount(result.getBoolean("HasDollarDiscount"));
		order.setHasPercentageDiscount(result.getBoolean("HasPercentageDiscount"));
		order.setIsBilledOrder(result.getBoolean("IsBilledOrder"));
		order.setIsBuffetOrder(result.getBoolean("IsBuffetOrder"));
		order.setIsInPos(result.getBoolean("IsInPos"));
		order.setIsPaid(result.getBoolean("IsPaid"));
		order.setIsTaxExempt(result.getBoolean("IsTaxExempt"));
		order.setIsVoid(result.getBoolean("IsVoid"));
		order.setItems(orderItems);
		order.setNumPeopleToFeed(result.getInt("NumPeopleToFeed"));
		order.setOrderCreateDateTime(result.getObject("CreateDateTime", LocalDateTime.class));
		order.setOrderDueDateTime(result.getObject("DueDateTime", LocalDateTime.class));
		order.setOrderId(result.getInt("OrderId"));
		order.setOrderNote(result.getString("OrderNote"));
		order.setPaymentType(result.getString("PaymentType"));
		order.setPercentageDiscountAmt(result.getBigDecimal("PercentageDiscountAmt"));
		order.setPercentageDiscountRate(result.getDouble("PercentageDiscountRate"));
		order.setPricePerHead(result.getBigDecimal("PricePerHead"));
		order.setSubtotal(result.getBigDecimal("Subtotal"));
		order.setTaxAmount(result.getBigDecimal("Tax"));
		order.setTotal(result.getBigDecimal("Total"));
		order.setVoidReason(result.getString("VoidReason"));
		
		
		
		
		
		
		return order;
	}

	private DailyNote createDailyNote(ResultSet result) throws SQLException
	{
		DailyNote dailyNote = new DailyNote();
		dailyNote.setCreateDateTime(result.getObject("CreateDateTime", LocalDateTime.class));
		dailyNote.setDailyNote(result.getString("DailyNote"));
		dailyNote.setDailyNoteId(result.getInt("DailyNoteId"));
		return dailyNote;
	}

	@Override
	public List<Customer> getAllCustomers() 
	{	
		List<Customer> customers = new ArrayList<Customer>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllCustomers);
			
			while(result.next()) 
			{
				customers.add(createCustomer(result));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return customers;
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) 
	{		
		Customer customer = new Customer();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{
			statement = conn.prepareStatement(sqlGetCustomerByCustomerId);
			statement.setInt(1, customerId);
			result = statement.executeQuery();				
				
			while(result.next()) 
			{
				customer = createCustomer(result);
			}
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber) 
	{		
		List<Customer> customer = new ArrayList<Customer>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{
			statement = conn.prepareStatement(sqlGetCustomerByPhoneNumber);
			statement.setString(1, phoneNumber);
			result = statement.executeQuery();				
				
			while(result.next()) 
			{
				customer.add(createCustomer(result));
			}
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return customer;
	}

	@Override
	public List<Customer> getCustomerByName(String customerFirstName, String customerLastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer updateCustomer) 
	{		
	
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			" UPDATE Customers " +
//			" SET AddressId = ?, " +
//			"	 FirstName = ?, " +
//			"	 LastName = ?, " +
//			"	 PhoneNumber = ?, " +
//			"	 Email = ?, " +
//			"	 CustomerNote = ? " +
//			" WHERE CustomerId = ? ";
			
			statement = conn.prepareStatement(sqlUpdateCustomer);
			statement.setInt(1, updateCustomer.getAddress().getAddressId());
			statement.setString(2, updateCustomer.getCustomerFirstName());
			statement.setString(3, updateCustomer.getCustomerLastName());
			statement.setString(4, updateCustomer.getPhoneNumber());
			statement.setString(5, updateCustomer.getEmail());
			statement.setString(6, updateCustomer.getCustomerNote());
			statement.setInt(7, updateCustomer.getCustomerId());
			
			rowCount = statement.executeUpdate();				
			System.out.println("Row Updated: " + rowCount);
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println("update customer: " + updateCustomer);
		return updateCustomer;
	}
	
	@Override
	public OrderItem addOrderItem(OrderItem addOrderItem)
	{
		System.out.println("addOrderItem");
//		INSERT INTO OrderItems (OrderId, MenuItemId, Size, Crust, Sauce, ItemName, DealCategory, HasDealApplied, 
//				MarkedToRemoveFromOrder, MenuGroup, MenuPrice, ActivePrice, ItemNote)
//				VALUES(
//					1,
//					1,
//					(SELECT Size FROM MenuItems WHERE MenuItemId=1),
//					'HandTossed',
//					'Red Sauce',
//					(SELECT ItemName FROM MenuItems WHERE MenuItemId=1),
//					(SELECT DealCategory FROM MenuItems WHERE MenuItemId=1),
//					FALSE,
//					FALSE,
//					(SELECT MenuGroup FROM MenuItems WHERE MenuItemId=1),
//					(SELECT MenuPrice FROM MenuItems WHERE MenuItemId=1),
//					(SELECT MenuPrice FROM MenuItems WHERE MenuItemId=1),
//					'I hope this works'
//				);
//		addOrderItem.setOrderId(orderId);
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{	
			statement = conn.prepareStatement(sqlAddOrderItem);
			statement.setInt(1, addOrderItem.getOrderId());
			statement.setInt(2, addOrderItem.getMenuItem().getMenuItemId());
			statement.setString(3, addOrderItem.getMenuItem().getSize());
			statement.setString(4, addOrderItem.getCrust());
			statement.setString(5, addOrderItem.getSauce());
			statement.setString(6, addOrderItem.getMenuItem().getItemName());
			statement.setString(7, addOrderItem.getMenuItem().getDealCategory());
			statement.setBoolean(8, addOrderItem.getHasDealApplied());
			statement.setBoolean(9, addOrderItem.getMarkedToRemoveFromOrder());
			statement.setString(10, addOrderItem.getMenuItem().getMenuGroup());
			statement.setBigDecimal(11, addOrderItem.getMenuItem().getMenuPrice());
			statement.setBigDecimal(12, addOrderItem.getActivePrice());
			statement.setString(13, addOrderItem.getItemNote());
			
			rowCount = statement.executeUpdate();				
				
			addOrderItem.setOrderItemId(getLastInsertedId(conn));
			System.out.println("Rows effected: " + rowCount);
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println(addOrderItem);
		return addOrderItem;
	}
	
	@Override
	public List<OrderItem> addOrderItems(List<OrderItem> newItems, int orderId)
	{	
		for(OrderItem currentItem : newItems)
		{
//			INSERT INTO OrderItems (OrderId, MenuItemId, Size, Crust, Sauce, ItemName, DealCategory, HasDealApplied, 
//					MarkedToRemoveFromOrder, MenuGroup, MenuPrice, ActivePrice, ItemNote)
//					VALUES(
//						1,
//						1,
//						(SELECT Size FROM MenuItems WHERE MenuItemId=1),
//						'HandTossed',
//						'Red Sauce',
//						(SELECT ItemName FROM MenuItems WHERE MenuItemId=1),
//						(SELECT DealCategory FROM MenuItems WHERE MenuItemId=1),
//						FALSE,
//						FALSE,
//						(SELECT MenuGroup FROM MenuItems WHERE MenuItemId=1),
//						(SELECT MenuPrice FROM MenuItems WHERE MenuItemId=1),
//						(SELECT MenuPrice FROM MenuItems WHERE MenuItemId=1),
//						'I hope this works'
//					);
			currentItem.setOrderId(orderId);
			int	rowCount = 0;
			PreparedStatement statement = null;
			
			Connection conn = CateringMariaDbUtil.getConnection();
	
			try 
			{	
				statement = conn.prepareStatement(sqlAddOrderItem);
				statement.setInt(1, currentItem.getOrderId());
				statement.setInt(2, currentItem.getMenuItem().getMenuItemId());
				statement.setString(3, currentItem.getMenuItem().getSize());
				statement.setString(4, currentItem.getCrust());
				statement.setString(5, currentItem.getSauce());
				statement.setString(6, currentItem.getMenuItem().getItemName());
				statement.setString(7, currentItem.getMenuItem().getDealCategory());
				statement.setBoolean(8, currentItem.getHasDealApplied());
				statement.setBoolean(9, currentItem.getMarkedToRemoveFromOrder());
				statement.setString(10, currentItem.getMenuItem().getMenuGroup());
				statement.setBigDecimal(11, currentItem.getMenuItem().getMenuPrice());
				statement.setBigDecimal(12, currentItem.getActivePrice());
				statement.setString(13, currentItem.getItemNote());
				
				rowCount = statement.executeUpdate();				
					
				currentItem.setOrderItemId(getLastInsertedId(conn));
				System.out.println("Rows effected: " + rowCount);
				
			} 
			catch (SQLException e) 
			{			
				e.printStackTrace();
			} 
			finally 
			{
				try 
				{				
					statement.close();
					conn.close();
				} 
				catch (SQLException e) 
				{				
					e.printStackTrace();
				}
			}
			System.out.println(currentItem);
		}
		return newItems;
	}

	@Override
	public Order addOrder(Order newOrder) 
	{
	/*
	 * Service layer is taking care of checking to see if it's a new
	 * customer or not and adding that customer to the database if it
	 * is a new customer. So by the time I make it here I know for sure
	 * that the customer is in the database.
	 * 
	 * Since I'm adding a brand new order I do know that all of the items
	 * on the order must be new with orderItemIds set at 0, so all of those
	 * will need to be added to the database no matter what.
	 */
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			INSERT INTO Orders (CustomerId, DueDateTime, IsVoid, VoidReason, NumPeopleToFeed, PricePerHead, PaymentType, 
//				IsPaid, IsInPos, IsBilledOrder, IsBuffetOrder, IsTaxExempt, HasDealsApplied, HasDeliveryFee, HasDollarDiscount, 
//				HasPercentageDiscount, PercentageDiscountRate, PercentageDiscountAmt, DollarDiscountAmt, Subtotal, Tax, 
//				DeliveryFee, CateringFee, Total, OrderNote)
//			VALUES(2, '2019-08-01 16:30:00', FALSE, '', 50, 0, '', FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE, TRUE, 
//				FALSE, 0.00, 0.00, 10.00, 20, 1, 4, 0, 30, 'huzaah');
			
			statement = conn.prepareStatement(sqlAddOrder);
			statement.setInt(1, newOrder.getCustomer().getCustomerId());
			statement.setObject(2, newOrder.getOrderDueDateTime());
			statement.setBoolean(3, newOrder.getIsVoid());
			statement.setString(4, newOrder.getVoidReason());
			statement.setInt(5, newOrder.getNumPeopleToFeed());
			statement.setBigDecimal(6, newOrder.getPricePerHead());
			statement.setString(7, newOrder.getPaymentType());
			statement.setBoolean(8, newOrder.getIsPaid());
			statement.setBoolean(9, newOrder.getIsInPos());
			statement.setBoolean(10, newOrder.getIsBilledOrder());
			statement.setBoolean(11, newOrder.getIsBuffetOrder());
			statement.setBoolean(12, newOrder.getIsTaxExempt());
			statement.setBoolean(13, newOrder.getHasDealsApplied());
			statement.setBoolean(14, newOrder.getHasDeliveryFee());
			statement.setBoolean(15, newOrder.getHasDollarDiscount());
			statement.setBoolean(16, newOrder.getHasPercentageDiscount());
			statement.setDouble(17, newOrder.getPercentageDiscountRate());
			statement.setBigDecimal(18, newOrder.getPercentageDiscountAmt());
			statement.setBigDecimal(19, newOrder.getDollarDiscountAmt());
			statement.setBigDecimal(20, newOrder.getSubtotal());
			statement.setBigDecimal(21, newOrder.getTaxAmount());
			statement.setBigDecimal(22, newOrder.getDeliveryFee());
			statement.setBigDecimal(23, newOrder.getCateringFee());
			statement.setBigDecimal(24, newOrder.getTotal());
			statement.setString(25, newOrder.getOrderNote());
			
			rowCount = statement.executeUpdate();				
				
			newOrder.setOrderId(getLastInsertedId(conn));
			System.out.println("Rows effected: " + rowCount);
			
			newOrder.setItems(addOrderItems(newOrder.getItems(), newOrder.getOrderId()));
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println(newOrder);
		return newOrder;
//		return null;
	}

	@Override
	public List<Order> getAllOrders() 
	{	
		List<Order> orders = new ArrayList<Order>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllOrders);
			
			while(result.next()) 
			{
				orders.add(createOrder(result));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return orders;
	}
	
	@Override
	public Order updateOrder(Order updateOrder) 
	{		
	/*
	 * 	Service layer takes care of updating customer and address
	 */
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			"" UPDATE Orders " +
//			"    SET CustomerId = ?, " + 
//			"		 DueDateTime = ?, " + 
//			"	  	 IsVoid = ?, " + 
//			"		 VoidReason = ?, " +
//			"		 NumPeopleToFeed = ?, " +
//			"		 PricePerHead = ?, " +
//			"		 PaymentType = ?, " +
//			"		 IsPaid = ?, " +
//			"		 IsInPos = ?, " +
//			"		 IsBilledOrder = ?, " +
//			"		 IsBuffetOrder = ?, " +
//			"		 IsTaxExempt = ?, " +
//			"		 HasDealsApplied = ?, " +
//			"		 HasDeliveryFee = ?, " +
//			"		 HasDollarDiscount = ?, " +
//			"		 HasPercentageDiscount = ?, " +
//			"		 PercentageDiscountRate = ?, " +
//			"		 PercentageDiscountAmt = ?, " +
//			"		 DollarDiscountAmt = ?, " +
//			"		 Subtotal = ?, " +
//			"		 Tax = ?, " +
//			"		 DeliveryFee = ?, " +
//			"		 CateringFee = ?, " + 
//			"		 Total = ?, " +
//			"		 OrderNote = ? " +
//			"  WHERE OrderId = ? ";
			
			statement = conn.prepareStatement(sqlUpdateOrder);
			statement.setInt(1, updateOrder.getCustomer().getCustomerId());
			statement.setObject(2, updateOrder.getOrderDueDateTime());
			statement.setBoolean(3, updateOrder.getIsVoid());
			statement.setString(4, updateOrder.getVoidReason());
			statement.setInt(5, updateOrder.getNumPeopleToFeed());
			statement.setBigDecimal(6, updateOrder.getPricePerHead());
			statement.setString(7, updateOrder.getPaymentType());
			statement.setBoolean(8, updateOrder.getIsPaid());
			statement.setBoolean(9, updateOrder.getIsInPos());
			statement.setBoolean(10, updateOrder.getIsBilledOrder());
			statement.setBoolean(11, updateOrder.getIsBuffetOrder());
			statement.setBoolean(12, updateOrder.getIsTaxExempt());
			statement.setBoolean(13, updateOrder.getHasDealsApplied());
			statement.setBoolean(14, updateOrder.getHasDeliveryFee());
			statement.setBoolean(15, updateOrder.getHasDollarDiscount());
			statement.setBoolean(16, updateOrder.getHasPercentageDiscount());
			statement.setDouble(17, updateOrder.getPercentageDiscountRate());
			statement.setBigDecimal(18, updateOrder.getPercentageDiscountAmt());
			statement.setBigDecimal(19, updateOrder.getDollarDiscountAmt());
			statement.setBigDecimal(20, updateOrder.getSubtotal());
			statement.setBigDecimal(21, updateOrder.getTaxAmount());
			statement.setBigDecimal(22, updateOrder.getDeliveryFee());
			statement.setBigDecimal(23, updateOrder.getCateringFee());
			statement.setBigDecimal(24, updateOrder.getTotal());
			statement.setString(25, updateOrder.getOrderNote());
			statement.setInt(26, updateOrder.getOrderId());
			
			rowCount = statement.executeUpdate();				
			System.out.println("Row Updated: " + rowCount);
			
			updateOrder.setItems(updateOrderItems(updateOrder.getItems()));
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println("update order: " + updateOrder);
		return updateOrder;
	}
	
	@Override
	public OrderItem updateOrderItem(OrderItem updateItem)
	{
		System.out.println("updateOrderItem");
			int	rowCount = 0;
			PreparedStatement statement = null;
			
			Connection conn = CateringMariaDbUtil.getConnection();

			try 
			{				
//				" UPDATE OrderItems " +
//						"    SET OrderId = ?, " +
//						"		 MenuItemId = ?, " +
//						"		 Size = ?, " +
//						"		 Crust = ?, " +
//						"		 Sauce = ?, " +
//						"		 ItemName = ?, " +
//						"		 DealCategory = ?, " +
//						"		 HasDealApplied = ?, " +
//						"		 MarkedToRemoveFromOrder = ?, " +
//						"		 MenuGroup = ?, " +
//						"		 MenuPrice = ?, " +
//						"		 ActivePrice = ?, " +
//						"		 ItemNote = ? " +
//						"		 WHERE OrderItemId = ? ";
				
				statement = conn.prepareStatement(sqlUpdateOrderItem);
				statement.setInt(1, updateItem.getOrderId());
				statement.setInt(2, updateItem.getMenuItem().getMenuItemId());
				statement.setString(3, updateItem.getMenuItem().getSize());
				statement.setString(4, updateItem.getCrust());
				statement.setString(5, updateItem.getSauce());
				statement.setString(6, updateItem.getMenuItem().getItemName());
				statement.setString(7, updateItem.getMenuItem().getDealCategory());
				statement.setBoolean(8, updateItem.getHasDealApplied());
				statement.setBoolean(9, updateItem.getMarkedToRemoveFromOrder());
				statement.setString(10, updateItem.getMenuItem().getMenuGroup());
				statement.setBigDecimal(11, updateItem.getMenuItem().getMenuPrice());
				statement.setBigDecimal(12, updateItem.getActivePrice());
				statement.setString(13, updateItem.getItemNote());
				statement.setInt(14, updateItem.getOrderItemId());
				
				rowCount = statement.executeUpdate();				
				System.out.println("Row Updated: " + rowCount);
			} 
			catch (SQLException e) 
			{			
				e.printStackTrace();
			} 
			finally 
			{
				try 
				{				
					statement.close();
					conn.close();
				} 
				catch (SQLException e) 
				{				
					e.printStackTrace();
				}
			}
			System.out.println("update orderItem: " + updateItem);
			return updateItem;
		}
	
	@Override
	public void deleteOrderItem(OrderItem deleteItem)
	{		
		System.out.println("deleteOrderItem");
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			" DELETE FROM OrderItems WHERE OrderItemId = ? ";
			
			statement = conn.prepareStatement(sqlDeleteOrderItem);
			statement.setInt(1, deleteItem.getOrderItemId());
			
			rowCount = statement.executeUpdate();				
			System.out.println("Row Updated: " + rowCount);
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		System.out.println("delete orderItem: " + deleteItem);
	}
	
	@Override
	public List<OrderItem> updateOrderItems(List<OrderItem> updateItems)
	{
		for(int i = updateItems.size()-1; i >= 0; i--)
		{
			if(updateItems.get(i).getMarkedToRemoveFromOrder())
			{
				deleteOrderItem(updateItems.get(i));
				updateItems.remove(i);
			}
		}	
		
		for(OrderItem currentItem : updateItems)
		{
			if(currentItem.getOrderItemId() == 0)
			{
				currentItem.setOrderItemId(addOrderItem(currentItem).getOrderId());
			}
			else
			{
				updateOrderItem(currentItem);
			}
		}
		return updateItems;
	}

	@Override
	public List<Order> getOrderByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getOrderHistoryByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> deleteByOrderId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> addMenuItem(MenuItem newMenuItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuItem> getAllMenuItems() 
	{
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllAvailableMenuItems);
			
			while(result.next()) 
			{
				menuItems.add(createMenuItem(result));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return menuItems;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) 
	{	
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		ResultSet result = null;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{
			statement = conn.prepareStatement(sqlGetOrderItemsByOrderId);
			statement.setInt(1, orderId);
			result = statement.executeQuery();				
				
			while(result.next()) 
			{
				orderItems.add(createOrderItem(result));
			}
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return orderItems;
	}

	@Override
	public List<DailyNote> getAllDailyNotes() 
	{	
		List<DailyNote> dailyNotes = new ArrayList<DailyNote>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllDailyNotes);
			
			while(result.next()) 
			{
				dailyNotes.add(createDailyNote(result));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return dailyNotes;
	}
	
	@Override
	public DailyNote addDailyNote(DailyNote newDailyNote) 
	{		
		int	rowCount = 0;
		PreparedStatement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{				
//			INSERT INTO DailyNotes(DailyNote)
//				VALUES('Sold a bunch of pizza');
			
			statement = conn.prepareStatement(sqlAddDailyNote);
			statement.setString(1, newDailyNote.getDailyNote());
			
			rowCount = statement.executeUpdate();				
				
			newDailyNote.setDailyNoteId(getLastInsertedId(conn));
			System.out.println("Rows effected: " + rowCount);
			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return newDailyNote;
	}

	@Override
	public CompanySettings getCompanySettings() 
	{
		CompanySettings settings = new CompanySettings();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetCompanySettings);
			
			result.next();
			settings.setStandardDeliveryFee(result.getDouble("StandardDeliveryFee"));
			settings.setTaxRate(result.getDouble("TaxRate"));
			settings.setStandardCateringRate(result.getDouble("StandardCateringRate"));
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return settings;
	}

	@Override
	public List<String> getSauceOptions() 
	{
		List<String> sauceOptions = new ArrayList<String>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAllSauceOptions);
			
			while(result.next()) 
			{
				sauceOptions.add(result.getString("Sauce"));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return sauceOptions;
	}

	@Override
	public List<String> getAddressTypes() 
	{
		List<String> addressTypes = new ArrayList<String>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetAddressTypes);
			
			while(result.next()) 
			{
				addressTypes.add(result.getString("AddressType"));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return addressTypes;
	}

	@Override
	public List<String> getCrustOptions() 
	{
		List<String> crustOptions = new ArrayList<String>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetCrustOptions);
			
			while(result.next()) 
			{
				crustOptions.add(result.getString("CrustOption"));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return crustOptions;
	}

	@Override
	public List<String> getPaymentTypes() 
	{
		List<String> paymentTypes = new ArrayList<String>();
		
		ResultSet result = null;
		Statement statement = null;
		
		Connection conn = CateringMariaDbUtil.getConnection();

		try 
		{			
			statement = conn.createStatement();			
			result = statement.executeQuery(sqlGetPaymentTypes);
			
			while(result.next()) 
			{
				paymentTypes.add(result.getString("PaymentType"));	
			}			
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{				
				result.close();
				statement.close();
				conn.close();
			} 
			catch (SQLException e) 
			{				
				e.printStackTrace();
			}
		}
		return paymentTypes;
	}

}
