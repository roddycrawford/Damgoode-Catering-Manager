package com.damgoodepies.catering.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//import org.joda.money.BigMoney;
//import org.joda.money.CurrencyUnit;

import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.CompanySettings;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.DailyNote;
import com.damgoodepies.catering.model.MenuItem;
import com.damgoodepies.catering.model.OrderItem;
//import com.damgoodepies.catering.model.OrderNote;
import com.damgoodepies.catering.model.Order;

public class CateringDaoMock implements CateringDao
{
	private static List<Address> addressList = new ArrayList<Address>();
	private static List<Customer> customerList = new ArrayList<Customer>();
	private static List<Order> orderList = new ArrayList<Order>();
	private static List<OrderItem> orderItemList = new ArrayList<OrderItem>();
//	private static List<OrderNote> orderNotes = new ArrayList<OrderNote>();
	private static List<MenuItem> menuItems = new ArrayList<MenuItem>();
	private static List<DailyNote> dailyNotes = new ArrayList<DailyNote>();
	private static CompanySettings settings = new CompanySettings(.11, 4, .1);
	
//	Mock Address Table
	static
	{
//		public CateringAddress(String streetNum, String streetName, int roomNum, String addressTypeString,
//		String city, String state, String zipCode, String businessName, String addressNote)
		
		addressList.add(new Address("6706", "Cantrell Rd.", 0, 
				"none", "Little Rock", "AR", "72207", "Damgoode Pies #2", "so tasty"));
		addressList.add(new Address("2701", "Kavanaugh Blvd.", 110,
				"business", "Little Rock", "AR", "72205", "Damgoode Pies #1", "fresh and magical"));
		addressList.add(new Address("500", "President Clinton Ave.", 105,
				"apartment", "Little Rock", "AR", "72201", "Damgoode Pies #6", "fancy patio"));
		addressList.add(new Address("4301", "W. Markham St.", 2,
				"hospital", "Little Rock", "AR", "72205", "UAMS", "good customer!"));
	}
	
//	Mock Customer Table
	static
	{
//		public Customer(String customerFirstName, String customerLastName, Address address,
//				String phoneNumber, String email, String customerNote)
		
		customerList.add(new Customer("Roddy", "Crawford", addressList.get(0), "5013520642", 
				"roddy@damgoode.com", "Roddy is the greatest"));
		customerList.add(new Customer("Laura", "Crawford", addressList.get(1),"5016281709", 
				"lkhope89@gmail.com", "Laura is also the greatest"));
		customerList.add(new Customer("Hayden", "Koon", addressList.get(2),"5016642239", 
				"littleguy@gmail.com", "Hayden is pretty great"));
		customerList.add(new Customer("Hadley", "Koon", addressList.get(3),"5016642274", 
				"tinyprincess@gmail.com", "Hadley is the prettiest princess"));
	}
	
//	Mock menuItem Table
	static
	{
//		public MenuItem(String size, String itemName,
//				String dealCategory, String menuGroup, BigDecimal menuPrice)
		
		menuItems.add(new MenuItem("18", "Cheese", "Cheese", "Pizza", 
				 BigDecimal.valueOf(18.99)));
		menuItems.add(new MenuItem("18", "Artie", "Tier1", "Pizza",
				BigDecimal.valueOf(24.49)));
		menuItems.add(new MenuItem("18", "BBQ Chicken", "Tier2", "Pizza",
				BigDecimal.valueOf(36.99)));
		menuItems.add(new MenuItem("Catering", "Caesar Salad", "", "Salad",
				BigDecimal.valueOf(54.99)));
		menuItems.add(new MenuItem("Party", "Cheese Bread", "", "App",
				BigDecimal.valueOf(20.99)));
		
	}
	
//	Mock orderItem Table
	static
	{
//		public OrderItem(int orderId, MenuItem menuItem, String crust, String sauce, String itemNote,
//				BigDecimal activePrice, boolean hasDealApplied, boolean markedToRemoveFromOrder)
		
		orderItemList.add(new OrderItem(1, menuItems.get(0),  "HandTossed", "Original Red",
				"funk tastic", menuItems.get(0).getMenuPrice(), false, false));
		orderItemList.add(new OrderItem(1, menuItems.get(1), "Thin", "Pink",
				"mad man in a box", menuItems.get(1).getMenuPrice(), false, false));
		orderItemList.add(new OrderItem(2, menuItems.get(2), "HandTossed", "Pesto",
				"it's a pizza-pie", menuItems.get(2).getMenuPrice(), false, false));
		orderItemList.add(new OrderItem(2, menuItems.get(3), "Thin", "Original Red",
				"boop", menuItems.get(3).getMenuPrice(), false, false));
		orderItemList.add(new OrderItem(3, menuItems.get(2), "Thin", "Original Red",
				"blop", menuItems.get(2).getMenuPrice(), false, false));
	}
	
//	Mock Order Table
	static
	{
//		public Order(Customer customer, LocalDateTime orderCreateDateTime, 
//				LocalDateTime orderDueDateTime, String paymentType, boolean isPaid, boolean isInPos, 
//				boolean isBilledOrder, boolean isTaxExempt, boolean hasPercentageDiscount, 
//				boolean hasDollarDiscount, double percentageDiscountRate, double dollarDiscountAmt, 
//				boolean hasDeliveryFee, List<OrderItem> orderItems, String orderNote, boolean isVoid,
//				String voidReason, int numPeopleToFeed, boolean isBuffetOrder, BigDecimal pricePerHead,
//				boolean hasDealsApplied)
		
		LocalDateTime now = LocalDateTime.now();
		
		List<OrderItem> order1 = new ArrayList<OrderItem>();
		order1.add(orderItemList.get(0));
		order1.add(orderItemList.get(1));
		
		List<OrderItem> order2 = new ArrayList<OrderItem>();
		order2.add(orderItemList.get(2));
		order2.add(orderItemList.get(3));
		
		List<OrderItem> order3 = new ArrayList<OrderItem>();
		order3.add(orderItemList.get(4));
		
		orderList.add(new Order(customerList.get(0), now, now, 
				"cash", false, false, false, false, true, false, 50, 0, true, order1, "Do 10 jumping jacks", 
				false, "", 25, true, BigDecimal.valueOf(13.00), false));
		orderList.add(new Order(customerList.get(1), now, now, 
				"Credit Card", false, true, true, true, false, true, 0.00, 10, false, order2, "go around back", 
				false, "", 50, false, BigDecimal.valueOf(0), false));
		orderList.add(new Order(customerList.get(2), now, now, 
				"Credit Card", true, false, false, false, false, false, 0.00, 0.00, true, order3, 
				"be there by 6", false, "", 100, false, BigDecimal.valueOf(0), false));
	}
	
//	Mock OrderNotes table
//	static
//	{
////		public CateringNote(int orderId, String note)
//		
//		orderNotes.add(new OrderNote(1, "go around back"));
//		orderNotes.add(new OrderNote(2, "stand on your head"));
//		orderNotes.add(new OrderNote(3, "Hayden made an order!"));
//	}
	
//	Mock DailyNote table
	static
	{
		LocalDateTime now2 = LocalDateTime.now();
		dailyNotes.add(new DailyNote(101, "called a bunch of folks", now2));
		dailyNotes.add(new DailyNote(102, "ate a bunch of pizza", now2));
	}
	
