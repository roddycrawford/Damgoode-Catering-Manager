package com.damgoodepies.catering.model;

import java.math.BigDecimal;

import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

public class OrderItem 
{
	private static int lastOrderItemIdUsed = 100;
	private int orderItemId;
	private int orderId;
//	private int menuItemId;
	
	private MenuItem menuItem;
	
//	private String size;
	private String crust;
	private String sauce;
//	private String itemName;
//	private String dealCategory;
//	private String menuGroup;
	private String itemNote;
	private boolean hasDealApplied;
	private boolean markedToRemoveFromOrder;
	
//	private BigMoney menuPrice;
	private BigMoney activePrice;
	private CurrencyUnit usd = CurrencyUnit.USD;
	

	public OrderItem(int orderId, MenuItem menuItem, String crust, String sauce, String itemNote,
			BigDecimal activePrice, boolean hasDealApplied, boolean markedToRemoveFromOrder)
	{
		this.orderItemId = ++lastOrderItemIdUsed;
		this.orderId = orderId;
//		this.menuItemId = menuItemId;
		this.menuItem = menuItem;
//		this.size = menuItem.getSize();
		this.crust = crust;
		this.sauce = sauce;
//		this.itemName = menuItem.getItemName();
//		this.dealCategory = menuItem.getDealCategory();
//		this.menuGroup = menuItem.getMenuGroup();
//		this.menuPrice = BigMoney.of(usd, menuItem.getMenuPrice());
		this.activePrice = BigMoney.of(usd, activePrice);
		this.itemNote = itemNote;
		this.hasDealApplied = hasDealApplied;
		this.markedToRemoveFromOrder = markedToRemoveFromOrder;
	}
	
	public OrderItem() 
	{
		
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("Order Item ID: " + orderItemId + "\n");
		buffer.append(menuItem.getSize() + "\" " + crust + " " + menuItem.getItemName() + "\n");
//		buffer.append(CompanySettings.printMoney(BigMoney.of(usd, menuItem.getMenuPrice())));
		
		return buffer.toString();
	}

//	public String getSize() 
//	{
//		return size;
//	}

	public String getCrust() 
	{
		return crust;
	}

//	public String getItemName() 
//	{
//		return itemName;
//	}

//	public BigDecimal getMenuPrice() 
//	{
//		return menuPrice.getAmount();
//	}

	/**
	 * @return the sauce
	 */
	public String getSauce() 
	{
		return sauce;
	}

	public int getOrderItemId() 
	{
		return orderItemId;
	}

	public int getOrderId() 
	{
		return orderId;
	}

//	public String getDealCategory() 
//	{
//		return dealCategory;
//	}

//	public String getMenuGroup()
//	{
//		return menuGroup;
//	}

	public BigDecimal getActivePrice() 
	{
		return activePrice.getAmount();
	}

	public String getItemNote() {
		return itemNote;
	}

//	public int getMenuItemId() {
//		return menuItemId;
//	}

//	public void setMenuItemId(int menuItemId) {
//		this.menuItemId = menuItemId;
//	}

	public MenuItem getMenuItem() 
	{
		return menuItem;
	}

	public boolean getHasDealApplied() 
	{
		return hasDealApplied;
	}

	public boolean getMarkedToRemoveFromOrder() 
	{
		return markedToRemoveFromOrder;
	}

	public void setMarkedToRemoveFromOrder(boolean markedToRemoveFromOrder) 
	{
		this.markedToRemoveFromOrder = markedToRemoveFromOrder;
	}

	public void setHasDealApplied(boolean hasDealApplied) 
	{
		this.hasDealApplied = hasDealApplied;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	public void setItemNote(String itemNote) 
	{
		this.itemNote = itemNote;
	}

//	public void setDealCategory(String dealCategory) 
//	{
//		this.dealCategory = dealCategory;
//	}

//	public void setMenuGroup(String menuGroup) 
//	{
//		this.menuGroup = menuGroup;
//	}

	public void setActivePrice(BigDecimal activePrice) 
	{
		this.activePrice = BigMoney.of(CurrencyUnit.USD, activePrice);
	}

	public void setOrderItemId(int orderItemId) 
	{
		this.orderItemId = orderItemId;
	}

	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}

	/**
	 * @param sauce the sauce to set
	 */
	public void setSauce(String sauce) 
	{
		this.sauce = sauce;
	}

//	public void setSize(String size) 
//	{
//		this.size = size;
//	}

	public void setCrust(String crust) 
	{
		this.crust = crust;
	}

//	public void setItemName(String name) 
//	{
//		this.itemName = name;
//	}

//	public void setMenuPrice(BigDecimal price) 
//	{	
//		
//		this.menuPrice = BigMoney.of(CurrencyUnit.USD, price);
//	}
}
