package com.damgoodepies.catering.model;

public enum AddressType 
{
	APARTMENT("Apartment", "Apt"),
	BUSINESS("Business", "Suite"),
	HOSPITAL("Hospital", "Room"),
	SCHOOL("School", "Room"),
	HOUSE("House", "");

	private String typeString;
	private String abbreviation;
	
	private AddressType(String typeString, String abbreviation)
	{
		this.typeString = typeString;
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() 
	{
		return abbreviation;
	}
	
	public String getTypeString()
	{
		return this.typeString;
	}
	
}
