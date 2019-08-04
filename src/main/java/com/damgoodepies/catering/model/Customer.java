package com.damgoodepies.catering.model;


public class Customer 
{
	private static int lastCustomerIdUsed = 100;
	private int customerId;
	private String customerFirstName;
	private String customerLastName;
	private String phoneNumber;
	private String email;
//	private int addressId;
	private Address address;
	private String customerNote;

	
	public Customer(String customerFirstName, String customerLastName, Address address,
			String phoneNumber, String email, String customerNote)
	{
		this.customerId = ++lastCustomerIdUsed;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.phoneNumber = phoneNumber;
		this.setEmail(email);
//		this.addressId = addressId;
		this.customerNote = customerNote;
		this.address = address;
	}
	
	public Customer()
	{
		
	}
	
	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("customerId: " + customerId + "\n");
		buffer.append("addressId - from customer: " + address.getAddressId() + "\n");
		buffer.append("Name: " + customerFirstName + " " + customerLastName + "\n");
		buffer.append("Phone: " + phoneNumber + "\n");
		buffer.append("Email: " + email + "\n");
		buffer.append("address: " + address + "\n");
		buffer.append("contact note: " + customerNote);
		
		return buffer.toString();
	}

	public int getCustomerId()
	{
		return customerId;
	}

	public String getCustomerFirstName()
	{
		return customerFirstName;
	}

	public String getCustomerLastName() 
	{
		return customerLastName;
	}

	public String getPhoneNumber() 
	{
		return phoneNumber;
	}

	public Address getAddress()
	{
		return address;
	}

	public String getCustomerNote() 
	{
		return customerNote;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

//	public int getAddressId() {
//		return address.getAddressId();
//	}

//	public void setAddressId(int addressId) 
//	{
//		this.address.setAddressId(addressId);
//	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public void setCustomerId(int customerId) 
	{
		this.customerId = customerId;
	}

	public void setCustomerFirstName(String customerFirstName)
	{
		this.customerFirstName = capitalizeString(customerFirstName);
	}

	public void setCustomerLastName(String customerLastName)
	{
		this.customerLastName = capitalizeString(customerLastName);
	}

	public void setPhoneNumber(String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public void setCustomerNote(String customerNote) 
	{
		this.customerNote = customerNote;
	}
	
	private String capitalizeString(String capitalized)
	{
		String[] words = capitalized.split(" ");
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i < words.length; i++)
		{
			words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
		}
		
		for(int i = 0; i < words.length-1; i++)
		{
			buffer.append(words[i] + " ");
		}
		
		buffer.append(words[words.length-1]);
		
		return buffer.toString();
	}
}
