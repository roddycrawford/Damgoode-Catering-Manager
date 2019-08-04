package com.damgoodepies.catering.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.CompanySettings;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.DailyNote;
import com.damgoodepies.catering.model.MenuItem;
import com.damgoodepies.catering.model.Order;
import com.damgoodepies.catering.model.OrderItem;
import com.damgoodepies.catering.service.CateringService;



@Path("/catering")
public class CateringController 
{
	CateringService service = new CateringService();
	
	@GET
	@Path("/sauceoptions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getSauceOptions()
	{
		return service.getSauceOptions();
	}
	
	@GET
	@Path("/crustoptions")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getCrustOptions()
	{
		return service.getCrustOptions();
	}
	
	@GET
	@Path("/paymenttypes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getPaymentTypes()
	{
		return service.getPaymentTypes();
	}
	
	@GET
	@Path("/addresstypes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAddressTypes()
	{
		return service.getAddressTypes();
	}
	
	@GET
	@Path("/companysettings")
	@Produces(MediaType.APPLICATION_JSON)
	public CompanySettings getCompanySettings()
	{
		return service.getCompanySettings();
	}
	
	@GET
	@Path("/addresses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Address> getAllAddresses()
	{
		return service.getAllAddresses();
	}
	
	@GET
	@Path("/addresses/{addressId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Address getAddressByAddressId(@PathParam("addressId") int addressId)
	{
		return service.getAddressByAddressId(addressId);
	}
	
	@POST
	@Path("/addresses/add_new")
	@Produces(MediaType.APPLICATION_JSON)
	public Address addAddress(Address newAddress)
	{
		return service.addAddress(newAddress);
	}
	
	@PUT
	@Path("/addresses/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Address updateAddress(Address updateAddress)
	{
		return service.updateAddress(updateAddress);
	}
	
	@GET
	@Path("/customers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers()
	{
		return service.getAllCustomers();
	}
	
	@GET
	@Path("/customers/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerByCustomerId(@PathParam("customerId") int customerId)
	{
		return service.getCustomerByCustomerId(customerId);
	}
	
	@GET
	@Path("/customers/phone")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomerByPhoneNumber(@QueryParam("phoneNumber") String phoneNumber)
	{
		return service.getCustomerByPhoneNumber(phoneNumber);
	}
	
	@GET
	@Path("/customers/name")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getContactByName(@QueryParam("fName") String fName, @QueryParam("lName") String lName)
	{
		return service.getCustomerByName(fName, lName);
	}
	
	@POST
	@Path("/customers/add_new")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer addCustomer(Customer newCustomer)
	{
		System.out.println(newCustomer);
		return service.addCustomer(newCustomer);
	}
	
	@GET
	@Path("/orders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getAllOrders()
	{
		return service.getAllOrders();
	}
	
	@GET
	@Path("/orders/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrderByOrderId(@PathParam("orderId") int orderId)
	{
		return service.getOrderByOrderId(orderId);
	}
	
	@GET
	@Path("/history/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrderHistoryByContactId(@PathParam("customerId") int customerId)
	{
		System.out.println("customer Id: " + customerId);
		return service.getOrderHistoryByCustomerId(customerId);
	}
	
	@GET
	@Path("/orderitems/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrderItem> getOrderItemsByOrderId(@PathParam("orderId") int orderId)
	{
		return service.getOrderItemsByOrderId(orderId);
	}
	
	@GET
	@Path("/menuitems/retrieve")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuItem> getAllMenuItems()
	{
		return service.getAllMenuItems();
	}
	
	@GET
	@Path("/dailynotes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DailyNote> getAllDailyNotes()
	{
		return service.getAllDailyNotes();
	}
	
	@DELETE
	@Path("/orders/{orderId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> deleteByOrderId(@PathParam("orderId") int orderId)
	{
		return service.deleteByOrderId(orderId);
	}
	
	@POST
	@Path("/menuitems/add_new")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MenuItem> addMenuItem(MenuItem newMenuItem)
	{
		return service.addMenuItem(newMenuItem); 
	}
	
	@POST
	@Path("/orders/add_new")
	@Produces(MediaType.APPLICATION_JSON)
	public Order addOrder(Order newOrder)
	{
		return service.addOrder(newOrder);
	}
	
	@PUT
	@Path("/orders/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Order updateOrder(Order updateOrder)
	{
		return service.updateOrder(updateOrder);
	}
	

	
	@POST
	@Path("/dailynotes/add_new")
	@Produces(MediaType.APPLICATION_JSON)
	public DailyNote addDailyNote(DailyNote newDailyNote)
	{
		return service.addDailyNote(newDailyNote);
	}
	
	
	
	@PUT
	@Path("/customers/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer updateContact(Customer updateCustomer)
	{
		return service.updateCustomer(updateCustomer);
	}
}
