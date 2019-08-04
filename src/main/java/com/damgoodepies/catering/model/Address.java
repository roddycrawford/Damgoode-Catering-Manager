package com.damgoodepies.catering.model;

public class Address 
{
	private static int lastAddressIdUsed = 100;
	
	private int addressId;
	private int roomNum;
	private String streetNum;
	private String streetName;
	private AddressType addressType;
	private String city;
	private String state;
	private String zipCode;
	private String businessName;
	private String addressNote;
	
	public Address()
	{
		
	}
	
	public Address(String streetNum, String streetName, int roomNum, String addressTypeString,
			String city, String state, String zipCode, String businessName, String addressNote)
	{
		this.addressId = ++lastAddressIdUsed;
		this.streetNum = streetNum;
		this.streetName = streetName;
		this.roomNum = roomNum;
		setAddressType(addressTypeString);
		
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.businessName = businessName;
		this.addressNote = addressNote;
	}
	
	public void setAddressType(String addressTypeString)
	{
		switch(addressTypeString.toUpperCase())
		{
			case "APARTMENT":
				this.addressType = AddressType.APARTMENT;
				break;
			case "BUSINESS":
				this.addressType = AddressType.BUSINESS;
				break;
			case "HOSPITAL":
				this.addressType = AddressType.HOSPITAL;
				break;
			case "SCHOOL":
				this.addressType = AddressType.SCHOOL;
				break;
			case "HOUSE" :
			default:
				this.addressType = AddressType.HOUSE;
				break;
		}
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("AddressID: " + addressId + "\n");
		
		if(businessName.length() > 0)
		{
			buffer.append(businessName + "\n");
		}
		buffer.append(streetNum + " " + streetName);
		
		if(addressType.getAbbreviation() != "")
		{
			buffer.append(" " + addressType.getAbbreviation() + ": " + getRoomNum() + "\n");
		}
		else
		{
			buffer.append("\n");
		}
		
		buffer.append(city + ", " + state + " " + zipCode + "\n");

		
		return buffer.toString();
	}
	
	public int getAddressId() 
	{
		return addressId;
	}
	
	public String getAddressType()
	{
		return this.addressType.getTypeString();
	}

	public String getStreetNum() 
	{
		return streetNum;
	}

	public String getStreetName() 
	{
		return streetName;
	}

	public String getCity() 
	{
		return city;
	}

	public String getState() 
	{
		return state;
	}

	public String getZipCode() 
	{
		return zipCode;
	}

	public String getBusinessName() 
	{
		return businessName;
	}

	public String getAddressNote() 
	{
		return addressNote;
	}

	public int getRoomNum() 
	{
		return this.roomNum;
	}
	
	public String getAddressTypeAbr()
	{
		return addressType.getAbbreviation();
	}

	public void setRoomNum(int roomNum) 
	{
		this.roomNum = roomNum;
	}

	public void setAddressId(int addressId) 
	{
		this.addressId = addressId;
	}

	public void setStreetNum(String streetNum) 
	{
		this.streetNum = streetNum;
	}

	public void setStreetName(String streetName) 
	{
		this.streetName = capitalizeString(streetName);
	}

	public void setCity(String city) 
	{
		this.city = capitalizeString(city);
	}

	public void setState(String state) 
	{
		this.state = state.toUpperCase();
	}

	public void setZipCode(String zipCode) 
	{
		this.zipCode = zipCode;
	}

	public void setBusinessName(String businessName) 
	{
		this.businessName = businessName;
	}

	public void setAddressNote(String addressNote) 
	{
		this.addressNote = addressNote;
	}
	
	private String capitalizeString(String capitalized)
	{
//		System.out.println(capitalized);
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
