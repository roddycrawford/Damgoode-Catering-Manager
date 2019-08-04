package com.damgoodepies.catering.dao;

import java.util.List;

import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.CompanySettings;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.DailyNote;
import com.damgoodepies.catering.model.MenuItem;
import com.damgoodepies.catering.model.Order;
import com.damgoodepies.catering.model.OrderItem;

public interface CateringDao 
{
	public Address addAddress(Address newAddress);
	public List<Address> getAllAddresses();
	public Address getAddressByAddressId(int addressId);
	public Address updateAddress(Address updateAddress);
		
	public Customer addCustomer(Customer newCustomer);
	public List<Customer> getAllCustomers();
	public Customer getCustomerByCustomerId(int customerId);
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber);
	public List<Customer> getCustomerByName(String customerFirstName, String customerLastName);
	public Customer updateCustomer(Customer updateCustomer);
	
	public Order addOrder(Order newOrder);
	public List<Order> getAllOrders();
	public List<Order> getOrderByOrderId(int orderId);
	public List<Order> getOrderHistoryByCustomerId(int customerId);
//	updateOrder
	public List<Order> deleteByOrderId(int orderId);
	public List<OrderItem> addOrderItems(List<OrderItem> newItems, int orderId);

	public List<MenuItem> addMenuItem(MenuItem newMenuItem);	
	public List<MenuItem> getAllMenuItems();

	public List<OrderItem> getOrderItemsByOrderId(int orderId);
	public List<DailyNote> getAllDailyNotes();
//	public List<Address> getAddressByBusinessName(String businessName);
	
	public CompanySettings getCompanySettings();
	public List<String> getSauceOptions();
	public List<String> getAddressTypes();
	public List<String> getCrustOptions();
	public List<String> getPaymentTypes();
	public DailyNote addDailyNote(DailyNote newDailyNote);
	public Order updateOrder(Order updateOrder);
	public OrderItem addOrderItem(OrderItem addOrderItem);
	public List<OrderItem> updateOrderItems(List<OrderItem> updateItems);
	public OrderItem updateOrderItem(OrderItem updateItem);
	public void deleteOrderItem(OrderItem deleteItem);

	


}