	@Override
	public List<Address> getAllAddresses() 
	{
		return addressList;
	}

	@Override
	public Address getAddressByAddressId(int addressId) 
	{
		Address address = new Address();
//		List<Address> addresses = new ArrayList<Address>();
		
		for(Address currentAddress : CateringDaoMock.addressList)
		{
//			System.out.println(address);
			if(currentAddress.getAddressId() == addressId)
			{
				address = currentAddress;
			}
		}
		
//		System.out.println("AddressId it was looking for: " + addressId);
//		System.out.println("size of address ArrayList: " + addresses.size());
		return address;
	}

	@Override
	public List<Customer> getAllCustomers() 
	{
		return customerList;
	}

	@Override
	public Customer getCustomerByCustomerId(int customerId) 
	{
		for(Customer currentCustomer : CateringDaoMock.customerList)
		{
			if(currentCustomer.getCustomerId() == customerId)
			{
				return currentCustomer;
			}
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber) 
	{
		List<Customer> contacts = new ArrayList<Customer>();
		
		for(Customer contact : CateringDaoMock.customerList)
		{
			if(contact.getPhoneNumber().equals(phoneNumber))
			{
				contacts.add(contact);
			}
		}
		return contacts;
	}

	@Override
	public List<Customer> getCustomerByName(String customerFirstName, String customerLastName) 
	{
		List<Customer> contacts = new ArrayList<Customer>();
		
		for(Customer contact : CateringDaoMock.customerList)
		{
//			Both first and last name passed in
			if(customerFirstName.length() > 0 && customerLastName.length() > 0)
			{
				if(contact.getCustomerFirstName().equalsIgnoreCase(customerFirstName) &&
						contact.getCustomerLastName().equalsIgnoreCase(customerLastName))
				{
					contacts.add(contact);
				}
			}
//			Only first name passed in
			else if(customerFirstName.length() > 0 && customerLastName.length() == 0)
			{
				if(contact.getCustomerFirstName().equalsIgnoreCase(customerFirstName))
				{
					contacts.add(contact);
				}
			}
//			Only last name passed in
			else if(customerFirstName.length() == 0 && customerLastName.length() > 0)
			{
				if(contact.getCustomerLastName().equalsIgnoreCase(customerLastName))
				{
					contacts.add(contact);
				}
			}
		}
		return contacts;
	}

	@Override
	public List<Order> getAllOrders() 
	{
		List<Order> orders = new ArrayList<Order>();
		
		for(Order order : CateringDaoMock.orderList)
		{
			order = addItemsAndNotesToOrder(order);
			orders.add(order);
		}
		return orders;
//		return CateringDaoMock.orderList;
	}
	
	private Order addItemsAndNotesToOrder(Order order)
	{
//		Adds any items in the item table with the same orderID to the Order
		List<OrderItem> relevantItems = new ArrayList<OrderItem>();
		for(OrderItem item : CateringDaoMock.orderItemList)
		{
			if(item.getOrderId() == order.getOrderId())
			{
				relevantItems.add(item);
			}
		}
		
		order.setItems(relevantItems);
		
//		Adds any order notes in the order note table with the same order ID to the Order
//		List<OrderNote> relevantNotes = new ArrayList<OrderNote>();
//		for(OrderNote note : CateringDaoMock.orderNotes)
//		{
//			if(note.getOrderId() == order.getOrderId())
//			{
//				relevantNotes.add(note);
//			}
//		}
//		order.setOrderNotes(relevantNotes);
		
		return order;
	}

	@Override
	public List<Order> getOrderByOrderId(int orderId) 
	{
		List<Order> allOrders = getAllOrders();
		List<Order> relevantOrders = new ArrayList<Order>();
		
		for(Order order : allOrders)
		{
			if(order.getOrderId() == orderId)
			{
				relevantOrders.add(order);
			}
		}
				
		return relevantOrders;
	}

	@Override
	public List<Order> getOrderHistoryByCustomerId(int customerId) 
	{
		List<Order> allOrders = getAllOrders();
		List<Order> relevantOrders = new ArrayList<Order>();
		
		for(Order order : allOrders)
		{
			if(order.getCustomer().getCustomerId() == customerId)
			{
				relevantOrders.add(order);
			}
		}
				
		return relevantOrders;
	}

	@Override
	public List<Order> deleteByOrderId(int orderId) 
	{
		List<Order> deletedOrders = new ArrayList<Order>();
		
		for(Order order : CateringDaoMock.orderList)
		{
			if(order.getOrderId() == orderId)
			{
				deletedOrders.add(order);
				CateringDaoMock.orderList.remove(order);
				break;
			}
		}
		
		return deletedOrders;
	}

	@Override
	public Order addOrder(Order newOrder) 
	{
		CateringDaoMock.customerList.add(newOrder.getCustomer());
		
//		for(OrderNote note : newOrder.getOrderNotes())
//		{
//			CateringDaoMock.orderNotes.add(note);
//		}
		
		for(OrderItem item : newOrder.getItems())
		{
			CateringDaoMock.orderItemList.add(item);
		}
		
		CateringDaoMock.orderList.add(newOrder);
		return newOrder;
	}

	@Override
	public Customer addCustomer(Customer newCustomer) 
	{
		newCustomer.setCustomerId(getNewCustomerId());
//		newCustomer.setAddressId(newCustomer.getAddress().getAddressId());
		CateringDaoMock.customerList.add(newCustomer);
		
//		System.out.println("newCustomer after adding the new customer Id" + newCustomer);
//		System.out.println("newCustomer.getAddressId(): " + newCustomer.getAddressId());
//		System.out.println("newCustomer.getCustomerId(): " + newCustomer.getCustomerId());
		
		setCustomerAddress(newCustomer.getAddress().getAddressId(), newCustomer.getCustomerId());
		return newCustomer;
	}
	
	private int getNewCustomerId()
	{
		int maxContactId = 0;
		for(Customer contact : CateringDaoMock.customerList)
		{
			if(contact.getCustomerId() > maxContactId)
			{
				maxContactId = contact.getCustomerId();
			}
		}
		return maxContactId + 1;
	}
	
	private void setCustomerAddress(int addressId, int customerId)
	{
		Address address = getAddressByAddressId(addressId);
		
		for(Customer customer : CateringDaoMock.customerList)
		{
			if(customer.getCustomerId() == customerId)
			{
				customer.setAddress(address);
				break;
			}
		}
	}

	@Override
	public Address addAddress(Address newAddress) 
	{
		newAddress.setAddressId(getNewAddressId());
		CateringDaoMock.addressList.add(newAddress);
		return newAddress;
	}
	
	private int getNewAddressId()
	{
		int maxAddressId = 0;
		for(Address address : CateringDaoMock.addressList)
		{
			if(address.getAddressId() > maxAddressId)
			{
				maxAddressId = address.getAddressId();
			}
		}
		return maxAddressId + 1;
	}

	@Override
	public Address updateAddress(Address updateAddress)
	{
		Address newAddress = null;
		for(Address address : CateringDaoMock.addressList)
		{
			if(address.getAddressId() == updateAddress.getAddressId())
			{
				if(updateAddress.getAddressType() != null)
				{
					address.setAddressType(updateAddress.getAddressType());
				}
				if(updateAddress.getBusinessName() != null)
				{
					address.setBusinessName(updateAddress.getBusinessName());
				}
				if(updateAddress.getCity() != null)
				{
					address.setCity(updateAddress.getCity());
				}
				if(updateAddress.getRoomNum() != 0)
				{
					address.setRoomNum(updateAddress.getRoomNum());
				}
				if(updateAddress.getState() != null)
				{
					address.setState(updateAddress.getState());
				}
				if(updateAddress.getStreetName() != null)
				{
					address.setStreetName(updateAddress.getStreetName());
				}
				if(updateAddress.getStreetNum() != null)
				{
					address.setStreetNum(updateAddress.getStreetNum());
				}
				if(updateAddress.getZipCode() != null)
				{
					address.setZipCode(updateAddress.getZipCode());
				}
				if(updateAddress.getAddressNote() != null)
				{
					address.setAddressNote(updateAddress.getAddressNote());
				}
				
				newAddress = updateAddress;
				break;
			}
		}
		return newAddress;
	}

	@Override
	public Customer updateCustomer(Customer updateCustomer) 
	{		
		Customer newContact = null;
		for(Customer contact : CateringDaoMock.customerList)
		{
			if(contact.getCustomerId() == updateCustomer.getCustomerId())
			{
//				if(updateCustomer.getAddressId() != 0)
//				{
//					contact.setAddressId(updateCustomer.getAddressId());
//					setCustomerAddress(updateCustomer.getAddressId(), updateCustomer.getCustomerId());
//				}
				if(updateCustomer.getCustomerNote() != null)
				{
					contact.setCustomerNote(updateCustomer.getCustomerNote());
				}
				if(updateCustomer.getCustomerFirstName() != null)
				{
					contact.setCustomerFirstName(updateCustomer.getCustomerFirstName());
				}
				if(updateCustomer.getCustomerLastName() != null)
				{
					contact.setCustomerLastName(updateCustomer.getCustomerLastName());
				}
				if(updateCustomer.getEmail() != null)
				{
					contact.setEmail(updateCustomer.getEmail());
				}
				if(updateCustomer.getPhoneNumber() != null)
				{
					contact.setPhoneNumber(updateCustomer.getPhoneNumber());
				}
				
				newContact = updateCustomer;
				break;
			}
		}
		return newContact;
	}

	@Override
	public List<MenuItem> getAllMenuItems() 
	{
		return CateringDaoMock.menuItems;
	}

	@Override
	public List<MenuItem> addMenuItem(MenuItem newMenuItem) 
	{
		newMenuItem.setMenuItemId(getNewMenuItemId());
		CateringDaoMock.menuItems.add(newMenuItem);
		return CateringDaoMock.menuItems;
	}
	
	private int getNewMenuItemId()
	{
		int maxMenuItemId = 0;
		for(MenuItem menuItem : CateringDaoMock.menuItems)
		{
			if(menuItem.getMenuItemId() > maxMenuItemId)
			{
				maxMenuItemId = menuItem.getMenuItemId();
			}
		}
		return maxMenuItemId + 1;
	}

	@Override
	public List<OrderItem> getOrderItemsByOrderId(int orderId) 
	{
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for(OrderItem item : CateringDaoMock.orderItemList)
		{
			if(item.getOrderId() == orderId)
			{
				orderItems.add(item);
			}
		}
		
		return orderItems;
	}

	@Override
	public List<DailyNote> getAllDailyNotes() 
	{
		return CateringDaoMock.dailyNotes;
	}

	@Override
	public CompanySettings getCompanySettings() 
	{
		return settings;
	}

	@Override
	public List<String> getSauceOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAddressTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCrustOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPaymentTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyNote addDailyNote(DailyNote newDailyNote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> addOrderItems(List<OrderItem> newItem, int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrder(Order updateOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem addOrderItem(OrderItem addOrderItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> updateOrderItems(List<OrderItem> updateItems) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem updateOrderItem(OrderItem updateItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrderItem(OrderItem deleteItem) {
		// TODO Auto-generated method stub
		
	}


//	@Override
//	public List<Address> getAddressByBusinessName(String businessName) 
//	{
//		List<Address> addresses = new ArrayList<Address>();
//		
//		for(Address address : CateringDaoMock.addressList)
//		{
//			if(address.getBusinessName().equals(businessName))
//			{
//				addresses.add(address);
//			}
//		}
//		
//		return addresses;
//	}

	
	
	
}
