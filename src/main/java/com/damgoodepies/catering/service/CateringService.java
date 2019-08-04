package com.damgoodepies.catering.service;

//import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.damgoodepies.catering.dao.CateringDao;
import com.damgoodepies.catering.dao.CateringDaoImpl;
import com.damgoodepies.catering.dao.CateringDaoMock;
import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.CompanySettings;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.DailyNote;
import com.damgoodepies.catering.model.Order;
import com.damgoodepies.catering.model.OrderItem;
import com.damgoodepies.catering.model.Error;
import com.damgoodepies.catering.model.MenuItem;

//TODO Add 'addAddressNoteByAddressId

public class CateringService 
{
//	CateringDao dao = new CateringDaoMock();
	CateringDao dao = new CateringDaoImpl();
	
	public CateringService()
	{
	}

	public List<Address> getAllAddresses() 
	{
		return dao.getAllAddresses();
	}

	public Address getAddressByAddressId(int addressId) 
	{
		validateAddressId(addressId);
		return dao.getAddressByAddressId(addressId);
	}
	
	public List<Customer> getAllCustomers() 
	{
		return dao.getAllCustomers();
	}
	
	public Customer getCustomerByCustomerId(int customerId) 
	{
//		validateCustomerId(customerId);
		return dao.getCustomerByCustomerId(customerId);
	}
	
	public List<Customer> getCustomerByPhoneNumber(String phoneNumber) 
	{
		validateCustomerPhone(phoneNumber);
		return dao.getCustomerByPhoneNumber(phoneNumber);
	}
	
	public List<Customer> getCustomerByName(String customerFirstName, String customerLastName) 
	{
		validateCustomerByName(customerFirstName, customerLastName);
		return dao.getCustomerByName(customerFirstName, customerLastName);
	}
	
	public List<Order> getAllOrders() 
	{
		return dao.getAllOrders();
	}
	
	public List<Order> getOrderByOrderId(int orderId) 
	{
		validateOrderByOrderId(orderId);
		return dao.getOrderByOrderId(orderId);
	}
	
	public List<Order> getOrderHistoryByCustomerId(int customerId) 
	{
//		validateCustomerId(customerId);
		return dao.getOrderHistoryByCustomerId(customerId);
	}
	
	public List<MenuItem> getAllMenuItems() 
	{
		return dao.getAllMenuItems();
	}

	public List<OrderItem> getOrderItemsByOrderId(int orderId) 
	{
//		validateOrderByOrderId(orderId);
		return dao.getOrderItemsByOrderId(orderId);
	}

	public List<Order> deleteByOrderId(int orderId) 
	{
		validateOrderByOrderId(orderId);
		return dao.deleteByOrderId(orderId);
	}
	
	public Order addOrder(Order newOrder) 
	{
//		validateCustomerId(newOrder.getCustomerId());
//		validateCustomerPhone(newOrder.getCustomer().getPhoneNumber());
//		validateAddressId(newOrder.getCustomer().getAddressId());
		
//		Find out if the customer for the new order is a new customer or if they are
//		already in the database. If they're new it adds them to the database,
//		if they aren't new it updates the customer which also updates the address.
		Customer customerForNewOrder = new Customer();
		if(newOrder.getCustomer().getCustomerId() == 0)
		{
			customerForNewOrder = addCustomer(newOrder.getCustomer());
			newOrder.setCustomer(customerForNewOrder);
		}
		else
		{
			customerForNewOrder = updateCustomer(newOrder.getCustomer());
			newOrder.setCustomer(customerForNewOrder);
		}

				
		return dao.addOrder(newOrder);
	}
	
	public Customer addCustomer(Customer newCustomer) 
	{
		Address newAddress = addAddress(newCustomer.getAddress());
//		System.out.println("new address: " + newAddress);
		newCustomer.setAddress(newAddress);
//		newCustomer.setAddressId(newAddress.getAddressId());
//		System.out.println("new customer: " + newCustomer);
				
		return dao.addCustomer(newCustomer);
	}
	
//	private Address findOrBuildAddress(Address address)
//	{
//		List<Address> addresses = dao.getAddressByBusinessName(address.getBusinessName());
//		
//		if(addresses.size() > 0)
//		{
//			for(Address currentAddress : addresses)
//			{
//				if((address.getRoomNum() == currentAddress.getRoomNum()) &&
//					address.getStreetNum().equals(currentAddress.getStreetNum()) &&
//					address.getStreetName().equals(currentAddress.getStreetName()) &&
//					address.getCity().equals(currentAddress.getCity()) &&
//					address.getState().equals(currentAddress.getState()) &&
//					address.getZipCode().equals(currentAddress.getZipCode()) &&
//					address.getAddressNote().equals(currentAddress.getAddressNote()))
//				{
//					address.setAddressId(currentAddress.getAddressId());
//					break;
//				}	
//			}
//		}
//		
//		
//		
//		
//		return address;
//	}
	
//	private boolean isExistingAddress(Address address)
//	{
//		boolean result = false;
//		
//		
//		
//		
//		return result;
//	}
	
	public Address addAddress(Address newAddress) 
	{
		return dao.addAddress(newAddress);
	}
	
	public DailyNote addDailyNote(DailyNote newDailyNote) 
	{
		return dao.addDailyNote(newDailyNote);
	}

