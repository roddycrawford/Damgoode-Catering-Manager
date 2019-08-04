package com.damgoodepies.catering.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
//import java.util.ArrayList;
import java.util.List;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class Order
{
	private int orderId;
	private Customer customer;
//	private int customerId;
	private List<OrderItem> orderItems;
	private BigMoney subtotal;
	private BigMoney taxAmount;
	private BigMoney deliveryFee;
	private BigMoney cateringFee;
	private BigMoney total;
	private String orderNote;
	private LocalDateTime orderCreateDateTime;
	private LocalDateTime orderDueDateTime;
	private String paymentType;
	private boolean isPaid;
	private boolean isInPos;
	private boolean isBilledOrder;
	private boolean hasDeliveryFee;
	private boolean hasDealsApplied;
	private boolean isTaxExempt;
	private boolean isVoid;
	private String voidReason;
	private int numPeopleToFeed;
	
	
	private boolean hasPercentageDiscount;
	private boolean hasDollarDiscount;
	private double percentageDiscountRate;
	private BigMoney percentageDiscountAmt;
	private BigMoney dollarDiscountAmt;
	
	private boolean isBuffetOrder;
	private BigMoney pricePerHead;
	
	private static int lastOrderNumUsed = 0;
	private static CurrencyUnit usd = CurrencyUnit.USD;
	
	public Order(Customer customer, LocalDateTime orderCreateDateTime, 
			LocalDateTime orderDueDateTime, String paymentType, boolean isPaid, boolean isInPos, 
			boolean isBilledOrder, boolean isTaxExempt, boolean hasPercentageDiscount, 
			boolean hasDollarDiscount, double percentageDiscountRate, double dollarDiscountAmt, 
			boolean hasDeliveryFee, List<OrderItem> orderItems, String orderNote, boolean isVoid,
			String voidReason, int numPeopleToFeed, boolean isBuffetOrder, BigDecimal pricePerHead,
			boolean hasDealsApplied)
	{
		this.orderId = ++lastOrderNumUsed;
		this.customer = customer;
//		this.customerId = customerId;
//		this.orderItems = new ArrayList<OrderItem>();

		this.orderNote = orderNote;
		this.orderCreateDateTime = orderCreateDateTime;
		this.orderDueDateTime = orderDueDateTime;
		this.paymentType = paymentType;
		this.isPaid = isPaid;
		this.isInPos = isInPos;
		this.isBilledOrder = isBilledOrder;
		this.isTaxExempt = isTaxExempt;
		this.hasPercentageDiscount = hasPercentageDiscount;
		this.hasDollarDiscount = hasDollarDiscount;
		this.dollarDiscountAmt = BigMoney.of(usd, dollarDiscountAmt);
		this.percentageDiscountRate = percentageDiscountRate;
		this.hasDeliveryFee = hasDeliveryFee;
		this.orderItems = orderItems;
		this.isVoid = isVoid;
		this.voidReason = voidReason;
		this.numPeopleToFeed = numPeopleToFeed;
		this.isBuffetOrder = isBuffetOrder;
		this.pricePerHead = BigMoney.of(usd, pricePerHead);
		this.hasDealsApplied = hasDealsApplied;
		calculateAllMonies();
	}
	
	public Order()
	{
		
	}
	
	private void calculateAllMonies()
	{
		BigMoney buildSubtotal = BigMoney.of(usd, 0.00);
		
		for(OrderItem item : orderItems)
		{
			buildSubtotal = buildSubtotal.plus(item.getActivePrice());
			System.out.println(buildSubtotal);
//			setSubtotal(this.subtotal.plus(item.getActivePrice()).getAmount());
		}
		setSubtotal(buildSubtotal.getAmount());
		System.out.println("subtotal: " + this.subtotal);
		
		

		if(isTaxExempt)
		{
			setTaxAmount(BigMoney.of(usd, 0.00).getAmount());
		}
		else
		{
			setTaxAmount((this.subtotal.multipliedBy(.11)).getAmount());
		}
		
		if(hasDeliveryFee)
		{
			setDeliveryFee((BigMoney.of(usd, 4)).getAmount());
		}
		else
		{
			setDeliveryFee((BigMoney.of(usd, 0.00)).getAmount());
		}
		
		if(isBilledOrder)
		{
			setCateringFee((this.subtotal.multipliedBy(.1)).getAmount());
			System.out.println("cateringFee" + this.cateringFee);
		}
		else
		{
			setCateringFee(BigMoney.of(usd, 0.00).getAmount());
		}
		
		if(hasPercentageDiscount)
		{
			this.percentageDiscountAmt = this.subtotal.multipliedBy(this.percentageDiscountRate/100);
			System.out.println("percentageDiscountAmount: " + this.percentageDiscountAmt);
			this.subtotal = this.subtotal.minus(this.percentageDiscountAmt);
			System.out.println("subtotal - percentageDiscountAmount: " + this.subtotal);
		}
		else
		{
			this.percentageDiscountAmt = BigMoney.of(usd, 0.00);
			System.out.println("percentageDiscountAmount-should be 0: " + this.percentageDiscountAmt);
		}
		
		if(hasDollarDiscount)
		{
			System.out.println("dollarDiscountAmt: " + this.dollarDiscountAmt);
			this.subtotal = this.subtotal.minus(this.dollarDiscountAmt);
		}
		else
		{
			this.dollarDiscountAmt = BigMoney.of(usd, 0.00);
		}
		
		setTotal((this.subtotal.plus(this.taxAmount).plus(this.cateringFee).plus(this.deliveryFee)).getAmount());
	}
	
//	@Override
//	public String toString()
//	{
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("Order Number: " + orderId + "\n");
//		buffer.append("Contact:\n" + customer + "\n");
//		
//		buffer.append("items:\n");
//		for(OrderItem item : orderItems)
//		{
//			buffer.append(item + "\n");
//		}
//		
//		buffer.append("Subtotal: " + CompanySettings.printMoney(subtotal) + "\n");
//		buffer.append("Tax: " + CompanySettings.printMoney(taxAmount) + "\n");
//		buffer.append("Delivery Fee: " + CompanySettings.printMoney(deliveryFee) + "\n");
//		buffer.append("Total: " + CompanySettings.printMoney(total) + "\n");
//		buffer.append("Payment Type: " + paymentType + "\n");
//		
//		buffer.append("Notes:\n");
//		for(OrderNote note : orderNotes)
//		{
//			buffer.append(note + "\n");
//		}
//		
//		return buffer.toString();
//	}

	public int getOrderId() 
	{
		return orderId;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public List<OrderItem> getItems()
	{
		return orderItems;
	}

	public BigDecimal getSubtotal() 
	{
		return subtotal.getAmount();
	}

	public BigDecimal getTaxAmount()
	{
		return taxAmount.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getDeliveryFee() 
	{
		return deliveryFee.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getTotal()
	{
		return total.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getOrderNote()
	{
		return orderNote;
	}

	public LocalDateTime getOrderCreateDateTime() 
	{
		return orderCreateDateTime;
	}

	public LocalDateTime getOrderDueDateTime() 
	{
		return orderDueDateTime;
	}

	public String getPaymentType() 
	{
		return paymentType;
	}

	public boolean getIsPaid() 
	{
		return isPaid;
	}

	public boolean getIsInPos() 
	{
		return isInPos;
	}

	public BigDecimal getCateringFee() 
	{
		return cateringFee.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
	}

//	public int getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(int customerId) {
//		this.customerId = customerId;
//	}

	public boolean getIsBilledOrder() {
		return isBilledOrder;
	}

	public boolean getHasDeliveryFee() {
		return hasDeliveryFee;
	}

	public boolean getIsTaxExempt() {
		return isTaxExempt;
	}

	public boolean getHasPercentageDiscount() 
	{
		return hasPercentageDiscount;
	}

	public boolean getHasDollarDiscount() 
	{
		return hasDollarDiscount;
	}

	public double getPercentageDiscountRate() 
	{
		return percentageDiscountRate;
	}

	public BigDecimal getPercentageDiscountAmt() 
	{
		return percentageDiscountAmt.getAmount();
	}

	public BigDecimal getDollarDiscountAmt() 
	{
		return dollarDiscountAmt.getAmount();
	}

	public boolean getIsVoid() 
	{
		return isVoid;
	}

	public String getVoidReason() 
	{
		return voidReason;
	}

	public int getNumPeopleToFeed() 
	{
		return numPeopleToFeed;
	}

	public boolean getIsBuffetOrder() 
	{
		return isBuffetOrder;
	}

	public BigDecimal getPricePerHead() 
	{
		return pricePerHead.getAmount();
	}

	public boolean getHasDealsApplied() 
	{
		return hasDealsApplied;
	}

	public void setHasDealsApplied(boolean hasDealsApplied) 
	{
		this.hasDealsApplied = hasDealsApplied;
	}

	public void setIsBuffetOrder(boolean isBuffetOrder) 
	{
		this.isBuffetOrder = isBuffetOrder;
	}

	public void setPricePerHead(BigDecimal pricePerHead) 
	{
		this.pricePerHead = BigMoney.of(usd, pricePerHead);
	}

	public void setVoidReason(String voidReason) 
	{
		this.voidReason = voidReason;
	}

	public void setNumPeopleToFeed(int numPeopleToFeed) 
	{
		this.numPeopleToFeed = numPeopleToFeed;
	}

	public void setIsVoid(boolean isVoid) 
	{
		this.isVoid = isVoid;
	}

	public void setHasPercentageDiscount(boolean hasPercentageDiscount) 
	{
		this.hasPercentageDiscount = hasPercentageDiscount;
	}

	public void setHasDollarDiscount(boolean hasDollarDiscount) 
	{
		this.hasDollarDiscount = hasDollarDiscount;
	}

	public void setPercentageDiscountRate(double percentageDiscountRate) 
	{
		this.percentageDiscountRate = percentageDiscountRate;
	}

	public void setPercentageDiscountAmt(BigDecimal percentageDiscountAmt) 
	{
		this.percentageDiscountAmt = BigMoney.of(usd, percentageDiscountAmt.setScale(2, RoundingMode.HALF_UP));
	}

	public void setDollarDiscountAmt(BigDecimal dollarDiscountAmt) 
	{
		this.dollarDiscountAmt = BigMoney.of(usd, dollarDiscountAmt.setScale(2, RoundingMode.HALF_UP));
	}

	public void setIsTaxExempt(boolean isTaxExempt) {
		this.isTaxExempt = isTaxExempt;
	}

	public void setHasDeliveryFee(boolean hasDeliveryFee) {
		this.hasDeliveryFee = hasDeliveryFee;
	}

	public void setCateringFee(BigDecimal cateringFee) 
	{
		this.cateringFee = BigMoney.of(usd, cateringFee.setScale(2, RoundingMode.HALF_UP));
	}

	public void setIsBilledOrder(boolean isBilledOrder) 
	{
		this.isBilledOrder = isBilledOrder;
	}

	public void setIsPaid(boolean isPaid) 
	{
		this.isPaid = isPaid;
	}

	public void setIsInPos(boolean isInPos) 
	{
		this.isInPos = isInPos;
	}

	public void setPaymentType(String paymentType) 
	{
		this.paymentType = paymentType;
	}

	public void setOrderCreateDateTime(LocalDateTime orderCreateDateTime) 
	{
		this.orderCreateDateTime = orderCreateDateTime;
	}

	public void setOrderDueDateTime(LocalDateTime orderDueDateTime) 
	{
		this.orderDueDateTime = orderDueDateTime;
	}

	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public void setItems(List<OrderItem> items) 
	{
		this.orderItems = items;
	}

	public void setSubtotal(BigDecimal subtotal) 
	{
		this.subtotal = BigMoney.of(usd, subtotal.setScale(2, RoundingMode.HALF_UP));
	}

	public void setTaxAmount(BigDecimal taxAmount) 
	{
		this.taxAmount = BigMoney.of(usd, taxAmount.setScale(2, RoundingMode.HALF_UP));
	}

	public void setDeliveryFee(BigDecimal deliveryFee) 
	{
		this.deliveryFee = BigMoney.of(usd, deliveryFee.setScale(2, RoundingMode.HALF_UP));
	}

	public void setTotal(BigDecimal total) 
	{
		this.total = BigMoney.of(usd, total.setScale(2, RoundingMode.HALF_UP));
	}

	public void setOrderNote(String orderNote)
	{
		this.orderNote = orderNote;
	}
}
