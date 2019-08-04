package com.damgoodepies.catering.model;

//import java.math.BigDecimal;
//
//import org.joda.money.BigMoney;

public class CompanySettings 
{
	private double taxRate;
	private double standardDeliveryFee;
	private double standardCateringRate;
	
	public CompanySettings(double taxRate, double standardDeliveryFee, double standardCateringRate)
	{
		this.taxRate = taxRate;
		this.standardDeliveryFee = standardDeliveryFee;
		this.standardCateringRate = standardCateringRate;
	}
	
	public CompanySettings()
	{
		
	}

	public double getTaxRate() 
	{
		return taxRate;
	}
	
	public double getStandardDeliveryFee() 
	{
		return standardDeliveryFee;
	}

	public double getStandardCateringRate() {
		return standardCateringRate;
	}

	public void setStandardCateringRate(double standardCateringRate) 
	{
		this.standardCateringRate = standardCateringRate;
	}

	public void setTaxRate(double taxRate) 
	{
		this.taxRate = taxRate;
	}

	public void setStandardDeliveryFee(double standardDeliveryFee) 
	{
		this.standardDeliveryFee = standardDeliveryFee;
	}

//	public static String printMoney(BigMoney money)
//	{
//		BigDecimal formatMoney = money.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
//		return "$" + formatMoney;
//	}
}