	public Address updateAddress(Address updateAddress) 
	{
		return dao.updateAddress(updateAddress);
	}
	
/*
 * 	Checks to see if the address already associated with the updateCustomer has an addressId of 0.
 * 	If it does then that address needs to be added to the database, which it does and then it sets
 * 	the address of updateCustomer to the returned Address from addAddress which has it's addressId.
 * 
 * 	If the addressId is not 0 then the address just needs to be updated, so it updates the address.
 */
	public Customer updateCustomer(Customer updateCustomer) 
	{
		if(updateCustomer.getAddress().getAddressId() == 0)
		{
			Address updatedAddress = addAddress(updateCustomer.getAddress());
			updateCustomer.setAddress(updatedAddress);
		}
		else
		{
			updateAddress(updateCustomer.getAddress());
		}
		
		return dao.updateCustomer(updateCustomer);
	}
	

	public List<MenuItem> addMenuItem(MenuItem newMenuItem) 
	{
		return dao.addMenuItem(newMenuItem); 
	}
	
	public List<DailyNote> getAllDailyNotes() 
	{
		return dao.getAllDailyNotes();
	}

//	private void invalidUpdate() 
//	{
//		Error error = new Error(105, "invalid update, no values to update");
//		Response response = Response.status(400)
//				 .entity(error)
//				 .build();
//		throw new WebApplicationException(response);
//		
//	}
	
	private boolean validateOrderByOrderId(int orderId)
	{
		boolean isValid = true;
		if((dao.getOrderByOrderId(orderId).size() != 1) || (orderId == 0))
		{
			isValid = false;
			
			Error error = new Error(104, "invalid value for orderId, order ID: \'" + orderId + "\' does not exist");
			Response response = Response.status(400)
					 .entity(error)
					 .build();
			throw new WebApplicationException(response);
		}
		
		return isValid;
	}

	private boolean validateCustomerByName(String customerFirstName, String customerLastName)
	{
		boolean isValid = true;
		if((dao.getCustomerByName(customerFirstName, customerLastName).size() == 0) 
				|| (customerFirstName.equals("") && customerLastName.equals("")))
		{
			isValid = false;
			
			Error error = new Error(103, "No Customer by the Name: \'" + customerFirstName + " " + customerLastName);
			Response response = Response.status(400)
					 .entity(error)
					 .build();
			throw new WebApplicationException(response);
		}
		
		return isValid;
	}
	
	private boolean validateCustomerPhone(String phoneNumber)
	{
		boolean isValid = true;
		if((dao.getCustomerByPhoneNumber(phoneNumber).size() == 0) || (phoneNumber.equals("")))
		{
			isValid = false;
			
			Error error = new Error(102, "Phone Number: \'" + phoneNumber + "\' does not exist");
			Response response = Response.status(400)
					 .entity(error)
					 .build();
			throw new WebApplicationException(response);
		}
		
		return isValid;
	}
	
//	private boolean validateCustomerId(int customerId)
//	{
//		boolean isValid = true;
//		if((dao.getCustomerByCustomerId(customerId).size() != 1) || (customerId == 0))
//		{
//			isValid = false;
//			
//			Error error = new Error(101, "invalid value for contactId, contact for ID \'" + customerId + "\' does not exist");
//			Response response = Response.status(400)
//					 .entity(error)
//					 .build();
//			throw new WebApplicationException(response);
//		}
//		
//		return isValid;
//	}

	private boolean validateAddressId(int addressId) 
	{
		boolean isValid = true;
//		if((dao.getAddressByAddressId(addressId).size() != 1) || (addressId == 0))
//		{
//			isValid = false;
//			
//			Error error = new Error(100, "invalid value for addressID, address for ID \'" + addressId + "\' does not exist");
//			Response response = Response.status(400)
//					 .entity(error)
//					 .build();
//			throw new WebApplicationException(response);
//		}
//		
		return isValid;
	}

	public CompanySettings getCompanySettings() 
	{
		return dao.getCompanySettings();
	}

	public List<String> getSauceOptions() 
	{
		return dao.getSauceOptions();
	}

	public List<String> getAddressTypes() 
	{
		return dao.getAddressTypes();
	}

	public List<String> getCrustOptions() 
	{
		return dao.getCrustOptions();
	}

	public List<String> getPaymentTypes() 
	{
		return dao.getPaymentTypes();
	}

	/*
	 * 	Checks to see if the customerId of updateOrder's customer is 0. If
	 * 	it is 0 then the customer needs to be added to the database, which it
	 * 	then does. updateOrder's customer is then set to the Customer returned
	 * 	by that method which has now been added to the database and has it's 
	 * 	actual customer id.
	 * 
	 * 	If the customerId is not 0 then the customer only needs to be updated
	 * 	which it then does. updateOrder does not need to reset it's customer
	 * 	at this point because the customer was updated with the information
	 * 	that updateOrder already had.
	 */
	public Order updateOrder(Order updateOrder) 
	{
		if(updateOrder.getCustomer().getCustomerId() == 0)
		{
			Customer updatedCustomer = addCustomer(updateOrder.getCustomer());
			updateOrder.setCustomer(updatedCustomer);
		}
		else
		{
			updateCustomer(updateOrder.getCustomer());
		}
		
		return dao.updateOrder(updateOrder);
	}

	


	





	



	

	

	

	
	




	
	
}
