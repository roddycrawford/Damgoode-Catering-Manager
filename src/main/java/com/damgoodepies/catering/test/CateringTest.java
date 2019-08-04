package com.damgoodepies.catering.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.time.LocalDateTime;

import com.damgoodepies.catering.model.Address;
import com.damgoodepies.catering.model.Customer;
import com.damgoodepies.catering.model.OrderItem;
import com.damgoodepies.catering.model.OrderNote;
import com.damgoodepies.catering.model.Order;

public class CateringTest 
{

	public static void main(String[] args) 
	{  /*
//		public CateringAddress(String streetNum, String streetName, int roomNum, String addressTypeString,
//		String city, String state, String zipCode, String businessName, String addressNote)
		
		Address address = new Address("6706", "Cantrell Rd.", 100, "business",
				"Little Rock", "AR", "72207", "DamgoodePies", "this is an address note");
		
		Address address2 = new Address("6706", "Cantrell Rd.", 100, "none",
				"Little Rock", "AR", "72207", "DamgoodePies", "this is an address note");

		System.out.println("address: \n" + address + "\n");
		System.out.println("address2: \n" + address2 + "\n");
		
//		public CateringContact(String customerFirstName, String customerLastName,
//				String phoneNumber, CateringAddress address, String contactNote)
	
		Customer contact = new Customer("Roddy", "Crawford", "501-352-0642",
				"roddy@damgoode.com", address, "this is a contact note");
		
		System.out.println("contact: \n" + contact + "\n");
		
//		public CateringItem(int orderId, int size, String crust, 
//		String sauce, String name, BigMoney price)
		
		CurrencyUnit usd = CurrencyUnit.USD;
		BigMoney price = BigMoney.of(usd, 9.99);
		OrderItem item1 = new OrderItem(1, 10, "HT", "Pink Sauce", "Classic Pepperoni", price);
		System.out.println("item:\n" + item1);
		
		OrderItem item2 = new OrderItem(1, 10, "HT", "White Sauce", "Classic Pepperoni", price);
		System.out.println("item2:\n" + item2);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		items.add(item1);
		items.add(item2);
		
//		public CateringOrder(int orderNumber, CateringContact contact, List<CateringItem> items,
//		BigMoney subtotal, BigMoney deliveryFee, List<CateringNote> orderNotes,
//		LocalDateTime orderCreateDateTime, LocalDateTime orderDueDateTime)
		
		List<OrderNote> orderNotes = new ArrayList<OrderNote>();
		OrderNote cateringNote = new OrderNote(1, "this is an order note");
		orderNotes.add(cateringNote);
		LocalDateTime now = LocalDateTime.now();
		Order order1 = new Order(contact, items, 
				BigMoney.of(usd, 4.00), orderNotes, now, now, "Credit Card", false, false);
		
		System.out.println("\norder:\n" + order1);
		
		
*/	
		new CateringTest().run();
		
	}
	
	
	
	private void run() {
		System.out.println(capitalizeString(""));
		
	}



	private String capitalizeString(String capitalized)
	{
		String[] words = capitalized.split(" ");
		StringBuffer buffer = new StringBuffer();
		
		
		for(int i = 0; i < words.length; i++)
		{
			if(words[i].length() > 0)
			{
				words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
			}
		}
		
		for(int i = 0; i < words.length-1; i++)
		{
			if(words[i].length() > 0)
			{
				buffer.append(words[i] + " ");
			}
		}
		
		buffer.append(words[words.length-1]);
		
		return buffer.toString();
	}
}
